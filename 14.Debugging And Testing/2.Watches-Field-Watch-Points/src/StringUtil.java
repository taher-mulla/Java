public class StringUtil {
    private StringBuilder sBuilder = new StringBuilder();
    //here we are setting a watchers, this will stop at every point the variable is changes
    //we can add this to be deleted on first hit also
    private int charAdded = 0;

    public void  addChar(StringBuilder sBuilder, char c){
        //this line has not been executed because of the breakpoint
        //variable c has been given value of a and 97 is its ascii, Also the sBuilder="" is empty
//        this.sBuilder.append(c);
        sBuilder.append(c);
        charAdded++;
    }
}
