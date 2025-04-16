public class Main {
    public static void main(String[] args) {

        System.out.println("Start main()");

        MyThread thread1 = new MyThread("Jack");
        MyThread thread2 = new MyThread("Nick");

        thread1.start(); /// no run() !!!;
        thread2.start();


        for (int i = 0; i <10 ; i++) {

            System.out.println("Main:  "  + i);

        }
        System.out.println("____________finish main");
    }
}