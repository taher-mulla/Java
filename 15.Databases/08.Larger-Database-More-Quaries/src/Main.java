import model.Artist;
import model.Datasource;
import model.Song;
import model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()){
            System.out.println("Can not open database");
            return;
        }


        //**************** Printing the artist table ****************
        System.out.println("**************** Printing the artist table **************** ");
//        List<Artist> artists = datasource.queryArtists();
        List<Artist> artists = datasource.queryOrderByArtists(Datasource.ORDER_BY_ASC);
        if(artists == null){
            System.out.println("No artists");
            return;
        }
        for(Artist artist:artists){
            System.out.println("Id = "+artist.getId()+"  \tName =  "+artist.getName());
        }


        //**************** Printing a query for albums by an artist ****************
        System.out.println("\n**************** Printing a query for albums by an artist **************** ");
        List<String> albumsForArtist =
                datasource.queryAlbumForArtists("Iron Maiden", Datasource.ORDER_BY_ASC);
        System.out.println();
        for(String album:albumsForArtist){
            System.out.println(album);
        }

        //***************** returning the artist name, track name, and the track number form song on album *****************
        System.out.println("\n***************** returning the artist name, track name, and the track number form song on album *****************");
        List<SongArtist> songArtists =
                datasource.queryArtistFromSongsFromAlbums("Heartless", Datasource.ORDER_BY_ASC);
        System.out.println();
        if(songArtists == null) {
            System.out.println("Something went wrong while calling queryArtistFromSongsFromAlbums");
            return;
        }
        for(SongArtist artist:songArtists){
            System.out.println("Artist name : "+artist.getArtistNae()+
                    " | Album name : "+artist.getAlbumName()+
                    " | Track no : "+artist.getTrack());
        }

        //************ getting songs metadata *****************
        System.out.println("\n************ getting songs matadata *****************");
        datasource.querySongsMataData();


        datasource.close();

    }
}
