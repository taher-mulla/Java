import java.util.Scanner;

public class Mobile {
    private Scanner scan = new Scanner(System.in);
    private Contacts contacts = new Contacts();


    public void options(){

        while(true){
            System.out.println("\n\nPls Enter your choice ::");
            System.out.println("1. Display Contacts");
            System.out.println("2. Enter New contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. View number by name");
            System.out.println("5. Exit");
            int option = scan.nextInt();
            if(option == 5)
                break;
            scan.nextLine();
            //passing the choice to the contacts class
            contacts.functions(option);
        }
    }
}
