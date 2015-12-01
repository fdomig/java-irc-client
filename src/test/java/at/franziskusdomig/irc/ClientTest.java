package at.franziskusdomig.irc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client();
    }

    @Test
    public void testCreateServerReturnsServerConnection() throws Exception {
        ServerConnection server = client.createServer();
        Assert.assertNotNull(server);
    }

}