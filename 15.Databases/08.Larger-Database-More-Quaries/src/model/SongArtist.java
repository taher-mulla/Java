package model;

//returning the artist nae, track name, and the track number for song on album
public class SongArtist {
    private String artistNae;
    private String albumName;
    private  int track;


    public SongArtist(String artistNae, String albumName, int track) {
        this.artistNae = artistNae;
        this.albumName = albumName;
        this.track = track;
    }

    public String getArtistNae() {
        return artistNae;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getTrack() {
        return track;
    }
}
