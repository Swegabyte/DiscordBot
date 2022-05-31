package Util;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import java.util.Optional;

//Parse useful data out of events instead of processing in listener.
public class EventParser {

    public String getChannelName(TextChannel channel){
        String channelToString = channel.toString();
        return getLastValueOfToString(channelToString);
    }

    public String getMessage(Optional<Message> message){
        String channelToString = message.toString();
        return getLastValueOfToString(channelToString);
    }

    private static String getLastValueOfToString(String payload){
        //Increment by 2 to get rid of ": "
        //TODO: Maybe use regex for parsing and pass into this method for parsing type (name, message, etc)
        return payload.substring(payload.lastIndexOf(':')+2, payload.indexOf(')'));
    }

}
