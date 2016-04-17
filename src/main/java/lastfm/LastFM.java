package lastfm;

/**
 * Created by dcampbell on 4/17/16.
 */

import de.umass.lastfm.*;

import java.util.Collection;

public class LastFM {

    private String KEY;
    private String SECRET;
    private String user;
    private String pass;

    private void getLoadkeys() throws Exception {
        LoadKeys loadkeys = new LoadKeys();
        loadkeys.loadAll();
        this.KEY = loadkeys.key;
        this.SECRET = loadkeys.secret;
    }

    public void setUserPass(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void getRecentArtists(Integer returnnum) throws Exception {
        getLoadkeys();
        Chart<Artist> chart = User.getWeeklyArtistChart(user, returnnum, KEY);
        Collection<Artist> artists = chart.getEntries();
        for (Artist artist: artists) {
            System.out.println(artist.getName());
        }
    }
}
