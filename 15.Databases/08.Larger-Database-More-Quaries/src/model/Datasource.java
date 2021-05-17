package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/user/Java-Learning/Udemy-Java/19.Databases/8.LargerDataBMoreQuaries/"+DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int COLUMN_INDEX_ALBUM_ID = 1;
    public static final int COLUMN_INDEX_ALBUM_NAME = 2;
    public static final int COLUMN_INDEX_ALBUM_ARTIST = 2;


    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int COLUMN_INDEX_ARTIST_ID = 1;
    public static final int COLUMN_INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int COLUMN_INDEX_SONG_ID = 1;
    public static final int COLUMN_INDEX_SONG_TRACK = 2;
    public static final int COLUMN_INDEX_SONG_TITLE = 3;
    public static final int COLUMN_INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    //**************** returning the artist name, track name, and the track number for song on album
//    SELECT artists.name, albums.name, songs.track FROM songs
//    INNER JOIN albums ON songs.album = albums._id
//    INNER JOIN artists ON albums.artist = artists._id
//    WHERE songs.title="Go Your Own Way"
//    ORDER BY artists.name, albums.name COLLATE NOCASE ASC


//    SELECT artists.name, albums.name, songs.track FROM songs
//    INNER JOIN albums ON songs.album = albums._id
//    INNER JOIN artists ON albums.artist = artists._id
//    WHERE songs.title="
    public static final String QUERY_ARTIST_FROM_SONGS_FROM_ALBUM =
            "SELECT "+TABLE_ARTISTS+"."+COLUMN_ARTIST_NAME+" , "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+" , "+TABLE_SONGS+"."+COLUMN_SONG_TRACK+" FROM "+TABLE_SONGS+
                    " INNER JOIN "+TABLE_ALBUMS+" ON "+TABLE_SONGS+"."+COLUMN_SONG_ALBUM+" = "+TABLE_ALBUMS+"."+COLUMN_ALBUM_ID+
                    " INNER JOIN "+TABLE_ARTISTS+" ON "+TABLE_ALBUMS+"."+COLUMN_ALBUM_ARTIST+" = "+TABLE_ARTISTS+"."+COLUMN_ARTIST_ID+
                    " WHERE "+TABLE_SONGS+"."+COLUMN_SONG_TITLE +" = \"";

//        ORDER BY artists.name, albums.name COLLATE NOCASE
    public static final String QUERY_ARTIST_FROM_SONGS_FROM_ALBUM_SORT =
            " ORDER BY "+TABLE_ARTISTS+"."+COLUMN_ARTIST_NAME+" , "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+" COLLATE NOCASE ";




    private Connection comm;

    //returning list of Artists(table)
    public List<Artist> queryArtists(){
        Statement statement = null;
        ResultSet result = null;

        try{

            statement = comm.createStatement();
            result = statement.executeQuery("SELECT * FROM "+TABLE_ARTISTS);
            List<Artist> artists = new ArrayList<>();
            //making a list of artist in artists
            while(result.next()){
                Artist artist = new Artist();

                //to use the result by column index
                artist.setId(result.getInt(COLUMN_INDEX_ARTIST_ID));
                artist.setName(result.getString(COLUMN_INDEX_ARTIST_NAME));


                //to use the result by column name
                //this is slow as the result has to go through all columns and match the correct one to the given value
//                artist.setId(result.getInt(COLUMN_ARTIST_ID));
//                artist.setName(result.getString(COLUMN_ARTIST_NAME));

                artists.add(artist);
            }
            return artists;

        }catch (SQLException e){
            System.out.println("queryArtists failed "+e.getMessage());
            e.printStackTrace();
            return null;
        }finally {
            try{
                if(result!=null)
                    result.close();
            }catch (SQLException e){
                System.out.println("error closing result of queryArtists");
            }

            try{
                if(statement !=null)
                    statement.close();
            }catch (SQLException e){
                System.out.println("error closing statement of queryArtists");
            }
        }
    }




    //returning a list in asc or desc order
    //returning list of Artists(table)
    public List<Artist> queryOrderByArtists(int sortOrder){

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if(sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC)
                sb.append(" DESC ");
            else
                sb.append(" ASC ");
        }

        try( Statement statement =  comm.createStatement();
             ResultSet result = statement.executeQuery(sb.toString()) ){

            List<Artist> artists = new ArrayList<>();
            //making a list of artist in artists
            while(result.next()){
                Artist artist = new Artist();

                //to use the result by column index
                artist.setId(result.getInt(COLUMN_INDEX_ARTIST_ID));
                artist.setName(result.getString(COLUMN_INDEX_ARTIST_NAME));


                //to use the result by column name
                //this is slow as the result has to go through all columns and match the correct one to the given value
//                artist.setId(result.getInt(COLUMN_ARTIST_ID));
//                artist.setName(result.getString(COLUMN_ARTIST_NAME));

                artists.add(artist);
            }
            return artists;

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<String> queryAlbumForArtists (String artistName, int sortOrder){

        //SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id WHERE artists.name = "Carole King" ORDER BY albums.name COLLATE NOCASE ASC
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(TABLE_ALBUMS+'.'+COLUMN_ALBUM_NAME);
        sb.append(" FROM ");
        sb.append(TABLE_ALBUMS);
        sb.append(" INNER JOIN ");
        sb.append(TABLE_ARTISTS);
        sb.append(" ON ");
        sb.append(TABLE_ALBUMS+'.'+COLUMN_ALBUM_ARTIST);
        sb.append(" = ");
        sb.append(TABLE_ARTISTS+'.'+COLUMN_ARTIST_ID);
        sb.append(" WHERE ");
        sb.append(TABLE_ARTISTS+'.'+COLUMN_ARTIST_NAME);
        sb.append(" = \"");
        //This string till here can be declared as a constant also
        sb.append(artistName);
        sb.append("\"");


        if(sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(TABLE_ALBUMS+'.'+COLUMN_ALBUM_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC)
                sb.append(" DESC ");
            else
                sb.append(" ASC ");
        }

        System.out.println("SQL statement for queryAlbumForArtists = \n"+sb.toString());
        try( Statement statement =  comm.createStatement();
             ResultSet result = statement.executeQuery(sb.toString()) ){

            List<String> albums = new ArrayList<>();
            while(result.next()){
                //here we are adding just column 1
                albums.add(result.getString(1));
            }
            return albums;

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }


//    *****************returning the artist name, track name, and the track number form song on album
    public List<SongArtist> queryArtistFromSongsFromAlbums(String songName, int sortOrder){
        StringBuilder sb= new StringBuilder();
        sb.append(QUERY_ARTIST_FROM_SONGS_FROM_ALBUM);
        sb.append(songName);
        sb.append("\"");
        if(sortOrder != ORDER_BY_NONE){
            sb.append(QUERY_ARTIST_FROM_SONGS_FROM_ALBUM_SORT);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC)
                sb.append(" DESC ");
            else
                sb.append(" ASC ");
        }
        System.out.println("SQL statement for queryArtistFromSongsFromAlbums = \n"+sb.toString());

        try( Statement statement =  comm.createStatement();
             ResultSet result = statement.executeQuery(sb.toString()) ){

            List<SongArtist> songArtists = new ArrayList<>();
            while(result.next()){

                songArtists.add(new SongArtist(result.getString(1), result.getString(2), result.getInt(3) ));


            }
            return songArtists;

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    //Getting songs MataData
    public void querySongsMataData(){
        String sqlStatement = "SELECT * FROM "+TABLE_SONGS;

        try( Statement statement =  comm.createStatement();
             ResultSet result = statement.executeQuery(sqlStatement) ){

            ResultSetMetaData data = result.getMetaData() ;
            int numColumn = data.getColumnCount();

            for(int i=1; i<=numColumn; i++){
                System.out.println("Column "+i+" in the songs table is names "+data.getColumnName(i));
            }

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
        }

    }

    //opening database
    public boolean open(){
        try{
            comm= DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //closing database
    public boolean close(){
        try{
            if(comm != null) {
                comm.close();
                return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println("closing error "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
