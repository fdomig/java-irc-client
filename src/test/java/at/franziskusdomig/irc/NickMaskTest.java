package at.franziskusdomig.irc;

import org.junit.Assert;
import org.junit.Test;

public class NickMaskTest {

    @Test(expected = AssertionError.class)
    public void testNullMaskThrows() throws Exception {
        new NickMask(null);
    }

    @Test
    public void testValidNick() throws Exception {
        Assert.assertEquals("Nick", "nick", new NickMask("nick!user@host.local").nick());
    }

    @Test
    public void testValidUser() throws Exception {
        Assert.assertEquals("User", "user", new NickMask("nick!user@host.local").user());
    }

    @Test
    public void testValidUserHost() throws Exception {
        Assert.assertEquals("User", "user@host.local", new NickMask("nick!user@host.local").userHost());
    }

    @Test
    public void testValidHost() throws Exception {
        Assert.assertEquals("User", "host.local", new NickMask("nick!user@host.local").host());
    }
}