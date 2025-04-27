public class Main {
    public static void main(String[] args) {
        Document docA = new Document(1, "A");
        Document docB = new Document(2, "B");
        Document docC = new Document(3, "C");

        docA.addRelatedDocument(docB);
        docB.addRelatedDocument(docA);
        docC.addRelatedDocument(docA);

        Thread t1 = new Thread(docA::edit, "T1");
        Thread t2 = new Thread(docB::edit, "T2");
        Thread t3 = new Thread(docC::edit, "T3");

        t1.start();
        t2.start();
        t3.start();
    }
}
