package modules.people;

import java.util.HashMap;
import java.util.Map;

public class People {
    private Map<String, Person> people;

    public People() {
        this.people = new HashMap<>();
    }

    public void add(Person person) {
        if (person == null) throw new IllegalArgumentException("person cannot be null");
        if (people.containsKey(person.getRut())) {
            throw new IllegalArgumentException("person with RUT: " + person.getRut() + ". Already exists");
        }

        people.put(person.getRut(), person);
    }

    public Person get(String rut) {
        return people.get(rut);
    }

    public void remove(String rut) {
        people.remove(rut);
    }

    public boolean contains(String rut) {
        return people.containsKey(rut);
    }

    public boolean isEmpty() {
        return people.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Person person : people.values()) {
            sb.append(person.toString()).append("\n---\n");
        }

        return sb.toString();
    }
}
