//Predicates and Suppliers and Consumers and allot more see below

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Employee jhon = new Employee("Jhon Doe", 30);
        Employee taher =  new Employee("Taher Mass",39);
        Employee sohail = new Employee("Sohail Mgdsgv",24);
        Employee suraj = new Employee("Suraj Ksgdd", 32);

        List<Employee> employees = new ArrayList<>();
        employees.add(jhon);
        employees.add(taher);
        employees.add(sohail);
        employees.add(suraj);

        //using a lambda expression for a predicate
        printEmployeesByAge(employees, "Employees over 30 -- using a lambda expression", employee -> employee.getAge()>30);
        printEmployeesByAge(employees, "Employees under 30 -- using a lambda expression", employee -> employee.getAge()<=30);

        //using a anonymous class for a predicate
        printEmployeesByAge(employees
                ,"Employees younger than 25 -- using a anonymous class"
                ,new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge()<25;
            }
        });


        //***********Supplier Example************
        System.out.println("***********Supplier Example************");

        //to get 10 numbers using for loop
        System.out.println("Using for loop");
        for(int i=0;i<10;i++){
            System.out.println(new Random().nextInt(100));
        }

        //using a supplier
        System.out.println("Using supplier");
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(100);
        for(int i=0;i<10;i++){
            System.out.println(randomSupplier.get());
        }


        //using a function to get the last names with a lambda expression
        //Function<AA, BB> ---- AA= the thing passed to it & BB= the return type
        Function<Employee, String> getLastNameFunction = (Employee employee) ->{
            return employee.getName().substring(employee.getName().indexOf(' ')+1);
        };
        String lastName = getLastNameFunction.apply(employees.get(2));
        System.out.println("Using a function to get last name = "+lastName);

        //to get first name name
        Function<Employee, String> getFirstNameFunction = (Employee employee) ->{
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        System.out.println("Using a function to get first or last names");
        System.out.println("First name of [2] = " +getName(getFirstNameFunction, employees.get(2)));
        System.out.println("Last name of [1] = " +getName(getLastNameFunction, employees.get(1)));

        //*********Chaining functions***************
        System.out.println("********* Chaining functions ***************");
        Function<Employee, String > upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainFunction = upperCase.andThen(firstName);
        System.out.println("Chaining of function = "+chainFunction.apply(employees.get(2)));

        //passing 2 values to the function *** BIFunction ***
        System.out.println("*** BIFunction ***");
        BiFunction<String, Employee, String > nameAndAge = (String name, Employee employee) ->{
            return name.concat(" "+employee.getAge());
        };
        //using a BiFunction you cannot use Function Chaining
        String upperName = upperCase.apply(employees.get(0));
        System.out.println("BIFunction = " +nameAndAge.apply(upperName, employees.get(0)));

        //********** Unary Operator **********
        IntUnaryOperator uniOp = i -> i+5;
        System.out.println("Unary Operator = "+uniOp.applyAsInt(10));


        //*********** Consumer **************
        System.out.println("*********** Consumer **************");
        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hellow World");

    }


    private static String getName(Function<Employee, String> getName, Employee employee){
        return getName.apply(employee);
    }

    //using a predicate
    //if we want to use the sae code to get greater than or less than, we can use a bool variable to do so
    //here will do the same using a predicate
    private static void printEmployeesByAge(List<Employee> employees, String ageText,
                                            Predicate<Employee> ageCondition){
        //printing names of whose age is greater than 30
        //Lambda expression
//        employees.forEach(employee -> {
//            if(employee.getAge()>30)
//                System.out.println(employee.getName()+" is older than 30 years");
//        });

        //using the predicate
        System.out.println("************** "+ ageText +" **************");
        for (Employee employee : employees) {
            if(ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }

        //there are other(specific) types of consumers and predicates
        //eg, double or int Consumer and int, double and long predicate
        //using an Integer Predicate
        System.out.println("********** Predicates *************");
        IntPredicate intP = i -> i>15;
        System.out.println("IntPredicate = "+intP.test(10));
        int a=20;
        System.out.println("IntPredicate = "+intP.test(a));


        IntPredicate greaterThanFifteen = i -> i>15;
        IntPredicate lessThanHundred = i -> i<100;
        System.out.println("2 Integer Predicates = "+greaterThanFifteen.and(lessThanHundred).test(92));
        System.out.println("2 Integer Predicates = "+greaterThanFifteen.and(lessThanHundred).test(10));
        System.out.println("2 Integer Predicates = "+greaterThanFifteen.and(lessThanHundred).test(109));



    }
}
