import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> someNumbers = Arrays.asList(
                "N432", "N21",
                "A12", "A32",
                "G43", "G21", "G09", "G54", "g14",
                "I21", "I12", "I19",
                "07");


        //********** Using lambda to sort and print *************
        List<String> sortedNumbers = new ArrayList<>();
        //here we are adding all the relevant numbers to the list to be sorted later
        someNumbers.forEach(number -> {
            if(number.toUpperCase().startsWith("G")){
                sortedNumbers.add(number); //adding all the numbers to the list to be sorted later
                System.out.println(number); //printing the numbers
            }
        });

        System.out.println("\n********* Sorted Numbers ************");
        sortedNumbers.sort( (String s1, String s2) -> s1.compareTo(s2) );
        sortedNumbers.forEach( (String s) -> System.out.println(s) );


        //*********** Using Stream to sort and print ****************
        System.out.println("\n********* Using Stream to sort and print ***********");
        //each stream operation performs its functions on the returned values of the function before it
          //map will take all the IP which will be a list of numbers, and convert this to upper case. It maps the old and new ones, old is replaced\
          //filter will filter out all the numbers and will return values that start with "G"
          //sorted will sort the values
          //forEach is printing all the values
        someNumbers
                .stream()
                .map(String::toUpperCase) //can also use .map( s->s.toUpperCase() )
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        //More stream examples
        Stream<String> a = Stream.of("I21", "I12", "I19", "07");
        Stream<String> b = Stream.of("N432", "N21", "A12", "A32", "I19", "07");
        Stream<String> concat = Stream.concat(a, b);
//        System.out.println("\nStream concat = " +concat.count());
//        System.out.println("\nStream concat = " +concat.distinct().count());
        System.out.println("\n\nStream concat = " +concat.distinct().peek(System.out::println).count());

        //********* Using Collectors ************
        System.out.println("\n********* Using Collectors ************");
        List<String> sortedNumbers1 = someNumbers.stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());
        for(String s : sortedNumbers1){
            System.out.println(s);
        }

        //can also use this
//        List<String> sortedNumbers1 = someNumbers.stream()
//                .map(String::toUpperCase)
//                .filter(s->s.startsWith("G"))
//                .sorted()
//                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);



    }
}
