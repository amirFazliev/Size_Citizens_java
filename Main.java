package hm422Citizen;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> person = persons.stream();
        long countChildren =person
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних (т.е. людей младше 18 лет) - " + countChildren);

        Stream<Person> person1 = persons.stream();
        List<String> familiesPerson = person1
                .filter(x -> x.getAge() < 28)
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getSex().equals(Sex.MAN))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println("Cписок фамилий призывников (т.е. мужчин от 18 и до 27 лет) - " + familiesPerson);

        Stream<Person> person2 = persons.stream();
        List<String> familiesPersonManInHigherEducation = person2
                .filter(x -> x.getAge() < 66)
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .map(x -> x.getFamily())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Oтсортированный по фамилии список потенциально работоспособных мужчин с высшим образованием в выборке (т.е. мужчин с высшим образованием от 18 до 65 лет) - " + familiesPersonManInHigherEducation);

        Stream<Person> person3 = persons.stream();
        List<String> familiesPersonWomanInHigherEducation = person3
                .filter(x -> x.getAge() < 61)
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getSex().equals(Sex.WOMAN))
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .map(x -> x.getFamily())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Oтсортированный по фамилии список потенциально работоспособных женщин с высшим образованием в выборке (т.е. женщин с высшим образованием от 18 до 60 лет) - " + familiesPersonWomanInHigherEducation);

    }
}

enum Sex {
    MAN,
    WOMAN
}

enum Education {
    ELEMENTARY,
    SECONDARY,
    FURTHER,
    HIGHER
}

class Person {
    private String name;
    private String family;
    private Integer age;
    private Sex sex;
    private Education education;

    public Person(String name, String family, int age, Sex sex, Education education) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.sex = sex;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Education getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", education=" + education +
                '}';
    }
}


