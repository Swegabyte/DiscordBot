package logging;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ChannelLogger {
    public static final String  LOGGING_CHANNEL = "bot-logging";
    private static Logger log = LoggerFactory.getLogger(ChannelLogger.class);


    public static void logToLoggingChannel(String logStatement,DiscordApi api){

        Optional<TextChannel> channel = api.getTextChannelById(LOGGING_CHANNEL);

        if(channel.isPresent()){
            new MessageBuilder().setContent(logStatement).send(channel.get());
        }  else {
            //TODO: Add logic to add bot-logging channel, then log statements.
            log.error("Channel is not present, cannot log to channel...");
        }
    }

}
