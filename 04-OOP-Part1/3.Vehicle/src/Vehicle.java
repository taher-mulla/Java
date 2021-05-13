public class Vehicle {
    private boolean wheels;
    private int noWheels;
    private String airGroundWater;

    public Vehicle(boolean wheels, int noWheels, String airGroundWater) {
        this.wheels = wheels;
        this.noWheels = noWheels;
        this.airGroundWater = airGroundWater;
    }

    public void print(){
        System.out.println("In printVehicle()");
        System.out.println("\tWheels "+wheels+" \n\tNumber of wheels = "+noWheels+
                " \n\tThe vehicle is "+airGroundWater);
    }


}
