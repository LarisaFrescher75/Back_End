public class Main {


    private static final int N_BOX = 1000;
    private static final int CAPACITY = 2;
    private static String premiumLoader = null;


    private static final Object finishLock = new Object();


    public static void main(String[] args) {

        Warehouse warehouse1 = new Warehouse("1");
        Warehouse warehouse2 = new Warehouse("2");

        Thread[] loaders = {
                new Thread(new Loader("Jack", N_BOX, CAPACITY, warehouse1, warehouse2)),
                new Thread(new Loader("John", N_BOX, CAPACITY, warehouse1, warehouse2)),
                new Thread(new Loader("Nick", N_BOX, CAPACITY, warehouse1, warehouse2)),
        };

        for (Thread thread : loaders) {
            thread.start();
        }

        for (Thread thread : loaders) {
            try {
                thread.join();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.println("Finished work first: " + premiumLoader);
    }

        public static void setFinishLock (String name){
            synchronized (finishLock) {
                if (premiumLoader == null) {
                    premiumLoader = name;
                    System.out.println(name + "first to finish");
                }

            }


        }
    }






