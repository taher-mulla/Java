package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/user/Java-Learning/Udemy-Java/19.Databases/9.SQLFuncationsAndViewsInJava/"+DB_NAME;

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


    public static final String TABLE_ARTISTS_SONGS_VIEW = "artist_list";
    //creating a view
//    CREATE VIEW IF NOT EXISTS artist_list
//    AS SELECT artists.name, albums.name AS album, songs.track, songs.title FROM songs
//    INNER JOIN albums ON songs.album = albums._id
//    INNER JOIN artists ON albums.artist = artists._id
//    ORDER BY artists.name, albums.name, songs.track
    public static final String VIEW_ARTISTS_FOR_SONG =
            "CREATE VIEW IF NOT EXISTS "+TABLE_ARTISTS_SONGS_VIEW+
                    " AS SELECT "+TABLE_ARTISTS+"."+COLUMN_ARTIST_NAME+" , "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+" AS "+COLUMN_SONG_ALBUM+" , "+TABLE_SONGS+"."+COLUMN_SONG_TRACK+" , "+TABLE_SONGS+"."+COLUMN_SONG_TITLE+" FROM "+TABLE_SONGS+
            " INNER JOIN "+TABLE_ALBUMS+" ON "+TABLE_SONGS+"."+COLUMN_SONG_ALBUM+" = "+TABLE_ALBUMS+"."+COLUMN_ALBUM_ID+
            " INNER JOIN "+TABLE_ARTISTS+" ON "+TABLE_ALBUMS+"."+COLUMN_ALBUM_ARTIST+" = "+TABLE_ARTISTS+"."+COLUMN_ARTIST_ID+
            " ORDER BY "+TABLE_ARTISTS+"."+COLUMN_ARTIST_NAME+" , "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+" , "+TABLE_SONGS+"."+COLUMN_SONG_TRACK;


//    SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way"
    public static final String QUARRY_VIEW_ARTIST_LIST = "Select "+COLUMN_ARTIST_NAME+" , "+COLUMN_SONG_ALBUM+" , "+COLUMN_SONG_TRACK+" FROM "+TABLE_ARTISTS_SONGS_VIEW+" WHERE "+COLUMN_SONG_TITLE+" = \"";

    private Connection comm;

    //***************** using a function count(*) and min id value *****************
    public int getCount(String table){
        String sqlStatement = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM "+table;

        try( Statement statement =  comm.createStatement();
             ResultSet result = statement.executeQuery(sqlStatement) ){

            System.out.println("Count = "+result.getInt("count")+" Min _id = "+result.getInt("min_id"));
            int count = result.getInt(1);
            return count;

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }


    //****************** creating a view ******************
    public boolean createViewFroSongArtists(){
        try( Statement statement =  comm.createStatement() ){

            statement.execute(VIEW_ARTISTS_FOR_SONG);
            return true;

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //************** Quarrying a view **************
    public List<SongArtist> qureySongView(String title){
        StringBuilder sb = new StringBuilder();
        sb.append(QUARRY_VIEW_ARTIST_LIST);
        sb.append(title);
        sb.append("\"");
        System.out.println("The sql statement is :\n"+sb.toString());

        try( Statement statement =  comm.createStatement();
        ResultSet results = statement.executeQuery(sb.toString())){

            List<SongArtist> songArtists = new ArrayList<>();
            while (results.next()){
                songArtists.add(new SongArtist(results.getString(1), results.getString(2), results.getInt(3)));
            }
            return songArtists;

        }catch (SQLException e) {
            System.out.println("queryArtists failed " + e.getMessage());
            e.printStackTrace();
            return null;
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
