import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //******** Validating a string ********
        System.out.println("Validating a string");
        String s1 = "I want a Bike";
        System.out.println(s1.matches("I want a Bike"));

        //******** Validating a type of string ********
        System.out.println("\nValidating a type of string (I want a ....)");
        String s2 ="I want a Ball";
        //Method 1
        System.out.println(s1.matches("I want a \\w+."));
        //Method 2
        System.out.println(s2.matches("I want a (Bike|Ball)"));

        //******** Creating a Regular Expression and putting it in a Pattern ********
        System.out.println("\nCreating a Regular Expression and putting it in a Pattern");
        String sp1 = "I want a \\w+."; //The regular expressions
        Pattern p1 = Pattern.compile(sp1);  //Putting the expression in the patters

        Matcher m1 = p1.matcher(s1);   //matching that pattern with the string
        System.out.println(m1.matches());

        Matcher m2 = p1.matcher(s2);
        System.out.println(m2.matches());

        //******** Replacing all occurrences of a blank with a '_' ********
        System.out.println("\nReplacing all occurrences of a blank with a '_'");
        String s3 = "Replace all blanks for me";
        //Method 1
        System.out.println(s3.replaceAll(" ", "_"));
        //Method 2
        System.out.println(s3.replaceAll("\\s","_"));

        //******** Match a string ********
        System.out.println("\nMatch a string");
        String s4 = "aaabssssswwwwkkkk";
        //Method 1 -- matching all alphabets
        System.out.println(s4.matches("[a-z]+"));
        //Method 2 -- Matching only the string
        System.out.println(s4.matches("^a{3}bs{5}w{4}k{4}$"));

        //******* Test a string having alphabets.numbers *********
        System.out.println("\nTest a string having alphabets.numbers");
        String s5 = "sdfe.23";
        String s6 = "2sad.232";
        String s7 = "asdeds.234w3";
        String s8 = "asdsawf.32521";

        System.out.println(s5.matches("^[a-z]+[\\.][0-9]+$"));
        System.out.println(s6.matches("^[a-z]+[\\.][0-9]+$"));
        System.out.println(s7.matches("^[a-z]+[\\.][0-9]+$"));
        System.out.println(s8.matches("^[a-z]+[\\.][0-9]+$"));

        //****** using a group print the numbers after the '.' ******
        System.out.println("\nusing a group print the numbers after the '.'");
        String s9 = "asds.1232sad.31253asd.4362dsaf.806";
        Pattern p2 = Pattern.compile("[a-z]+[\\.]([0-9]+)");  //the '()' is group 1
        Matcher m3 = p2.matcher(s9);
        while(m3.find()){
            System.out.println(m3.group(1));
        }


        //doing the same with a few wide spaces
        System.out.println("\ndoing the same with a few wide spaces");
        String s10 = "asds.1232\tsad.31253\tasd.4362\ndsaf.806\n";
        //you can also use this ""[a-z]+[\.]([0-9]+)|[\n|\t]"
        Pattern p3 = Pattern.compile("[a-z]+[\\.]([0-9]+)\\s");  //the '()' is group 1
        Matcher m4 = p3.matcher(s10);
        while(m4.find()){
            System.out.println(m4.group(1));
        }

        //printing the start and end indices
        System.out.println("printing the start and end indices");
        m4.reset();
        while(m4.find()){
            //note the op of group 1 is printed here
            System.out.println(m4);
            System.out.println("Start at : "+ m4.start(1)+"  End at : "+m4.end(1));
        }


        //******* Printing the values in the braces ******
        System.out.println("\nPrinting the values in the braces");
        String s11 = "{2,24},{53,5},{6,54},{55,7},{9,0}";
        //you can us "\\{(.+?)\\}" the '?' keeps it as lazy
        Pattern p4 = Pattern.compile("\\{([0-9]+[\\,][0-9]+)\\}");
        Matcher m5 = p4.matcher(s11);
        while (m5.find()){
            System.out.println(m5.group(1));
        }

    }
}
