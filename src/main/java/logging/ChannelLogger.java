package logging;

import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.server.Server;
import java.util.Collection;
import java.util.Optional;

public class ChannelLogger {
    public static final String  LOGGING_CHANNEL = "bot-logging";
    public static final String  SERVER_NAME = "The MemeSpeak";
    public static final String MEMESPEAK_ID = "499805062315507713";

    public static void logToLoggingChannel(String logStatement,DiscordApi api){
        Optional<TextChannel> logChannel = findChannelByName(LOGGING_CHANNEL, api);
        new MessageBuilder().setContent(logStatement).send(logChannel.get());

    }

    private static Optional<TextChannel> findChannelByName(String channelName, DiscordApi api){
        Collection<TextChannel> channel = api.getTextChannelsByName(channelName);
        Optional<TextChannel> logChannel = channel.stream().findFirst();
        return logChannel;
    }

    public static void initializeLoggingChannel(DiscordApi api){
        Logger log = Log4JInitializer.initializeLogger(ChannelLogger.class);
        Optional<TextChannel> logChannel = findChannelByName(LOGGING_CHANNEL, api);
        if (!logChannel.isPresent()) {
            log.warn("[CHANNEL LOGGING] Channel is not present, creating channel and logging.");
            Optional<Server> server = api.getServerById(MEMESPEAK_ID);
            server.ifPresent(value -> value.createTextChannelBuilder()
                    .setName("bot-logging")
                    .setTopic("logging for bots")
                    .create());
        } else {
            log.info("[CHANNEL LOGGING] Logging channel exists, skipping");
        }
    }
}
