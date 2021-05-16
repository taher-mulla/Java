//this is a faulty program it will not run

public class Main {

    public static void main(String[] args) {

        //WE WERE UPDATING THE WRONG VARIABLE

        StringUtil util = new StringUtil();
        StringBuilder sb = new StringBuilder();

        while(sb.length()<10){
            util.addChar(sb,'a');
        }
        System.out.println(sb);

    }
}
