import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private final String iban;
    private final String owner;
    private double balance;
    // объявляем закрытое поле с именем lock замок
    private final Lock lock = new ReentrantLock();

    // конструктор

    public Account(String iban, String owner, double balance) {
        this.iban = iban;
        this.owner = owner;
        this.balance = balance;
    }
    // пополнение счета

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
        // для снятия денег
    }

    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (getBalance() >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
    // функции, которые возвращают значения закрытых переменных, созданных в этом классе

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }

    public String getIban() {
        return iban;
    }
    // преобразует числа в строку

    @Override
    public String toString() {
        return "Account{" +
                "iban='" + iban + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
    // метод для транзакций между счетами

    public static boolean transfer(Account from, Account to, double amount, long timeout, TimeUnit unit) throws InterruptedException {
        Lock lock1 = from.getLock();
        Lock lock2 = to.getLock();

        boolean lock1Acquired = false;
        boolean lock2Acquired = false;

        // lock1.tryLock(timeout, unit) запрос на получение блокировки
        // в течении определенного времени

        try {
            lock1Acquired = lock1.tryLock(timeout, unit);
            if (lock1Acquired) {

                lock2Acquired = lock2.tryLock(timeout, unit);

                if (lock2Acquired) {

                    if (from.withdraw(amount)) {
                        to.deposit(amount);
                        return true;
                    } else {
                        System.out.println("Недостаточно средств на счету " + from.getIban());
                        return false;
                    }
                } else {
                    System.out.println("Не удалось получить блокировку " + to.getIban());
                }
            } else {
                System.out.println(" Не удалось получить блокировку" + from.getIban());
            }
            return false;
        } finally {
            if (lock2Acquired) {
                lock2.unlock();
            }
            if (lock1Acquired) {
                lock1.unlock();
                //Если поток не получит блокировку за время ожидания, он снимает все блокировки,
                // которые у него уже есть, и возвращает их, чтобы не было взаимной блокировки.
            }
        }
    }
}
