import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //********* AND **********
        //When we use "abc" this is the AND operator we are writing "a"&"b"&"c"

        //********* OR ***********
        System.out.println("********* OR ***********");
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));

        //********* NOT **********
        // 1
        // '^' when in '[]' is the first char, it is the NOT char
        //eg: [^abc] --- this is NOT abc
        // 2
        //'!' is also a NOT operator but needs to be used in a Negative Lookahead Expression
        System.out.println("\n********* NOT **********");
        String tTest = "tstvtdythat";

        //using '^'
        System.out.println("using '^'");
        String TTestReguExp = "t[^v]"; //every 't' not followed by a 'v'
        Pattern tTestPattern = Pattern.compile(TTestReguExp);
        Matcher tTestMatcher = tTestPattern.matcher(tTest);

        //the last 't' is not printed because there is no char ahead of it
        int count =0;
        while(tTestMatcher.find()){
            System.out.println("Occurrence: "+count+" : "+tTestMatcher.start()+" to "+tTestMatcher.end());
            count++;
        }


        //using '!'
        System.out.println("using Negative Lookahead '?!'");
        //negative Look Ahead -- it will look for t then no v but will include the v in the match
        String TTestReguExp1 = "t(?!v)";    //'?' is part of the Look Ahead syntax....Look Ahead start with a (?
        Pattern tTestPattern1 = Pattern.compile(TTestReguExp1);
        Matcher tTestMatcher1 = tTestPattern1.matcher(tTest);

        //the last 't' is not printed because there is no char ahead of it
        count =0;
        while(tTestMatcher1.find()){
            System.out.println("Occurrence: "+count+" : "+tTestMatcher1.start()+" to "+tTestMatcher1.end());
            count++;
        }

        //positive Look Ahead -- it will look for t then no v but will not include the v in the match
        System.out.println("using Positive Lookahead '?='");
        String TTestReguExp1Positive = "t(?=v)";    //'=' is part of the Look Ahead syntax....Look Ahead start with a (?=
        Pattern tTestPattern1Positive = Pattern.compile(TTestReguExp1Positive);
        Matcher tTestMatcher1Positive = tTestPattern1Positive.matcher(tTest);

        //the last 't' is not printed because there is no char ahead of it
        count =0;
        while(tTestMatcher1Positive.find()){
            System.out.println("Occurrence: "+count+" : "+tTestMatcher1Positive.start()+" to "+tTestMatcher1Positive.end());
            count++;
        }

        //**** Checking a phone number ****
        //the regular expressions = "^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$""
        //Sample num -- (092) 345-1254
        System.out.println("**** Checking a phone number ****");
        String phone1 = "12345678";
        String phone2 = "(123) 345-2134";
        String phone3 = "123 123-3214";
        String phone4 = "(123)453-1241";

        System.out.println("Phone1 = "+phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("Phone2 = "+phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("Phone3 = "+phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("Phone4 = "+phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
    }
}
