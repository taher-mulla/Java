public class Main {

    public static void main(String[] args) {

        StringUtil util = new StringUtil();
        StringBuilder sb = new StringBuilder();

        while(sb.length()<10){
            util.addChar(sb,'a');
        }
        System.out.println(sb);

    }
}
