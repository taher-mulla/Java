/********************************************Challange Operators********************************************/


public class operators{
	public static void main(String[] args){
		//The number is followed by a 'D' to tell the compiler to consider the number as a double
		double first=20.00D, second=80.00D, sum;	
		sum = (first + second) * 100.00;
		sum %=40.00D;
		System.out.println("Remainder = "+sum);
		
		boolean bool = (sum==0) ? true : false;
		
		System.out.println(bool);
		
		if(!bool)
			System.out.println("Got some remainder");
		
		
	}
}
