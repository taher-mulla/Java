package model;

import java.sql.*;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/user/Java-Learning/Udemy-Java/19.Databases/11.Transactions/" + DB_NAME;

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

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS + "(" + COLUMN_ARTIST_NAME + ") VALUES(?)";
    public static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS + "(" + COLUMN_ALBUM_NAME + " , " + COLUMN_ALBUM_ARTIST + ") VALUES(? , ?)";
    public static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS + "(" + COLUMN_SONG_TRACK + " , " + COLUMN_SONG_TITLE + " , " + COLUMN_SONG_ALBUM + ") VALUES(? , ? , ?)";

    private PreparedStatement insertIntoArtist;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;


    //to find if the artist or album exists
    public static final String QUERY_ARTIST =
            "SELECT " + COLUMN_ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";
    public static final String QUERY_ALBUM =
            "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    public PreparedStatement queryArtist;
    public PreparedStatement queryALBUM;


    private Connection comm;

    private int insertArtist(String name) throws SQLException {
        queryArtist.setString(1, name);
        ResultSet result = queryArtist.executeQuery();
        if (result.next())
            return result.getInt(1);
        else {
            //insert the artist
            insertIntoArtist.setString(1, name);
            int affectedRows = insertIntoArtist.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Couldn't add artist");

            ResultSet generatedKeys = insertIntoArtist.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }

    }


    private int insertAlbum(String name, int artistID) throws SQLException {
        queryALBUM.setString(1, name);
        ResultSet result = queryALBUM.executeQuery();
        if (result.next())
            return result.getInt(1);
        else {
            //insert the artist
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistID);
            int affectedRows = insertIntoAlbums.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Couldn't add album");

            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album");
            }
        }

    }


    public void insertSong(String title, String artist, String album, int track) {

        try {
            comm.setAutoCommit(false);

            int artistID = insertArtist(artist);
            int albumID = insertAlbum(album, artistID);

            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumID);

            int affectedRows = insertIntoSongs.executeUpdate();

            if (affectedRows == 1)
                comm.commit();
            else{
                throw new SQLException("The song insertion failed");
            }



        }//here we want an exception for any error hence we catch all exceptions
         catch (Exception e) {
            System.out.println("insert song error : " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                comm.rollback();
            } catch (SQLException e2) {
                System.out.println("ERROR IN ROLLBACK");
            }
        } finally {
            try {
                System.out.println("Setting default commit to true");
                comm.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't not set commit to true");
            }
        }

    }


    //opening database
    public boolean open() {
        try {
            comm = DriverManager.getConnection(CONNECTION_STRING);
            insertIntoArtist = comm.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = comm.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            //here we will not need the key
            insertIntoSongs = comm.prepareStatement(INSERT_SONG);


            queryArtist = comm.prepareStatement(QUERY_ARTIST);
            queryALBUM = comm.prepareStatement(QUERY_ALBUM);

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

            if (insertIntoAlbums != null)
                insertIntoAlbums.close();
            if (insertIntoArtist != null)
                insertIntoArtist.close();
            if (insertIntoSongs != null)
                insertIntoSongs.close();

            if (queryALBUM != null)
                queryALBUM.close();
            if (queryArtist != null)
                queryArtist.close();

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
