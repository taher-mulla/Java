package bankacc;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		AccountFunctions accF=new AccountFunctions();
		
		System.out.println("Pls enter your name: ");
		String name = scan.nextLine();
		System.out.println("Pls enter your email: ");
		String email = scan.nextLine();
		System.out.println("Pls enter your account number: ");
		int accNum = scan.nextInt();
		System.out.println("Pls enter your account phone num: ");
		int phoneNum = scan.nextInt();
		
		accF.setAccNumber(accNum);
		accF.setAccPhoneNum(phoneNum);
		accF.setAccName(name);
		accF.setAccEmail(email);
		
		int i=1;
		while(i==1)
		{
			System.out.println("Pls enter amount to add to account");
			int amt = accF.putFunds( scan.nextInt() );
			System.out.println("our ballance is : "+amt);
			
			System.out.println("Pls enter amount to remove from account");
			amt = accF.getFunds( scan.nextInt() );
			System.out.println("our ballance is : "+amt);
			
			
			System.out.println("Pls enter '1' to continue");
			i = scan.nextInt();
		}
		
		
	}
}
