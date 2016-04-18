package lastfm; /**
 * Created by dcampbell on 4/17/16.
 */


public class Main {

    public static void main(String[] args) throws Exception {
        LastFM lastfm = new LastFM();
        lastfm.setCredentials();
        lastfm.printSimilar();
    }
}
