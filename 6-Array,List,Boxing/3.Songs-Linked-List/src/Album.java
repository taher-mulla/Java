import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


//the songs are stored in a ArrayList songs and any song can be also in the LinkedList playlist
public class Album {
    private ArrayList<Song> songs ;
    private LinkedList<Song> playlist;


    public  Album(){
        this.songs = new ArrayList<Song>();
        this.playlist = new LinkedList<Song>();
    }

    public void printSongs(){
        System.out.println("Printing songs");
        for(int i=0;i<songs.size();i++){
            System.out.println((i+1)+". "+songs.get(i).getSongName()+"    "+songs.get(i).getSongDuration());
        }
    }

    public void startPlaying(){
    	//ListIterator is used to traverse the list
        ListIterator<Song> iterator = playlist.listIterator();
        System.out.println("First Song : "+iterator.next().getSongName());
        System.out.println("Play same song again: "+iterator.previous().getSongName());
        iterator.next();
        System.out.println("Next Song : "+iterator.next().getSongName());
        iterator.next();
        System.out.println("Skip 1 song and play next : "+iterator.next().getSongName());
        System.out.println("Play same song again: "+iterator.previous().getSongName());
    }

    public void deleteFromPlaylist(String songName){
        ListIterator<Song> listIterator = playlist.listIterator();
        while(listIterator.hasNext()){
            String name = listIterator.next().getSongName();
            if(name.equals(songName)){
                listIterator.previous();
                listIterator.remove();
            }
        }
    }

    public void printPlaylist(){
        Iterator<Song> iterator = playlist.iterator();
        int i=0;
        System.out.println("Printing Playlist");
        while(iterator.hasNext()){

            Song temp = iterator.next();
            System.out.println((i++)+". "+temp.getSongName()+"   "
                    +temp.getSongDuration());
        }
    }

    public void addSong(String songName, double songDuration){

        Song temp = new Song(songName,songDuration);
        songs.add(temp);
    }

    public void addSongToPlaylist(String songName){
    	//song from songs is put in a playlist
        int index = getIndex(songName);
        if( index!=(-1) )
            playlist.add(songs.get(index));
    }

    private int getIndex(String songName){
        for (int i=0;i<songs.size();i++){
            if( songs.get(i).getSongName().equals(songName) ){
                return i;
            }
        }
        System.out.println("Song cant be found");
        return -1;
    }

}
