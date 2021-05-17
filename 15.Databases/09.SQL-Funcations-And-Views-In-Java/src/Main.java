import model.Datasource;
import model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()){
            System.out.println("Can not open database");
            return;
        }

        //***************** using a function count(*) and min id value *****************
        System.out.println("***************** using a function count(*) and min id value ***************** ");
        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Count for songs : "+count);

        //****************** creating a view ******************
        System.out.println("\n****************** creating a view ****************** ");
        System.out.println(datasource.createViewFroSongArtists());


//        ************** Quarrying a view **************
        System.out.println("************** Quarrying a view **************");
        List<SongArtist> songArtist = datasource.qureySongView("Go Your Own Way");
        if(songArtist.isEmpty())
            System.out.println("Epty List");
        else
            for(SongArtist artist: songArtist){
                System.out.println("Artist name : "+artist.getArtistNae()+
                        "Album name : "+artist.getAlbumName()+
                        "Track number : "+artist.getTrack());
            }



        datasource.close();

    }
}
