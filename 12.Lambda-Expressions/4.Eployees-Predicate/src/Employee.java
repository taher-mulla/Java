public class Employee{
    private String name;
    private int age;

    //constructor for the class
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //getters and setters for the variables
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
