//Polymorphism example
public class DeluxBurger extends BaseBurger{        

    public DeluxBurger(String breadType, String meatType) {
        super(breadType, meatType);
    }

    @Override
    public int bill() {
        return super.bill()+4;
    }

    public int bill2(){
        return super.bill()+10;
    }
}
