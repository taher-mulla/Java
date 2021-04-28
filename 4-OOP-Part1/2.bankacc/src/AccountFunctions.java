package bankacc;

public class AccountFunctions
{
	private int accNumber, accBallance=0, accPhoneNum;
	private String accName, accEmail;
	
	
	public void setAccNumber(int accNumber) 
	{
		this.accNumber=accNumber;
	}
	public void setAccPhoneNum(int accPhoneNum) 
	{
		this.accPhoneNum=accPhoneNum;
	}
	public void setAccName(String accName) 
	{
		this.accName=accName;
	}
	public void setAccEmail(String accEmail) 
	{
		this.accEmail=accEmail;
	}
	
	public int putFunds(int putAmount)
	{
		if(putAmount>0)
			this.accBallance +=putAmount;
		else
			System.out.println("Number less than 0");
		return this.accBallance;
	}
	public int getFunds(int removeAmount)
	{
		if(this.accBallance >= removeAmount)
			this.accBallance -=removeAmount;
		else
			System.out.println("Not sufficient balance");
		return this.accBallance;
			
	}
}
