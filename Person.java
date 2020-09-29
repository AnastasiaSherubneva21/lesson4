package lesson4;

public class Person {

    protected String name;
    protected String bd;

    public Person(Person ob) {
        name = ob.name;
        bd = ob.bd;
    }

    public Person(String persname, String persbd) {
        bd = persbd;
        name = persname;
    }

    public String str() {
        return (name + " " + bd);
    }
}
