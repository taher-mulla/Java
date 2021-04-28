package carModle;

import java.util.Scanner;

public class main
{
	public static void main(String[] args)
	{
		//CREATING A CLASS OBJECT
		mod mod=new mod();
		Scanner scan= new Scanner(System.in);
			
		System.out.println("Pls enter the car number");
		int number = scan.nextInt();
		scan.nextLine();
		
		mod.setNumber(number);
		
		System.out.println("The number entered was "+mod.getNumber() );
	}
}
