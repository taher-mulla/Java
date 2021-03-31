
/********************************************Character Variable********************************************/

//	Char variables are kept in unicode for, hence you can use this to inatilize the variable

public class charBool{
	public static void main(String[] args){
		char myChar = 'D';
		char myUnicode = '\u0044';	//Char is beeing stored is 'D'
		System.out.println("My Character is = " + myChar);
		System.out.println("My Unicode Char 0044 = " + myUnicode);
		char myUnicode1 = '\u00A9';	//this code gives a sybol
		System.out.println("My Unicode Char 1 00A9 = " + myUnicode1);	
		
	}
}
