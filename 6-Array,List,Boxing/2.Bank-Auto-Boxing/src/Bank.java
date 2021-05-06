import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

	//This is the Bank class, a bank will have various branches which are operated here
    private ArrayList<Branches> branches;
    private Scanner scan = new Scanner(System.in);
    
    
    public Bank() {
        this.branches = new ArrayList<Branches>();
    }

    public void addBranch(String branchName){
        Branches tempBranch = new Branches(branchName);
        branches.add(tempBranch);
        System.out.println("Branch Added");
    }

    public void accessBranch(String branchName){
        Branches branch = new Branches(branchName);
        int index = giveBranchIndex(branchName);
        System.out.println("index = "+index);
        branch = branches.get(index);

        int options = 100;
        while (options != 4){
        
        //here we can access the branch and view individual branch data
            System.out.println("Options:\n" +
                    "1. Add Customer\n" +
                    "2. View all customers\n" +
                    "3. Add transaction\n" +
                    "4. Exit to main bank");
            options = scan.nextInt();
            scan.nextLine();

            if(options==1){
                System.out.println("Pls enter customer Name");
                String custName = scan.nextLine();
                System.out.println("Pls enter initial amount");
                int amount = scan.nextInt();
                scan.nextLine();
                branch.addCustomer(custName,amount);
            }
            else if(options == 2){
                branch.getCustomers();
            }
            else if (options == 3){
                branch.transactions();
            }else
                break;
        }

    }

    public void viewBranches(){
        for(int i=0;i<branches.size();i++){
            System.out.println((i+1)+". "+branches.get(i).getBranchName());
        }
    }

    private int giveBranchIndex(String brannchName){
        for(int index=0; index<branches.size();index++){
            if(brannchName.equals( branches.get(index).getBranchName()) )
                return index;
        }
        return -1;
    }
}
