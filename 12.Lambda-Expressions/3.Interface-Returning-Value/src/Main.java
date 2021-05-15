import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SplittableRandom;

public class Main {

    public static void main(String[] args) {

        Employee jhon = new Employee("Jhon Doe", 30);
        Employee taher =  new Employee("Taher M",23);
        Employee sohail = new Employee("Sohail M",24);
        Employee suraj = new Employee("Suraj K", 21);

        List<Employee> employees = new ArrayList<>();
        employees.add(jhon);
        employees.add(taher);
        employees.add(sohail);
        employees.add(suraj);

        //****** FIRST WE ARE GOING TO DO THIS USING AN ANONYMOUS CLASS **********
        //calling class 'stringStuff' and passing the UpperConcat obj, string 1 and string 2 to it
        //this also is the definition/body of the 'UpperConcat' function
        String anonymousString = stringStuff(new UpperConcat() {
            //this is the 'UpperConcat' method body
            @Override
            public String upperAnsConcat(String s1, String s2) {
                return s1.toUpperCase() + " , " + s2.toUpperCase();
            }
        },
                employees.get(0).getName(), employees.get(1).getName());

        System.out.println("anonymous class = "+anonymousString);

        //******LAMBDA EXPRESSION***********
        //when a lambda expression is just one line, the 'return' keyword is not needed it is put by the compiler by default
        UpperConcat us = (String s1, String s2) -> s1.toUpperCase() + " , " + s2.toUpperCase();
        String lambdaString = stringStuff(us, employees.get(0).getName(), employees.get(1).getName());
        System.out.println("Lambda Expression = "+lambdaString);

        //we will now try a lambda expression with multiple lines of code, it will show us the use of 'return'
        UpperConcat us1 = (String s1, String s2) -> {
            String result = s1.toUpperCase() + " , " + s2.toUpperCase();
            return result;
        };
        String lambdaMultipleLinesString = stringStuff(us, employees.get(0).getName(), employees.get(1).getName());
        System.out.println("Lambda Expression with multiple lines = "+lambdaMultipleLinesString);

        //using a non-Static class 'AnotherClass' ANONYMOUS class
        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.anonymousDoSomething();
        System.out.println("non-Static class (ANONYMOUS)= "+s);

        //using a non-Static class 'AnotherClass' Lambda Expression
        String s1 = anotherClass.lambdaDoSomething();
        System.out.println("non-Static class (LAMBDA)= "+s1);


    }

    //body of 'stringStuff' function
    public final static String stringStuff(UpperConcat uc, String s1, String s2){
        return uc.upperAnsConcat(s1, s2);
    }
}

interface UpperConcat{
    public String upperAnsConcat(String s1, String s2);
}


//looking at non-Static classes to better understand Lambda Expressions
class AnotherClass{

    //ANONYMOUS class
    public String anonymousDoSomething(){
        System.out.println("The AnotherClass class's name is = "+getClass().getSimpleName());
        return Main.stringStuff(new UpperConcat() {
            @Override
            public String upperAnsConcat(String s1, String s2) {
                System.out.println("In the AnotherClass in anonymousDoSomething class, its class's name is = "+getClass().getSimpleName());
                return s1.toUpperCase() + " , " + s2.toUpperCase();
            }
        }, "String1","String 2");
    }

    //Lambda expression
    public String lambdaDoSomething(){
        System.out.println("The AnotherClass class's name is = "+getClass().getSimpleName());
        UpperConcat uc = (s1, s2) -> {
            //this is just a nested block and hence the class name will show as 'AnotherClass'
            System.out.println("In the AnotherClass  in lambdaDoSomething  class, its class's name is = "+getClass().getSimpleName());
            String result = s1.toUpperCase() + " , " + s2.toUpperCase();
            return result;
        };
        return Main.stringStuff(uc,"String1","String 2");
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
