import Util.EventParser;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilTests {

    @Mock
    private TextChannel testChannel;

    @Mock
    private Message message;

    private EventParser eventParser;

    @Before
    public void setUp(){
        when(testChannel.toString()).thenReturn(": test-channel)");
        when(message.toString()).thenReturn(": this is a test message)");
        eventParser = new EventParser();
    }

    @Test
    public void testGetChannelName(){
        String actualName = eventParser.getChannelName(testChannel);
        Assert.assertEquals("test-channel", actualName);
    }

    @Test
    public void testGetMessage(){
        Optional<Message> optionalMessage = Optional.of(message);
        String actualMessage = eventParser.getMessage(optionalMessage);
        Assert.assertEquals("this is a test message", actualMessage);
    }

}
