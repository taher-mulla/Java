public class feetInchMethodOver
{


	public static void main(String[] args)
	{	
		int inches=1269;
		calFtInToCm(inches);
	}

	public static double calFtInToCm(int feet, int inches)
	{
		if (feet>=0 && inches>=0 && inches<=12)
		{
			double cm = (feet*30.48D) + (inches*2.54D);
			return cm;
		}
		else
			return -1;
	}

	public static int calFtInToCm(int inches)
	{

		if(inches>=0)
		{
			int onlyInches = inches%12;
			inches -=onlyInches;
			int feet = inches/12;
			double cm = calFtInToCm(feet,onlyInches);
			System.out.println("The returned cm val is = "+cm);
			return 1;
		}
		else 
			return -1;	
	}
	
}
