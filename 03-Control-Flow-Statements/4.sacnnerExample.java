import java.util.Scanner;

public class scannerExample
{
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("What is your name? ");
		String name=scan.nextLine();
		//scan.close();
		System.out.println("Your name is : "+name);
		System.out.println("What year were you born in?");
		boolean hasNextInt = scan.hasNextInt();
		if(hasNextInt)
		{
			int bornYear=scan.nextInt();
			System.out.println("Your birth year was : "+bornYear);
			System.out.println("You are " +(bornYear-2021)+ " years old currently");
		}
		else
			System.out.println("Age not Int");
	}
}
