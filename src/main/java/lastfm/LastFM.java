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
import java.util.Random;

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

    void printRandom() {
        Caller.getInstance().setDebugMode(false);
        System.out.println("Random Recommendation");

        Chart<Artist> chart = User.getWeeklyArtistChart(config.getUser(), 1, config.getKey());
        Collection<Artist> artists = chart.getEntries();
        int size = artists.size();
        int randomnum = new Random().nextInt(size);
        int i = 0;
        for (Artist artist : artists) {
            if (i == randomnum) {
                Collection<Artist> similars = Artist.getSimilar(artist.getName(), 5, config.getKey());
                int simRandom = new Random().nextInt(similars.size());
                int j = 0;
                for (Artist similar : similars) {
                    if (j == simRandom) {
                        System.out.println("Recommendation: " + similar.getName());
                        Collection<Tag> tags = Artist.getTopTags(similar.getName(), config.getKey());
                        System.out.print("\tTags: ");
                        //prints first 4 tags
                        int k = 0;
                        for (Tag tag : tags) {
                            if (k < 4) {
                                System.out.print(" " + tag.getName() + ",");
                                k++;
                            } else { break; }

                        }
                        System.out.print("\n");
                    }
                    j = j + 1;
                }
            }
            i = i + 1;
        }
    }

    private Session getSession() {
        Session session = Authenticator.getMobileSession(config.getUser(), config.getPass(),
                                                         config.getKey(), config.getSecret());
        return session;
    }

}
