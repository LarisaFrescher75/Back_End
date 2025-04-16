public class Main {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new MyRunnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 120 ; i++) {
                    if (i % 2 == 0){

                        System.out.println("Numbers divisible by 2: " + i);
                    }

                }
            }
        });
        Thread thread2 = new Thread(new MyRunnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 120 ; i++) {
                    if (i % 3 == 0){

                        System.out.println("Numbers divisible by 3: " + i);
                    }

                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread finished");

    }
}