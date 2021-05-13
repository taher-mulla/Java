public class Main {
    public static void main(String[] args){
        Furniture furniture = new Furniture(true,false);
        Electric electric = new Electric(15,"Phlips","Air India");
        Dimensions dimensions = new Dimensions(12,30,50);


	//THE 'Room' CLASS ACCEPTS THE 'Furniture', 'Electric' AND 'Dimensions' CLASSES. 
	//FROM HERE THE 'Room' CLASS CAN ACCEPT AND PRINT ALL THE VALUES IN THOSE CLASSES
        Room room = new Room(dimensions,furniture,electric);
        room.changeSofa(false);
        room.changeDimensions(12,12,12);
        room.getFurniture().print();
    }
}
