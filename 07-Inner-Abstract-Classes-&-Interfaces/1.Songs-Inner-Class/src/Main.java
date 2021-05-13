public class Main {
    public static void main(String[] args) {

        Album album = new Album();

        album.addSong("AAA", 9.98);
        album.addSong("BBB", 3.76);
        album.addSong("CCC", 1.23);
        album.addSong("DDD", 3.65);
        album.addSong("EEE", 4.65);
        album.addSong("AAA", 9.98);
        album.addSong("BBB", 3.76);
        album.printSongs();



//        album.addSongToPlaylist("AAA");
//        album.printPlaylist();
//        album.deleteFromPlaylist("AAA");
//        //System.out.println("Song added to playlist");
//        album.printPlaylist();
//
//        album.addSongToPlaylist("AAA");
//        album.addSongToPlaylist("BBB");
//        album.addSongToPlaylist("CCC");
//        album.addSongToPlaylist("DDD");
//        album.printPlaylist();
//
//        System.out.println("\n\n\nStart playing: ");
//        album.startPlaying();






        /*

        LinkedList<String> test = new LinkedList<String>();
        test.add("AA");
        test.add("BB");
        test.add("CC");
        test.add("DD");
        test.add("EE");
        test.add("FF");
        test.add("GG");

        ListIterator<String> iterator = test.listIterator();

        System.out.println(iterator.next());
        System.out.println(iterator.previous());
        iterator.next();
        System.out.println(iterator.next());

        while(iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.previous();
            String a = iterator.next();
            System.out.println("a == "+a);
            if (a.equals("BB")){
                System.out.println(a);
                System.out.println(iterator.previous());
                iterator.remove();
                System.out.println(iterator.next());
                iterator.previous();

            }
            //System.out.println(iterator.next());
        }
        ListIterator<String> temp = test.listIterator();

        System.out.println();
        while(temp.hasNext())
            System.out.println(temp.next());
            */
    }
}
