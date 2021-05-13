//Composition example
public class HealthyBurger {    
	
	//The 'BaseBurger'class is being used here to provide data in the overlapping fields    
    private BaseBurger burger;
    private boolean avacaod;
    private boolean greans;

    public HealthyBurger(BaseBurger burger, boolean avacaod, boolean greans) {
        this.burger = burger;
        this.avacaod = avacaod;
        this.greans = greans;
    }

    public BaseBurger getBurger() {
        return burger;
    }

    public void setBurger(BaseBurger burger) {
        this.burger = burger;
    }

    public boolean isAvacaod() {
        return avacaod;
    }

    public void setAvacaod(boolean avacaod) {
        this.avacaod = avacaod;
    }

    public boolean isGreans() {
        return greans;
    }

    public void setGreans(boolean greans) {
        this.greans = greans;
    }

    public int bill(){
        int count=0;
        if(avacaod)
            count++;
        if(greans)
            count++;

        count *=2;
        return burger.bill()+count;
    }
}
