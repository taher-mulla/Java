package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/user/Java-Learning/Udemy-Java/19.Databases/10.PreparedStatementsSQLInjection/"+DB_NAME;

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


//using a prepared statement
    //prepared statement are opened in the open() and should be closed in the close() methods
    public static final String QUERY_SONG_INFO_STATEMENT =
        "SELECT * FROM "+TABLE_SONGS+" WHERE "+COLUMN_SONG_TITLE+" = ?";
    private PreparedStatement viewSongDataPreparesStatement;


    private Connection comm;

   public Song querySongInfo(String title){
       try{
           //1 is there cause there is only 1 '?', if there were more then we would be putting more of these
           viewSongDataPreparesStatement.setString(1, title);
           ResultSet result = viewSongDataPreparesStatement.executeQuery();

           Song song = new Song();
               song.setId(result.getInt(1));
               song.setTrack(result.getInt(2));
               song.setName(result.getString(3));
               song.setAlbumId(result.getInt(4));

           return song;

       }catch (SQLException e) {
           System.out.println("queryArtists failed " + e.getMessage());
           e.printStackTrace();
           return null;
       }finally {

       }
   }



    //opening database
    public boolean open(){
        try{
            comm= DriverManager.getConnection(CONNECTION_STRING);
            viewSongDataPreparesStatement = comm.prepareStatement(QUERY_SONG_INFO_STATEMENT);
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
            if(viewSongDataPreparesStatement!=null){
                viewSongDataPreparesStatement.close();
            }

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
