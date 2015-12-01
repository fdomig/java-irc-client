package at.franziskusdomig.irc;

public class Event {

    private final ServerMessage message;

    public Event(ServerMessage message) {
        this.message = message;
    }

    private boolean isChannel(String str) {
        return str.matches("^[#$!&]");
    }

    public EventType getType() {
        return message.getEventType();
    }

    public NickMask getSource() {
        return message.getPrefix();
    }

    public NickMask getTarget() {
        return message.getPrefix();
    }

    public String getArguments() {
        return message.getArguments();
    }
}
