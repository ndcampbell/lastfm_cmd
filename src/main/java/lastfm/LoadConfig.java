package lastfm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Loads the ~/.lastfm_secrets configs.
 */

class LoadConfig {

    private String key;
    private String secret;
    private String user;
    private String pass;
    private static String configpath = System.getProperty("user.home") + "/.lastfm_secrets";


    void readConfigs() {
        try (Stream<String> stream = Files.lines(Paths.get(configpath))) {
            stream.forEach(this::setAll);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAll(String s){
        if (s.contains("ACCESS_KEY=")) {
            this.key = parseString(s);
        }
        else if (s.contains("SECRET=")) {
            this.secret = parseString(s);
        }
        else if (s.contains("USERNAME=")) {
            this.user = parseString(s);
        }
        else if (s.contains("PASSWORD=")) {
            this.pass = parseString(s);
        }
    }

    // Splits string on = and returns last item
    private String parseString(String item) {
        String[] parts = item.split("=");
        return parts[1];
    }

    //Setters for username and password if provided
    void setUser(String user) {
        this.user = user;
    }

    void setPass(String pass) {
        this.pass = pass;

    }
    //Getters
    String getKey() {
        return key;
    }

    String getSecret() {
        return secret;
    }

    String getUser() {
        return user;
    }

    String getPass() {
        return pass;
    }
}
