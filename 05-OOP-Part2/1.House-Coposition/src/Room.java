public class Room {
    private Dimensions dimensions;
    private Furniture furniture;
    private Electric electric;

    public Room(Dimensions dimensions, Furniture furniture, Electric electric) {
        this.dimensions = dimensions;
        this.furniture = furniture;
        this.electric = electric;
        System.out.println("In constructor Room()");
    }

    public void changeSofa(boolean what){
        furniture.setSofa(false);
        System.out.println("Called setSofa() by object in Room");
        System.out.println("Sofa: "+furniture.isSofa()+"\nTable: "+furniture.isTable());
    }

    public void changeDimensions(int l,int b,int h){
        System.out.println("Before changing");
        System.out.println("Length: "+dimensions.getL()+" Breath: "+dimensions.getB()
                +" Height: "+dimensions.getH());
        dimensions.setB(b);
        dimensions.setH(h);
        dimensions.setL(l);
        System.out.println("After changing");
        System.out.println("Length: "+dimensions.getL()+" Breath: "+dimensions.getB()
                +" Height: "+dimensions.getH());
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public void setElectric(Electric electric) {
        this.electric = electric;
    }
}
