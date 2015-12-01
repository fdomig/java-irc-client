package at.franziskusdomig.irc;

import java.util.Arrays;

public class NickMask {

    private final String mask;

    public NickMask(String mask) {
        assert mask != null;

        this.mask = mask;
    }

    public String nick() {
        return mask.split("!")[0];
    }

    public String userHost() {
        return mask.split("!")[1];
    }

    public String host() {
        return mask.split("@")[1];
    }

    public String user() {
        return Arrays.asList(mask.split("!")).get(1).split("@")[0];
    }

    @Override
    public String toString() {
        return mask;
    }

}
