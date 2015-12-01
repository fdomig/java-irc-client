package at.franziskusdomig.irc;

import java.net.Socket;

public abstract class Connection {

    protected final Client client;

    public abstract Socket getSocket();

    public Connection(Client client) {
        this.client = client;
    }

    public abstract void processData();

}
