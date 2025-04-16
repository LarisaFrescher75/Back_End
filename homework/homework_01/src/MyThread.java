public class MyThread extends Thread{
    @Override
    public void run() {

        String name = Thread.currentThread().getName();  // Thread.currentThread() - текущий поток
        System.out.println("Thread " + name + " start");
        for (int i = 0; i < 120; i++) {
            System.out.println(name + " " + i);
            try {
                Thread.sleep(3000);  // пауза 3с.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Thread " + name + " finish");


    }
}
