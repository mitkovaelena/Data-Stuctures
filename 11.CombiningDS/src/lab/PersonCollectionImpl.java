package lab;

import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    Map<String, Person> emailPerson;
    Map<String, SortedSet<Person>> domainPerson;
    Map<String, SortedSet<Person>> nameTownPerson;
    SortedMap<Integer, SortedSet<Person>> agePerson;
    SortedMap<String, SortedSet<Person>> ageTownPerson;

    public PersonCollectionImpl() {
        this.emailPerson = new HashMap<>();
        this.domainPerson = new HashMap<>();
        this.nameTownPerson = new HashMap<>();
        this.agePerson = new TreeMap<>();
        this.ageTownPerson = new TreeMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (emailPerson.containsKey(email)) {
            return false;
        }
        Person person = new Person(email, name, age, town);
        String domain = email.substring(email.indexOf("@") + 1);
        String nameTown = name + town;
        String townAge = town + String.valueOf(age);
        SortedSet<Person> sortedSet = new TreeSet<Person>();

        emailPerson.put(email, person);

        sortedSet = domainPerson.getOrDefault(domain,  new TreeSet<Person>());
        sortedSet.add(person);
        domainPerson.put(domain, sortedSet);

        sortedSet = nameTownPerson.getOrDefault(nameTown,  new TreeSet<Person>());
        sortedSet.add(person);
        nameTownPerson.put(nameTown, sortedSet);

        sortedSet = agePerson.getOrDefault(age,  new TreeSet<Person>());
        sortedSet.add(person);
        agePerson.put(age, sortedSet);

        sortedSet = ageTownPerson.getOrDefault(townAge,  new TreeSet<Person>());
        sortedSet.add(person);
        ageTownPerson.put(townAge, sortedSet);

        return true;
    }

    @Override
    public int getCount() {
        return this.emailPerson.size();
    }

    @Override
    public Person findPerson(String email) {
        if (this.emailPerson.containsKey(email)) {
            return this.emailPerson.get(email);
        }
        return null;
    }

    @Override
    public boolean deletePerson(String email) {
        if (!emailPerson.containsKey(email)) {
            return false;
        }
        emailPerson.remove(email);
        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        return domainPerson.getOrDefault(emailDomain, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        String nameTown = name + town;
        return nameTownPerson.getOrDefault(nameTown, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        LinkedList<Person> elements = new LinkedList<>();
        for (SortedSet<Person> sortedList : this.agePerson.subMap(startAge, endAge+1).values()) {
            elements.addAll(sortedList);
        }
        return elements;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        LinkedList<Person> elements = new LinkedList<>();
        String townAge1 = town + String.valueOf(startAge);
        String townAge2 = town + String.valueOf(endAge+1);
        for (SortedSet<Person> sortedList : this.ageTownPerson.subMap(townAge1, townAge2).values()) {
            for (Person person : sortedList) {
                if(person.getAge() >= startAge && person.getAge() <= endAge)
                elements.add(person);
            }
        }
        return elements;
    }
}
