

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Document {
    private final int id;
    private final String name;

    private final List<Document> relatedDocs = new ArrayList<>();

    public Document(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addRelatedDocument(Document doc) {
        relatedDocs.add(doc);
    }

    public List<Document> getRelatedDocs() {
        return relatedDocs;
    }

    public void edit() {
        // Создаем список всех документов, которые нужно заблокировать (включая текущий)
        List<Document> toLock = new ArrayList<>(relatedDocs);
        toLock.add(this);

        // Сортируем по id, чтобы все потоки захватывали блокировки в одинаковом порядке
        toLock.sort(Comparator.comparingInt(Document::getId));

        // Теперь захватываем все блокировки в правильном порядке
        lockDocuments(toLock, 0);
    }

    // Рекурсивно захватываем все блокировки по одной
    private void lockDocuments(List<Document> docs, int index) {
        if (index == docs.size()) {
            // Все блокировки получены — можно редактировать
            doEdit();
            return;
        }

        synchronized (docs.get(index)) {
            lockDocuments(docs, index + 1);
        }
    }

    // Собственно логика редактирования
    private void doEdit() {

        System.out.println(Thread.currentThread().getName() + " редактирует документ " + name);

        for (Document doc : relatedDocs) {

            System.out.println(Thread.currentThread().getName() + " редактирует связанный документ " + doc.name);
            // Здесь может быть логика реального редактирования
        }
    }
}


