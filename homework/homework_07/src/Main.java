import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // создаем аккаунты

        Account accountA = new Account("DE1111", "Jack", 1000);
        Account accountB = new Account("DE2222", "John", 1000);

        // создаем потоки, применяем класс TimeUnit со свойством MILLISECONDS для усыпления программы

        Thread thread1 = new Thread(() -> {
            try {
                if (Account.transfer(accountA, accountB, 200, 300, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread 1:  Транзакция проведена успешно");
                } else {
                    System.out.println("Thread 1: Не удалось совершить транзакцию");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                if (Account.transfer(accountB, accountA, 300, 300, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread  1: Транзакция проведена успешно");
                } else {
                    System.out.println("Thread 2: Не удалось совершить транзакцию");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        // потоки стартуют

        thread1.start();
        thread2.start();

        // этот метод позволяет приостановить выполнение текущего потока до тех пор,
        // пока поток, к которому применен данный метод не закончит свою работу.

        thread1.join();
        thread2.join();

        System.out.println(accountA);
        System.out.println(accountB);
    }
}