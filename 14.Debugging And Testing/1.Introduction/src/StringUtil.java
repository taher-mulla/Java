public class StringUtil {
    private StringBuilder sBuilder = new StringBuilder();
    private int charAdded = 0;

    public void  addChar(StringBuilder sBuilder, char c){
        //this line has not been executed because of the breakpoint
        //variable c has been given value of a and 97 is its ascii, Also the sBuilder="" is empty
//        this.sBuilder.append(c);
        sBuilder.append(c);
        charAdded++;
    }
}
