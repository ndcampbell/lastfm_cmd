package lastfm; /**
 * Created by dcampbell on 4/17/16.
 */


public class Entry {

    private static String url = "http://www.google.com";

    public static void main(String[] args) throws Exception {
        LastFM lastfm = new LastFM();
        lastfm.setUser("warmsounds");
        lastfm.getArtists(2);
    }
}
