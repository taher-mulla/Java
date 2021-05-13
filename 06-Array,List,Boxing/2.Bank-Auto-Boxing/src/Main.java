import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        Bank bank = new Bank();
        int option = 0;
        while (option!= 4){
        System.out.println("Options : \n" +
                "1: Adding a branch\n" +
                "2: Accessing a branch\n" +
                "3: View all branches\n" +
                "4: Exit");
        option = scan.nextInt();
        scan.nextLine();


        if (option == 1) {
            System.out.println("Pls enter the bank branch name");
            String name = scan.nextLine();
            bank.addBranch(name);
        } else if (option == 2) {
            System.out.println("Pls enter the bank branch name");
            String name = scan.nextLine();
            bank.accessBranch(name);
        } else if(option == 3){
            bank.viewBranches();
        }else
            break;
        }

    }
}
