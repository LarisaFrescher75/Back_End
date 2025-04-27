public class Main {


    public static void main(String[] args) throws InterruptedException {

        Account accountA = new Account("DE1111", "Jack", 1000);
        Account accountB = new Account("DE2222", "John", 1000);

        Thread thread1 = new Thread(() -> transfer(accountA, accountB, 100), "T1");
        Thread thread2 = new Thread(() -> transfer(accountB, accountA, 200), "T2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(accountA);
        System.out.println(accountB);
    }

    public static void transfer(Account from, Account to, double amount) {

        Account firstLock, secondLock;

        if (System.identityHashCode(from) < System.identityHashCode(to)) {
            firstLock = from;
            secondLock = to;
        } else {
            firstLock = to;
            secondLock = from;
        }

        synchronized (firstLock) {
            System.out.println("account" + from + "is locked ay" + Thread.currentThread().getName());

            synchronized (secondLock) {


                if (from.withdraw(amount)) {

                    System.out.println("account" + to + "is locked ay" + Thread.currentThread().getName());

                    to.deposit(amount);
                }


            }
        }
    }
}