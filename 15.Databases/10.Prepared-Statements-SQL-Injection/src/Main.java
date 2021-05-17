import model.Datasource;
import model.Song;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()){
            System.out.println("Can not open database");
            return;
        }

        Song song = datasource.querySongInfo("Rat Salad");
        System.out.println("ID = "+song.getId()+
                " | Track = "+song.getTrack()+
                " | Name = "+song.getName()+
                " | Album = "+song.getAlbumId());

        datasource.close();



    }
}
