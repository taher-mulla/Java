//THIS CLASS EXTENDS THE CLASS 'Car' IT CAN USE THE PARENT CLASS ITS LIKE INHERITANCE 
public class Swift extends Car{
    private int maxSpeed;
    private int milage;

    public Swift(int maxSpeed, int milage) {
        super("Sidan", true);
        this.maxSpeed = maxSpeed;
        this.milage = milage;
    }

    @Override
    public void print() {
        System.out.println("In printSwift()");
        System.out.println("\tThe Car is Swift ");
        System.out.println("\tMax speed = "+maxSpeed);
        System.out.println("\tMilage is = "+milage);
        super.print();
    }
}
