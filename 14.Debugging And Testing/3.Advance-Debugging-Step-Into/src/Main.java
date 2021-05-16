public class Main {

    public static void main(String[] args) {

        StringUtils utils = new StringUtils();
        String str = "cntdhxas";
        //here we may want to go to a specific method and can use smart step-in
        String result = utils.upperAndPrefix(utils.addSuffix(str));
        System.out.println(result);

        //you can also change the value of a  variable while at a breakpoint in debugging
    }
}
