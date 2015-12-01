package at.franziskusdomig.irc;

import org.junit.Assert;
import org.junit.Test;

public class FoldedCaseTest {

    @Test
    public void testToLowerCaseForAlphaNumericString() throws Exception {
        Assert.assertEquals("Alphanumeric string", "hello123", FoldedCase.toLowerCase("HeLlo123"));
    }

    @Test
    public void testToLowerCaseForBraces() throws Exception {
        Assert.assertEquals("String with braces", "hell[]o", FoldedCase.toLowerCase("Hell{}o"));
    }

    @Test
    public void testToLowercaseForAllSpecialChars() throws Exception {
        Assert.assertEquals("String with braces", "hell[]o[fo\\\\o]baz\\with~", FoldedCase.toLowerCase("hell[]o{fo||o}BAZ\\with^"));
    }
}