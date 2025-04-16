public class MyRunnable implements Runnable{
    @Override
    public void run() {


        String name = Thread.currentThread().getName();  // Thread.currentThread() - текущий поток
        System.out.println("(i) Thread " + name + " start");
        for (int i = 0; i < 120; i++) {
            System.out.println("(i)  " + name + " " + i);
            try {
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("(i) Thread " + name + " finish");
    }
}
