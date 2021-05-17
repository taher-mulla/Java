import model.Datasource;
import model.Song;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()){
            System.out.println("Can not open database");
            return;
        }


        ///to save to a specific point you can set a save point,
        // so when the rollback will be called, everything b4 that point will be committed


        datasource.insertSong("I sing my song","Taher","First Album", 1);

        datasource.close();


    }
}
