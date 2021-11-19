
import listeners.UserStatusListener;
import logging.ChannelLogger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //TODO: Find a way to hide token
        String token = "";
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        // Add a listener which answers with "Pong!" if someone writes "^ping"
        log.debug("Initializing Ping-Pong test listener");
        ChannelLogger.logToLoggingChannel("Initializing Ping-Pong test listener", api);

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("^ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        //Add User Status listener
        log.debug("Initializing User Status listener");
        ChannelLogger.logToLoggingChannel("Initializing User Status listener", api);
        api.addListener(new UserStatusListener(api));

        // Print the invite url of your bot
        //System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}