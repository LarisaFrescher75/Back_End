public class Main {
    public static void main(String[] args) throws InterruptedException {
        LatencyMonitor monitor = new LatencyMonitor();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                double latency = Math.random() * Integer.MAX_VALUE;
                monitor.updateLatency(latency);
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Minimum latency: " + monitor.getMinLatency());
    }
}