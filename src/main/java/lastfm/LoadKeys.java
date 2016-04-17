package lastfm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by dcampbell on 4/17/16.
 */

public class LoadKeys {

    public String key;
    public String secret;
    private static String configpath = System.getProperty("user.home") + "/.lastfm_secrets";

    public void loadAll() throws Exception {
        System.out.printf("Loading keys from %s \n" , configpath);
        loadKey();
        loadSecret();
    }

    private void loadKey() throws Exception {
        String unparsedKey;

        Stream<String> lines = Files.lines(Paths.get(configpath));
        Optional<String> hasKey = lines.filter(s -> s.contains("ACCESS_KEY")).findFirst();

        if (hasKey.isPresent()) {
            unparsedKey = hasKey.get();
            this.key = parseString(unparsedKey);
        }
    }

    private void loadSecret() throws Exception {
        String unparsedSecret;

        Stream<String> lines = Files.lines(Paths.get(configpath));
        Optional<String> hasSecret = lines.filter(s -> s.contains("SECRET")).findFirst();
        if (hasSecret.isPresent()) {
            unparsedSecret = hasSecret.get();
            this.secret = parseString(unparsedSecret);
        }
    }

    private String parseString(String item) {
        String[] parts = item.split("=");
        return parts[1];
    }
}
