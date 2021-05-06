import java.util.ArrayList;

public class Customers {
    private ArrayList<Double> transactions = new ArrayList<Double>();
    private String customerName;
    private int currentAmount = 0;

    public Customers(String customerName, int currentAmount) {
        this.customerName = customerName;
        this.currentAmount = currentAmount;
    }

    public void showTransactions(){
        for(int i=0;i< transactions.size();i++){
            System.out.println("\t"+(i+1)+". "+transactions.get(i));
        }
    }

    public void setTransactions(double amt){
        System.out.println("Adding transaction");
        transactions.add(amt);
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }
}
