//Regular expressions are usually used with Strings
//To find a substring, validate user ip, etc.

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {



        //**** Replacing all the 'I' with 'You' ****
        System.out.println("\n**** Replacing all the 'I' with 'You' ****");
        String string = "This is a string. I am a human. I a not a rbot";
        System.out.println(string);
        String newString = string.replaceAll("I", "You");
        System.out.println(newString);

        //character class represents a set or class
        //boundary matches look for ends of strings, words etc

        //**** Replacing part of the string with 'Y' ****
        //'^' operator looks at the start of the string therefore the second time it occurs it is  not replaced
        System.out.println("\n**** Replacing part of the string with 'Y' ****");
        String all = "asaeesFHAXSF213421j2SXeIIjiiAasSasFHIXAXiiasjd902174ikeca";
        System.out.println(all);
        System.out.println(all.replaceAll("^*asasFH*","YYYYY"));

        //when using the matches method, the whole string must match not just a substring
        System.out.println(all.matches("^hello"));
        System.out.println(all.matches("^asasFH"));
        System.out.println(all.matches("asasFHASF2134212IIjiiasasFHiiasd902174ikca"));
        System.out.println(all.matches("^asasFHASF2134212IIjiiasasFHiiasd902174ikca"));

        //'$' operator looks at the end of the string
        System.out.println(all.replaceAll("ikca$", "THE END"));

        //'[]' will match and replace all individual letters
        System.out.println(all.replaceAll("[IA]", "-X-"));

        //'[][]' will replace all letters if it is followed by the letter in the second brace
        System.out.println(all.replaceAll("[IAS][Xa]", "-X-"));

        //Changing from Lower Case to Upper Case
        System.out.println("Harry".replaceAll("[Hh]arry", "Harry"));
        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));

        //replacing all letters except few
        System.out.println(all.replaceAll("[^ej]", "X"));

        //replacing all occurrences of a to f and 3 to 8
        System.out.println(all.replaceAll("[abcdef345678]", "X"));
        System.out.println(all.replaceAll("[a-f3-8]", "X"));
        //ignoring case sensitivity
        System.out.println(all.replaceAll("(?i)[a-f3-8]", "X"));

        //replacing all the numbers
        System.out.println(all.replaceAll("[0-9]", "X"));
        System.out.println(all.replaceAll("\\d", "X"));

        //replacing all non-numeric
        System.out.println(all.replaceAll("\\D", "X"));

        //removing wide spaces
        String line = "I have a burger. I will eat this\t tomorrow, because I am not hungry.\n";
        System.out.println(line);
////        System.out.println(hasWhitespace.replaceAll("\\s", ""));
        System.out.println(line.replaceAll("[ \t]", "_"));
        //replacing everything else other than spaces
        System.out.println(line.replaceAll("[\\S]", "_"));

        //'\\w' will match all lower and upper case of letters
        System.out.println(line.replaceAll("\\w","X"));
        System.out.println(line.replaceAll("\\W","X"));

        //'\\b' will detect all the boundaries of words
        System.out.println(line.replaceAll("\\b","X"));




    }
}
