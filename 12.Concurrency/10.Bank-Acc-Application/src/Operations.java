public class Operations implements Runnable{

    BankAccount account;
    String colors;

    public Operations(BankAccount account, String colors) {
        this.account = account;
        this.colors = colors;
    }

    @Override
    public void run() {

        account.deposit(300);
        System.out.println(colors+ "Implements -- r1 post deposit : "+account.getBallance());
        account.withdraw(50);
        System.out.println(colors+"Implements -- r1 post withdraw :"+account.getBallance());
    }
}
