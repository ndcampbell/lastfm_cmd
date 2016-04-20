package lastfm;

import org.apache.commons.cli.*;

/**
 * Created by dcampbell on 4/17/16.
 */


public class Main {

    public static void main(String[] args) throws Exception {

        Options options = cliOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        //prints CLI help statement
        HelpFormatter formatter = new HelpFormatter();
        if (cmd.hasOption("help")) {
            formatter.printHelp("LastFM Recommendation Tool", options );
            System.exit(0);
        }

        LastFM lastfm = new LastFM();

        if (cmd.getOptionValue("u") != null) {
            lastfm.setCredentials(cmd.getOptionValue("u"));
        }
        else {
            lastfm.setCredentials();
        }

        lastfm.printSimilar();

    }

    private static Options cliOptions() {
        Options options = new Options();
        Option help = new Option("help", "prints this message");
        options.addOption("u", "user", true, "The username to use. Will use ~/.lastfm_secrete USERNAME if not given");
        options.addOption("l", "limit", true, "The number of artists to return");
        options.addOption("r", "random", false, "returns a single random artist recommendation");
        options.addOption(help);


        return options;
    }


}
