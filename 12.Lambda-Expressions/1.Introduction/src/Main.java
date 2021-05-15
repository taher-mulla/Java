import javax.net.ssl.CertPathTrustManagerParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //normal way to write a thread and execute code
        new Thread((new CodeToRun())).start();

        //you can also use a lambda expression like this
        new Thread(()-> System.out.println("Printing from runnable in lambda")).start();
        //lambda expressions have 3 parts Argument List, Arrow token and Body

        //adding multiple lines to a lambda expressions
        new Thread(()-> {
            System.out.println("Printing from runnable in lambda");
            System.out.println("Printing line no 2 from runnable in lambda");
            System.out.println("Printing line no 3 from runnable in lambda");
        }).start();

        Employee jhon = new Employee("Jhon Doe", 30);
        Employee taher =  new Employee("Taher M",39);
        Employee sohail = new Employee("Sohail M",24);
        Employee suraj = new Employee("Suraj K", 32);

        List<Employee> employees = new ArrayList<>();
        employees.add(jhon);
        employees.add(taher);
        employees.add(sohail);
        employees.add(suraj);

        //we will look at ways to sort this array
        //first the non-lambda expression way
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        for(Employee em: employees){
            System.out.println(em.getName());
        }

        //now we will sort using the lambda method          the lambda has been passed as the second variable
        Collections.sort(employees, (Employee emp1, Employee emp2) -> emp1.getName().compareTo(emp2.getName()));

        //you can also write like this, the expression will know the variable type because of 'employees'
        //Collections.sort(employees, (emp1, emp2) -> emp1.getName().compareTo(emp2.getName()));

        System.out.println("Sorted with Lambda");
        for(Employee em: employees){
            System.out.println(em.getName());
        }

        //*****Using lambda in a runnable
        //the 'int num' should ideally be declared as 'final' as it should not change during the thread lifetime,
        //and if not final it ay change
        int num = 24;
        //the '()' are empty as there is no value to be passed
        Runnable r =() -> {
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("We are in the Runnable - The value is = "+num);
        };
        new Thread(r).start();

        //another example of lambda expressions
        Employee em = employees.get(2);
        new Thread( () -> System.out.println(em.getAge()+" is the age of "+em.getName()) ).start();

        //printing the name and age
        employees.forEach(employee -> {
            System.out.println(employee.getAge());
            System.out.println(employee.getName());;
        });

        //more example of lambda expressions
        //printing names of whose age is greater than 30
        employees.forEach(employee -> {
            if(employee.getAge()>30)
                System.out.println(employee.getName()+" is older than 30 years");
        });

    }
}

class CodeToRun implements Runnable{
    @Override
    public void run() {

        System.out.println("Printing from runnable");

    }
}

class Employee{
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