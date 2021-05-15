import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Employee jhon = new Employee("Jhon Doe", 30);
        Employee taher =  new Employee("Taher Mass",39);
        Employee sohail = new Employee("Sohail Mgdsgv",24);
        Employee suraj = new Employee("Suraj Ksgdd", 32);

        Department hr = new Department("Human Resources");
        hr.addEmployees(jhon);
        hr.addEmployees(taher);
        hr.addEmployees(suraj);

        Department accounting = new Department("Accounting");
        accounting.addEmployees(sohail);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        //youngest employee using stream and flatMap
        System.out.println("\nUsing stream and flatMap\nyoungest employee = ");
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1,e2) -> e1.getAge() < e2.getAge() ? e1:e2)
                .ifPresent(System.out::println);

        //****
        //Streams will work / execute only if there is a terminating operation at the end

    }
}
