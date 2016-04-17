package lastfm;

/**
 * Created by dcampbell on 4/17/16.
 */

import de.umass.lastfm.*;

import java.util.Collection;

public class LastFM {

    private static final String KEY = "xxxx";
    private static final String SECRET = "xxxxx"
    private String user;
    private String pass;

    public void setUserPass(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void getArtists(Integer returnnum) throws Exception {
        Chart<Artist> chart = User.getWeeklyArtistChart(user, returnnum, KEY);
        User.
        Collection<Artist> artists = chart.getEntries();
        for (Artist artist: artists) {
            System.out.println(artist.getName());
        }
    }
}
