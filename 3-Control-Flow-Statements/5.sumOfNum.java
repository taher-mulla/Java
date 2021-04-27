import java.util.Scanner;

public class sumOfNum
{
	public static void main(String[] args)
	{
		int counter=1, sum=0;
		Scanner scan = new Scanner(System.in);
		while(counter<6)
		{
			System.out.println("Pls enter a nuber");
			if(scan.hasNextInt())
			{
				sum += scan.nextInt();
				counter++;
			}
			else
			{
				System.out.println("Not a number");
				scan.nextLine();
			}
			
			System.out.println("The sum at this stage is: "+sum);
		}
		
		System.out.println("The sum of all numbers is = "+sum);
		scan.close();
	}
}
