import java.util.ArrayList;
import java.util.Scanner;

public class Branches {

	//this is the branch class and from here we can access various branch customers
    private ArrayList<Customers> customers;
    private String branchName;
    private Scanner scan = new Scanner(System.in);

    public Branches(String branchName) {
        this.branchName = branchName;
        this.customers = new ArrayList<Customers>();
    }

    public void addCustomer(String custName, int amount){
        Customers tempCust = new Customers(custName,amount);
        customers.add(tempCust);
    }

    public void transactions(){
        System.out.println("Enter the customer name");
        String custName = scan.nextLine();
        int index = giveCustomerIndex(custName);
        System.out.println("Enter the transaction");
        double transaction = scan.nextInt();
        scan.nextLine();
        customers.get(index).setTransactions(transaction);
    }

    public void getCustomers(){
        Customers cust;
        for (int i=0;i<customers.size();i++){
            cust=customers.get(i);
            System.out.println((i+1)+". "+cust.getCustomerName()+"\t"+cust.getCurrentAmount());
            cust.showTransactions();
        }
    }

    public String getBranchName() {
        return branchName;
    }

    private int giveCustomerIndex(String custName){
        for(int index=0; index<customers.size();index++){
            if(custName.equals( customers.get(index).getCustomerName()) )
                return index;
        }
        return -1;
    }
}
