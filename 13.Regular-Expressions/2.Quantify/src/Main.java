//quantifier, pattern, and matcher
//matcher can be used only 1 time, then you gotta reset

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String all = "asaeeesFHAXSF213421j2SXeIIhhhhhiiijAasSasFhhiiiiiiijasjd902174ikeca";
        System.out.println(all);
        System.out.println(all.replaceAll("^asaeees","XXXXX"));

        //using a quantifier
        System.out.println(all.replaceAll("^asae{3}s", "QQQQQQ"));

        //replace a substring with 'n' Number of e's
        System.out.println(all.replaceAll("^asae+s", "QQQ+++"));

        //replace no matter what is in middle (if 'e' is there or not it wont matter)
        System.out.println(all.replaceAll("^asae*s", "QQQ****"));

        //replace if in middle we have 2 to 5 e's
        System.out.println(all.replaceAll("asae{2,5}s", "Q{}QQ"));

        //replace all occurrence of 'h' followed by any number of 'i' followed by atleast 1 j
        System.out.println("\n"+all);
        //any no of 'h', 0 to any number of 'i', followed by a 'j'
        System.out.println(all.replaceAll("h+i*j", " SOMETHING "));


        //****** Matcher **********
        System.out.println("\n****** Matcher **********");

        StringBuilder htmlCode = new StringBuilder();
        htmlCode.append("<h1>My Heading</h1>");
        htmlCode.append("<h2>Sub-Heading</h2>");
        htmlCode.append("<p>This is the fist para</p>");
        htmlCode.append("<p>This is the second heading</p>");
        htmlCode.append("<h2>Summary</h2>");
        htmlCode.append("<p>The Sumary para</p>");

        //to find a heading in the html code
        System.out.println("to find a heading -- h2 -- in the html code");
        String h2 = ".*<h2>.*";        //the dot considers everything adn the * ignores it
        Pattern pattern = Pattern.compile(h2, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlCode);
        System.out.println(matcher.matches());

        //To know where the occurrences are
        //Using the Find, Start and get methods
        String h22= "<h2>";        //the dot considers everything adn the * ignores it
        Pattern pattern1 = Pattern.compile(h22, Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(htmlCode);
        matcher1.reset();
        int count=0;
        while (matcher1.find()){
            count++;
            System.out.println("Occurrence "+count+" : "+matcher1.start()+" to "+matcher1.end());
        }

        //Using Group Pattern for the same
        System.out.println("\nUsing Group Pattern for the same");
        String h2Group = "(<h2>)";  //group
        Pattern patternGroup = Pattern.compile(h2Group);
        Matcher matcherGroup = patternGroup.matcher(htmlCode);
        System.out.println(matcherGroup.matches());
        matcherGroup.reset();
        while(matcherGroup.find()){
            System.out.println("Occurrence: "+matcherGroup.group(1));
        }


        //Greedy quantifier (*) ..... it will grab as much text as it can
        String h2GroupGreedy = "(<h2>.*</h2>)";  //group
        Pattern patternGroupGreedy = Pattern.compile(h2GroupGreedy);
        Matcher matcherGroupGreedy = patternGroupGreedy.matcher(htmlCode);
        System.out.println(matcherGroupGreedy.matches());
        matcherGroupGreedy.reset();
        while(matcherGroupGreedy.find()){
            System.out.println("Occurrence: "+matcherGroupGreedy.group(1));
        }

        //Lazy quantifier (?) ..... it will take least possible test
        String h2GroupLazy = "(<h2>.*?</h2>)";  //group
        Pattern patternGroupLazy = Pattern.compile(h2GroupLazy);
        Matcher matcherGroupLazy = patternGroupLazy.matcher(htmlCode);
        System.out.println(matcherGroupLazy.matches());
        matcherGroupLazy.reset();
        while(matcherGroupLazy.find()){
            System.out.println("Occurrence: "+matcherGroupLazy.group(1));
        }

        //Getting the text between the tags and not the tags ***** Text Groups ******
        System.out.println("\nGetting the text between the tags and not the tags");
        String textGroup = "(<h2>)(.*?)(</h2>)";   //this is grouping the parts of text....we are interested in group no 2
        Pattern textGroupPat = Pattern.compile(textGroup);
        Matcher textGroupMat = textGroupPat.matcher(htmlCode);

        while(textGroupMat.find()){
            //printing the group 2
            System.out.println("Occurrance : "+textGroupMat.group(2));
        }



    }
}
