import java.util.ArrayList;
import java.util.Scanner;

public class Contacts {
    Scanner scan = new Scanner(System.in);
    
    //ArrayList declaration, this list will hold data of type String 
    private ArrayList<String> names = new ArrayList<String>();
        //This list will hold data of type Integer 
    private ArrayList<Integer> numbers = new ArrayList<Integer>();

    public Contacts() {
        names.add("taher");
        numbers.add(1232131);
        names.add("sohail");
        numbers.add(9999931);
        names.add("yash");
        numbers.add(000000);
    }

    public void functions(int options){
        switch (options){
            case 1:
                displayContact();
                break;
            case 2:
                enterContact();
                break;
            case 3:
                deleteContact();
                break;
            case 4:
                viewContact();
                break;
            default:
                System.out.println("Wrong input\nPls re-enter");
                break;
        }
    }

    private void displayContact(){
        for(int i=0;i< names.size();i++){
            System.out.println((i+1)+". "+names.get(i)+"\t"+numbers.get(i));
        }
    }

    private void enterContact(){
        System.out.println("Pls enter a name: ");
        String name = scan.nextLine();
        names.add(name);
        System.out.println("Enter the phone number: ");
        int number = scan.nextInt();
        scan.nextLine();
        numbers.add(number);
    }

    private void deleteContact(){
    
    		//This code is for deleting the contact using Serial number
        /*
        System.out.println("Pls enter the Sr number of contact to be removed");
        int temp = scan.nextInt();
        scan.nextLine();
        names.remove(temp-1);
        numbers.remove(temp-1);
        displayContact();*/

        int temp;
        System.out.println("Pls enter a name: ");
        String name = scan.nextLine();
        temp = names.indexOf(name);
        //name removed
        names.remove(temp);
        //number removed
        numbers.remove(temp);

    }

    private void viewContact(){
        System.out.println("Pls enter a name: ");
        String name = scan.nextLine();
        int temp = names.indexOf(name);
        System.out.println("The number for this name is: "+numbers.get(temp));
    }

}
