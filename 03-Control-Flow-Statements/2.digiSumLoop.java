public class digiSum
{
	public static void main(String[] args)
	{
		int digit,sum=0;
		int num=21;
		
		while(num!=0)
		{
			digit = num%10;
			num /= 10;
			sum = sum+digit;
		}
		System.out.println("The sum is = " +sum);
	}
}
