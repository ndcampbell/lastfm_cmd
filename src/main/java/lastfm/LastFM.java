package lastfm;

/**
 * Created by dcampbell on 4/17/16.
 */

import de.umass.lastfm.*;

import java.util.Collection;

public class LastFM {

    private String KEY;
    private String SECRET;
    private String USER;
    private String PASS;

    public void setCredentials(String user, String pass) throws Exception {
        LoadKeys loadkeys = new LoadKeys();
        loadkeys.loadAll();
        this.KEY = loadkeys.key;
        this.SECRET = loadkeys.secret;
        this.USER = user;
        this.PASS = pass;
    }

    public void getRecentArtists(Integer returnnum) throws Exception {
        Chart<Artist> chart = User.getWeeklyArtistChart(USER, returnnum, KEY);
        Collection<Artist> artists = chart.getEntries();
        for (Artist artist: artists) {
            Collection<Artist>  similars = Artist.getSimilar(artist.getName(), 3, KEY);
            System.out.println("Current Artist: " + artist.getName());
            for (Artist similar: similars) {
                System.out.println("Similar Artist: " + similar.getName());
            }
        }
    }

    public void getRecommendedArtists() throws Exception {
        Session session = getSession();
        PaginatedResult<Artist> recs = User.getRecommendedArtists(session);
        for (Artist artist: recs) {
            System.out.println(artist.getName());
        }
    }

    private Session getSession() {
        Session session = Authenticator.getMobileSession(USER, PASS, KEY, SECRET);
        return session;
    }

}
