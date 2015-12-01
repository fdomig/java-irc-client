package at.franziskusdomig.irc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerMessage {

    private String tags;
    private NickMask prefix;
    private EventType command;
    private String arguments;

    private final static String RFC_1459_COMMAND_REGEXP = "^(@(?<tags>[^ ]*) )?(:(?<prefix>[^ ]+) +)?(?<command>[^ ]+)( *(?<arguments> .+))?";

    private final static Pattern CMD_PATTERN = Pattern.compile(RFC_1459_COMMAND_REGEXP);

    public ServerMessage(String raw) {

        Matcher matcher = CMD_PATTERN.matcher(raw);

        if (matcher.find()) {
            tags = matcher.group("tags");
            prefix = new NickMask(matcher.group("prefix"));
            command = EventType.getByName(matcher.group("command"));
            arguments = matcher.group("arguments");
        }
    }

    public String getTags() {
        return tags;
    }

    public NickMask getPrefix() {
        return prefix;
    }

    public EventType getEventType() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return String.format("[ServerMessage] tags: %s, prefix: %s, command: %s, arguments: %s", tags, prefix, command, arguments);
    }
}
