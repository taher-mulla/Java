package sample.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/user/Java-Learning/Udemy-Java/19.Databases/12.GUIWithSQL/" + DB_NAME;

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

    private Connection comm;
    private static Datasource instance = new Datasource();

    private Datasource(){

    }

    public List<Artist> queryArtists(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = comm.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_INDEX_ARTIST_ID));
                artist.setName(results.getString(COLUMN_INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }



    /*//returning a list in asc or desc order
    //returning list of Artists(table)
    public List<Artist> queryArtists(int sortOrder){

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

                artists.add(artist);
            }
            return artists;

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }*/


    public static Datasource getInstance(){
        return instance;
    }

    //opening database
    public boolean open() {
        try {
            comm = DriverManager.getConnection(CONNECTION_STRING);

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //closing database
    public boolean close() {
        try {

            if (comm != null) {
                comm.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("closing error " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
