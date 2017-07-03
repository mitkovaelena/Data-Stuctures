package organisation;

import java.util.*;

public class Organization implements IOrganization {

    private Map<String, List<Person>> personsByName;
    private List<Person> personsByInsertOrder;
    private SortedMap<Integer, List<Person>> personsByNameLenght;

    public Organization() {
        this.personsByName = new HashMap<>();
        this.personsByInsertOrder = new LinkedList<>();
        this.personsByNameLenght = new TreeMap<>();
    }

    @Override
    public int getCount() {
        return this.personsByInsertOrder.size();
    }

    @Override
    public boolean contains(Person person) {
        return this.containsByName(person.getName()) && this.personsByName.get(person.getName()).contains(person);
    }

    @Override
    public boolean containsByName(String name) {
        return this.personsByName.containsKey(name);
    }

    @Override
    public void add(Person person) {
        this.personsByName.putIfAbsent(person.getName(), new ArrayList<>());
        this.personsByName.get(person.getName()).add(person);

        this.personsByInsertOrder.add(person);

        this.personsByNameLenght.putIfAbsent(person.getName().length(), new ArrayList<>());
        this.personsByNameLenght.get(person.getName().length()).add(person);
    }

    @Override
    public Person getAtIndex(int index) {
        if(this.personsByInsertOrder.size() <= index){
            throw new IllegalArgumentException();
        }
        return this.personsByInsertOrder.get(index);
    }

    @Override
    public Iterable<Person> getByName(String name) {
        if(!this.personsByName.containsKey(name)){
            return new ArrayList<>();
        }
        return this.personsByName.get(name);
    }

    @Override
    public Iterable<Person> firstByInsertOrder() {
        if(this.personsByInsertOrder.isEmpty()){
            return new ArrayList<>();
        }
        return this.personsByInsertOrder.subList(0,1);
    }

    @Override
    public Iterable<Person> firstByInsertOrder(int count) {
        if(this.personsByInsertOrder.size() < count){
            count = this.personsByInsertOrder.size();
        }
        return this.personsByInsertOrder.subList(0, count);
    }

    @Override
    public Iterable<Person> searchWithNameSize(int minLength, int maxLength) {
        List<Person> outputList =  new LinkedList<>();
        for(List<Person> personList: this.personsByNameLenght.subMap(minLength, maxLength).values()){
            outputList.addAll(personList);
        }
        return outputList;
    }

    @Override
    public Iterable<Person> getWithNameSize(int length) {
        if(!this.personsByNameLenght.containsKey(length)){
            throw new IllegalArgumentException();
        }
        return this.personsByNameLenght.get(length);
    }

    @Override
    public Iterable<Person> peopleByInsertOrder() {
        return this.personsByInsertOrder;
    }

    @Override
    public Iterator<Person> iterator() {
        return this.personsByInsertOrder.iterator();
    }
}
