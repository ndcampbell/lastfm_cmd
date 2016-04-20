package lastfm;

/**
 * Gets LastFM stuff
 */

import de.umass.lastfm.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    void printSimilar() {
        Caller.getInstance().setDebugMode(false);
        System.out.println("Showing info for user: " + config.getUser());

        Chart<Artist> chart = User.getWeeklyArtistChart(config.getUser(), 1, config.getKey());
        Collection<Artist> artists = chart.getEntries();
        for (Artist artist: artists) {
            Collection<Artist>  similars = Artist.getSimilar(artist.getName(), 3, config.getKey());
            System.out.println("Recent Artist: " + artist.getName());
            for (Artist similar: similars) {
                System.out.println("\tSimilar Artist: " + similar.getName());
                //Gets tags of similar artist
                Collection<Tag> tags = Artist.getTopTags(similar.getName(), config.getKey());
                System.out.print("\t\tTags: ");
                //prints first 4 tags
                int i = 0;
                for (Tag tag: tags) {
                    if (i < 4) {
                        System.out.print(" " + tag.getName() + ",");
                        i++;
                    } else { break; }
                }
                System.out.print("\n");
                //Gets albums of similar artist
                Collection<Album> albums = Artist.getTopAlbums(similar.getName(), config.getKey());
                //prints out the first album
                String album = albums.iterator().next().getName();
                System.out.println("\t\tAlbum: " + album);
            }
            System.out.println("\n");
        }
    }

    private Session getSession() {
        Session session = Authenticator.getMobileSession(config.getUser(), config.getPass(),
                                                         config.getKey(), config.getSecret());
        return session;
    }

}
