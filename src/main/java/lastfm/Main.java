package lastfm;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 * Created by dcampbell on 4/17/16.
 */


public class Main {

    public static void main(String[] args) throws Exception {
        Options options = cliOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        System.out.println(cmd.getOptionValue("u"));

        LastFM lastfm = new LastFM();
        lastfm.setCredentials();
        lastfm.printSimilar();

    }

    private static Options cliOptions() {
        Options options = new Options();
        options.addOption("u", true, "The username to use. Will use ~/.lastfm_secrete USERNAME if not given");
        return options;
    }


}
