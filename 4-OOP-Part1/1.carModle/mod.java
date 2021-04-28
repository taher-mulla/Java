package carModle;

public class mod
{
	private int number;
	private String mod; 
	
	public void setNumber(int number)
	{
		if(number>10 && number<100)
			this.number=number;	//"this.number" points to the field "number" declared in the class, 
						//and "number" is the variable declared in the method
		else
			System.out.println("Invalid nuber");
	}
	
	public int getNumber()
	{
		return this.number;
	}
}
