package at.franziskusdomig.irc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum EventType {

    RPL_WELCOME("001"),
    RPL_YOURHOST("002"),
    RPL_CREATED("003"),
    RPL_MYINFO("004"),
    RPL_ISUPPORT("005"),

    RPL_YOURID("042"),

    RPL_LUSERCLIENT("251"),
    RPL_LUSEROP("252"),
    RPL_LUSERUNKNOWN("253"),
    RPL_LUSERCHANNELS("254"),
    RPL_LUSERME("255"),

    RPL_LOCALUSERS("265"),
    RPL_GLOBALUSERS("266"),

    RPL_TOPIC("332"),
    RPL_TOPICWHOTIME("333"),
    RPL_NAMREPLY("353"),
    RPL_ENDOFNAMES("366"),

    RPL_MOTD("372"),
    RPL_MOTDSTART("375"),
    RPL_ENDOFMOTD("376"),

    ERR_NICKNAMEINUSE("433"),
    ERR_NOTREGISTERED("451"),
    ERR_NEEDMOREPARAMS("461"),

    ERROR("ERROR"),
    PING("PING"),
    TOPIC("TOPIC"),
    MODE("MODE"),
    JOIN("JOIN"),
    PART("PART"),
    PRIVMSG("PRIVMSG"),

    UNRECOGNIZED_CODE("UNRECOGNIZED_CODE");

    private final String name;

    private static final Logger logger = Logger.getLogger(EventType.class.getName());

    private static final Map<String, EventType> codes = new HashMap<>();

    static {
        Arrays.asList(values())
                .stream()
                .forEach(code -> codes.put(code.name, code));
    }

    EventType(String name) {
        this.name = name;
    }

    public static EventType getByName(String name) {
        EventType eventType = codes.get(name);

        if (eventType == null) {
            logger.log(Level.INFO, "Missing reply code mapping for: {0}", name);
            eventType = UNRECOGNIZED_CODE;
        }

        return eventType;
    }

}
