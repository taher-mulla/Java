public class Operations2 implements Runnable {

    BankAccount account;
    String colors;

    public Operations2(BankAccount account, String colors) {
        this.account = account;
        this.colors = colors;
    }

    @Override
    public void run() {

        account.deposit(203.75);
        System.out.println(colors+ "Implements -- r2 post deposit : " + account.getBallance());
        account.withdraw(100);
        System.out.println(colors+ "Implements -- r2 post withdraw :" + account.getBallance());
    }
}

