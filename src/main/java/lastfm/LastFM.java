package lastfm;

/**
 * Gets LastFM stuff
 */

import de.umass.lastfm.*;

import java.util.Collection;

class LastFM {

    private LoadConfig config = new LoadConfig();


    void setCredentials(String user, String pass) {
        config.readConfigs();
        config.setUser(user);
        config.setPass(pass);
    }

    void setCredentials(String user) {
        config.readConfigs();
        config.setUser(user);
    }

    void setCredentials() {
        config.readConfigs();
    }

    void getRecentArtists(Integer returnnum) throws Exception {
        Chart<Artist> chart = User.getWeeklyArtistChart(config.getUser(), returnnum, config.getKey());
        Collection<Artist> artists = chart.getEntries();
        for (Artist artist: artists) {
            Collection<Artist>  similars = Artist.getSimilar(artist.getName(), 3, config.getKey());
            System.out.println("Current Artist: " + artist.getName());
            for (Artist similar: similars) {
                System.out.println("Similar Artist: " + similar.getName());
            }
        }
    }

    private Session getSession() {
        Session session = Authenticator.getMobileSession(config.getUser(), config.getPass(),
                                                         config.getKey(), config.getSecret());
        return session;
    }

}
