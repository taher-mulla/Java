public class BankAccount {
    private double ballance;
    private String accountNumber;

    public BankAccount(double ballance, String accountNumber) {
        this.ballance = ballance;
        this.accountNumber = accountNumber;
    }

    //YOU CAN ALSO 'synchronized' JUST THE BLOCK OF CODE BY USING THE CURRENT OBJECT 'this'
    public void deposit(double amount){
            synchronized (this){
                ballance += amount;
            }
    }

    //YOU CAN 'synchronized' THE FULL FUNCTION
    public synchronized void withdraw(double amount){
        ballance -=amount;
    }

    public double getBallance() {
        return ballance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccNo(){
        System.out.println("Account number = "+accountNumber);
    }
}
