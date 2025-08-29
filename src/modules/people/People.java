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
        if (this.people.containsKey(person.getRut())) {
            throw new IllegalArgumentException("person with RUT: " + person.getRut() + ". Already exists");
        }

        this.people.put(person.getRut(), person);
    }

    public Person get(String rut) {
        return this.people.get(rut);
    }

    public void remove(String rut) {
        this.people.remove(rut);
    }

    public boolean contains(String rut) {
        return this.people.containsKey(rut);
    }

    public boolean isEmpty() {
        return this.people.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Person person : this.people.values()) {
            sb.append(person.toString()).append("\n---\n");
        }

        return sb.toString();
    }
}
