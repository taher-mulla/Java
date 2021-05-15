import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        //*********** Converting a Runnable to Lambda ***********
        System.out.println("*********** Converting a Runnable to Lambda ***********");
        Runnable rn = new Runnable() {
            @Override
            public void run() {
                String myStr = "Lets play this game";
                String[] temp = myStr.split(" ");
                for(String st : temp)
                    System.out.println(st);

            }
        };
        rn.run();

        Runnable lambdaRN = () -> {
            String myStr = "Lets play this game";
            String[] temp = myStr.split(" ");
            for(String st : temp)
                System.out.println(st);
        };
        lambdaRN.run();


        //*********** Converting a method to a lambda ***********
        System.out.println("*********** Converting a method to a lambda ***********");
        //Method name is everySecondCharacter
        Function<String, String> fuc = (String source) -> {
            StringBuilder str = new StringBuilder();
            for(int i=0; i<source.length(); i++){
                if(i%2 == 1){
                    str.append(source.charAt(i));
                }
            }
            return  str.toString();
        };

        //Method
        System.out.println("Method = "+everySecondCharacter("1234567890"));
        //Lambda
        System.out.println("Lambda = "+fuc.apply("1234567890"));


        //********** Lambda that maps to the Supplier ***********
        System.out.println("********** Lambda that maps to the Supplier ***********");
        Supplier<String> iLoveJava = () -> "I Love Java";
        String iLoveJavaOP = iLoveJava.get();
        System.out.println(iLoveJavaOP);


        //**************** sorting using lambda expressions **************
        System.out.println("**************** sorting using lambda expressions **************");
        List<String> names = Arrays.asList(
                "Taher",
                "Sohail",
                "Suraj",
                "shreya",
                "Vishal",
                "Yash",
                "Bhoot",
                "apple");
        List<String> firstList = new ArrayList<>();
        names.forEach((String na) -> firstList.add(na.substring(0,1).toUpperCase() + na.substring(1)));
        firstList.sort((String s1, String s2) -> s1.compareTo(s2));
        firstList.forEach(s-> System.out.println(s));


        //**************** sorting using lambda expressions Method References **************
        System.out.println("\n**************** sorting using lambda expressions Method References **************");
        firstList.sort(String::compareTo);
        firstList.forEach(System.out::println);

        //************** Using stream and chain to sort *****************
        System.out.println("\n************** Using stream and chain to sort *****************");
        names.stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .sorted(String::compareTo)
                .forEach(System.out::println);

        //************** Using stream and chain print the number of names starting with 'A' *****************
        System.out.println("\n************** Using stream and chain print the number of names starting with 'A' *****************");
        Long namesWithA;
        namesWithA = names
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println("Number of names staring with 'A' is = "+namesWithA);

        //**** Using stream and chain to sort and printing the names
        //using peek to debug and see what is happening in the stream ****
        System.out.println("\n**** Using stream and chain to sort and printing the names \n" +
                "using peek to debug and see what is happening in the stream ****");
        names.stream()
                .peek(System.out::println)
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                //.peek(System.out::println)
                .sorted(String::compareTo)
                .forEach(System.out::println);


    }


    //*********** Converting a method to a lambda ***********
    //Method
    public static String everySecondCharacter(String source){
        StringBuilder str = new StringBuilder();
        for(int i=0; i<source.length(); i++){
            if(i%2 == 1){
                str.append(source.charAt(i));
            }
        }
        return  str.toString();
    }
}
