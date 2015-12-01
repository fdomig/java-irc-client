package at.franziskusdomig.irc;

import java.io.IOException;
import java.net.Socket;

public class SocketFactory {

    public Socket create(String address, int port) throws IOException {
        return new Socket(address, port);
    }
}
