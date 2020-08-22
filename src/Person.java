public class Person {
    private String id;
    private String name;
    private String dept;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public Person(String id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }
}