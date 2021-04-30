public class Main {
    public static void main(String[] args){
    
    	//this class internally extends and hence uses the 'Car' class
        Swift swift = new Swift();
        swift.startCar();
   
    	//this class internally extends and hence uses the 'Car' class 
        Maruti maruti = new Maruti();
        maruti.startCar();
        maruti.display();
    }
}

class Car{
    private int cylinders;
    private boolean engine;
    private String name;
    private int wheels;

    public Car(int cylinders, String name) {
        this.cylinders = cylinders;
        this.engine = true;
        this.name = name;
        this.wheels = 4;
    }

    public void startCar(){
        System.out.println("In Car :: Car is staring - useless car");
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public boolean isEngine() {
        return engine;
    }

    public void setEngine(boolean engine) {
        this.engine = engine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }
}

class Swift extends Car{

	//'super' --> THIS IS REFERRING TO THE CLASS 'Car' constructor
    public Swift() {
        super(6,"Swift");
    }

    @Override
    public void startCar() {
        System.out.println("In Swift :: Swift is starting - good car");
    }
}

class  Maruti extends Car{

	//'super' --> THIS IS REFERRING TO THE CLASS 'Car' constructor
    public Maruti() {
        super(6,"Maruti");
    }
    public void changeBySet(int c, String name){
        setCylinders(c);
        setName(name);
    }

    public void display(){
        System.out.println("The car cylinders are "+getCylinders()+
                "\n Name is "+getName());
    }
}
