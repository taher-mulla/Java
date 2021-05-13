
/************************************CASTING************************************/
/*	Simply put casting in Java is you telling the compilet that the given data type of of a certain form
	or you forcefully converting the data type
	example: casting a float or double to an int
*/

public class casting{
	public static void main(String[] args){
		int myInt = Integer.MIN_VALUE;
		byte myByte = Byte.MIN_VALUE;
		short myShort = Short.MIN_VALUE;
		
		int myNewIntInt = (myInt / 2);
		int myNewIntByte = (myByte / 2);
		int myNewIntShort = (myShort / 2);		
		System.out.println("Div Int Int = " + myNewIntInt);
		System.out.println("Div Int Byte = " + myNewIntByte);
		System.out.println("Div Int Short = " + myNewIntShort);
		
//This CASTING needs to be done cause	java by default makes the byte  short to integers on division
//We gotta tell java that the are not int and to keep them as short and byte	
		
		int myNewInt = (myInt / 2);			//Casting
		byte myNewByte = (byte)(myByte / 2);
		short myNewShort = (short) (myShort / 2);
		System.out.println("\nDiv Int Int = " + myNewInt);
		System.out.println("Div Byte Byte = " + myNewByte);
		System.out.println("Div Short Short = " + myNewShort);
	}
}
