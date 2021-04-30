public class BaseBurger {
    private String breadType;
    private String meatType;
    private boolean tomato;
    private boolean mayo;
    private boolean mustard;

    public BaseBurger(String breadType, String meatType) {
        this.breadType = breadType;
        this.meatType = meatType;
    }

    public int bill(){
        int count=0;

        if(tomato)
            count++;
        if(mustard)
            count++;
        if(mayo)
            count++;

        return count+10;


    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    public Boolean getTomato() {
        return tomato;
    }

    public void setTomato(Boolean tomato) {
        this.tomato = tomato;
    }

    public Boolean getMayo() {
        return mayo;
    }

    public void setMayo(Boolean mayo) {
        this.mayo = mayo;
    }

    public Boolean getMustard() {
        return mustard;
    }

    public void setMustard(Boolean mustard) {
        this.mustard = mustard;
    }
}
