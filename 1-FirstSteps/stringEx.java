public class stringEx{
	public static void main(String[] args){
		String myStr = "This is the String";
		System.out.println("The stored string is :: "+myStr);
		myStr = myStr + " + Add this to the String";
		System.out.println("The stored string is :: "+myStr);
		myStr = myStr + " \u00A9";
		System.out.println("The stored string is :: "+myStr);
		
		int iToStr = 10;
		String iPlusStr = "a int is going to be added -- ";
		iPlusStr = iPlusStr + iToStr;
		System.out.println("\nThe int has been added to the string it is :: "+iPlusStr);
	}
}
