package at.franziskusdomig.irc;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerConnection extends Connection {

    private Socket socket;
    private boolean isConnected;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Map<EventType, Set<EventHandler>> handlers = new HashMap<>();

    public ServerConnection(Client client) {
        super(client);
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    public void connect(String address, int port, String nick, SocketFactory socketFactory) {
        try {
            if (isConnected) {
                disconnect("Changing servers.");
            }
            setupDefaultEventHandler();
            socket = socketFactory.create(address, port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader((socket.getInputStream())));

            isConnected = true;

            nick(nick);
            user(nick, nick);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void processData() {
        try {
            String line = reader.readLine();

            if (line == null) {
                disconnect("Connection reset by peer.");
            }

            processLine(line);

        } catch (IOException e) {
            disconnect("Connection reset by peer.");
            throw new RuntimeException(e);
        }
    }

    private void processLine(String line) {
        System.out.println(line);

        ServerMessage serverMessage = new ServerMessage(line);
        handleEvent(new Event(serverMessage));
    }

    private void handleEvent(Event event) {
        getEventTypeHandlers(event.getType())
                .forEach(eventHandler -> eventHandler.handle(event));
    }

    private void setupDefaultEventHandler() {
        addEventHandler(EventType.PING, event -> pong(event.getArguments()));
    }

    public void addEventHandler(EventType eventType, EventHandler handler) {
        Set<EventHandler> typeHandlers = getEventTypeHandlers(eventType);

        typeHandlers.add(handler);
    }

    private Set<EventHandler> getEventTypeHandlers(EventType eventType) {
        Set<EventHandler> typeHandlers = handlers.get(eventType);

        if (typeHandlers == null) {
            typeHandlers = new HashSet<>();
            handlers.put(eventType, typeHandlers);
        }

        return typeHandlers;
    }

    private void pong(String argument) {
        sendRaw(String.format("PONG %s", argument));
    }

    synchronized private void sendRaw(String cmd) {
        try {
            System.out.println(">>> " + cmd);

            writer.write(String.format("%s\r\n", cmd));
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnect(String message) {
        if (!isConnected) {
            return;
        }

        quit(message);
    }

    public void quit(String message) {
        sendRaw("QUIT :" + message);
    }

    public void user(String userName, String realName) {
        sendRaw(String.format("USER %s 8 * :%s", userName, realName));
    }

    public void nick(String newNick) {
        sendRaw(String.format("NICK %s", newNick));
    }

    public void join(String channel) {
        sendRaw(String.format("JOIN %s", channel));
    }

    public void privmsg(String target, String message) {
        sendRaw(String.format("PRIVMSG %s :%s", target, message));
    }
}
