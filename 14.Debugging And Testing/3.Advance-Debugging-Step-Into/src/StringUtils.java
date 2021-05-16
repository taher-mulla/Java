public class StringUtils {

    public String upperAndPrefix(String str){
        return "Prefix__"+str.toUpperCase();
    }

    //addSuffix is called first in the call statements
    public String addSuffix(String str){
        return str+"__Suffix";
    }
}
