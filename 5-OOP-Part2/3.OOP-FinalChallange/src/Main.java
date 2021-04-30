public class Main {
    public static void main(String[] args){
        BaseBurger BaseBurger = new BaseBurger("Garlic","Fish");
        BaseBurger.setMayo(true);
        BaseBurger.setMustard(false);
        BaseBurger.setTomato(true);


        BaseBurger BB = new BaseBurger("Brown rye", "goat");
        BB.setMustard(true);
        BB.setTomato(true);
        BB.setMayo(true);
        
        //Composition Example -- the BaseBurger object is being passes to the  class
        HealthyBurger HealthyBurger = new HealthyBurger(BB,true,false);

        //Inheritance -- this class inherits from 'BaseBurger' class
        DeluxBurger DuluxBurger = new DeluxBurger("Normal","Rabbit");

        System.out.println("Your normal burger is for "+BaseBurger.bill());
        System.out.println("Toato: "+BaseBurger.getTomato());
        System.out.println("Mayo: "+BaseBurger.getMayo());
        System.out.println("Mustard: "+BaseBurger.getMustard());
        System.out.println("Bread is: "+BaseBurger.getBreadType()+" bread");
        System.out.println("Meat is: "+BaseBurger.getMeatType());

        System.out.println("\n\nYour Healthy burger is for "+HealthyBurger.bill());
        System.out.println("Toato: "+HealthyBurger.getBurger().getTomato());
        System.out.println("Mayo: "+HealthyBurger.getBurger().getMayo());
        System.out.println("Mustard: "+HealthyBurger.getBurger().getMustard());
        System.out.println("Bread is: "+HealthyBurger.getBurger().getBreadType()+" bread");
        System.out.println("Meat is: "+HealthyBurger.getBurger().getMeatType());
        System.out.println("Avacodo is: "+HealthyBurger.isAvacaod());
        System.out.println("Greeans is: "+HealthyBurger.isGreans());

        System.out.println("\n\nYour Delux burger is for "+DuluxBurger.bill());
        System.out.println("Your Delux Burger is for: "+DuluxBurger.bill2());
        System.out.println("Tomato: "+DuluxBurger.getTomato());
        System.out.println("Mayo: "+DuluxBurger.getMayo());
        System.out.println("Mustard: "+DuluxBurger.getMustard());
        System.out.println("Bread is: "+DuluxBurger.getBreadType()+" bread");
        System.out.println("Meat is: "+DuluxBurger.getMeatType());
        System.out.println("You will also get chips and a drink ;)");

    }
}
