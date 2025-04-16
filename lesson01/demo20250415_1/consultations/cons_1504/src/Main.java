import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Person> list = new ArrayList<>();
        list.add(new Person("Jac", 20));
        list.add(new Person("Anna", 40));
        list.add(new Person("Luka", 30));
        list.add(new Person("Paul", 50));
        list.add(new Person("Sofia", 25));


        System.out.println(ifPersonWithNameExists2(list, "Anna")); // true
        System.out.println(ifPersonWithNameExists2(list, "sergei")); // false


    }

    public static boolean ifPersonWithNameExists(List<Person> list, String name) {
        for (Person person : list) {
            if (person.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;


    }public static boolean ifPersonWithNameExists2(List<Person> list, String name) {
        return list.stream()
                .filter(p->p!=null)
                .filter(p -> p.getName().equalsIgnoreCase("Anna"))
                .count() > 0;


    }  public static boolean ifPersonWithNameExists3(List<Person> list, String name) {
        return list.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase("Anna"));
    }


    }
    /*
Дано: список Person
Необходимо: написать метод, который определит, есть ли Person с заданным именем в списке*/


