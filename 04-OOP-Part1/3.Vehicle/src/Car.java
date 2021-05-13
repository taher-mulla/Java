public class Car extends Vehicle{
    private String type;
    private boolean roof;

    public Car(String type, boolean roof) {
        super(true, 4, "Ground");
        this.type = type;
        this.roof = roof;
    }
    @Override
    public void print(){
        System.out.println("In PrintCar()");
        System.out.println("\tCar type is "+type+" \n\tCar roof is "+roof);
        super.print();
//        super.super.print();
    }



}
