package at.franziskusdomig.irc;

import java.util.HashSet;
import java.util.Set;

public class Client {

    private Set<Connection> connections = new HashSet<>();

    public Client() {

    }

    public ServerConnection createServer() {
        ServerConnection serverConnection = new ServerConnection(this);

        this.connections.add(serverConnection);

        return serverConnection;
    }

    public void processData(Connection connection) {
        if (connection.getSocket() != null) {
            connection.processData();
        }
    }

    public void processForever(long timeout) {
        while (true) {
            processOnce(timeout);
        }
    }

    public void processOnce(long timeout) {
        try {
            connections.stream().forEach(this::processData);

            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}
