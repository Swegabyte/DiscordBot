import listeners.ChannelMessageListener;
import listeners.UserStatusListener;
import logging.ChannelLogger;
import logging.Log4JInitializer;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static void main(String[] args) {

        Logger log = Log4JInitializer.initializeLogger(Main.class);

        //Initialize bot and connect to server.
        String token = System.getenv("DISC_TOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        ChannelLogger.initializeLoggingChannel(api);
        ChannelLogger.logToLoggingChannel("Bot starting up.", api);

        // Add a listener which answers with "Pong!" if someone writes "^ping"
        //Using lambda expression as practice example
        //The rest with be class based to keep their functionality clean and out of main.
        log.info("Initializing Ping-Pong test listener");
        ChannelLogger.logToLoggingChannel("Initializing Ping-Pong test listener", api);

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("^ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        //Add User Status listener
        log.info("Initializing User Status listener");
        ChannelLogger.logToLoggingChannel("Initializing User Status listener", api);
        api.addListener(new UserStatusListener(api));

        //Add Channel Cleanup listener
        log.info("Initializing Channel Cleanup listener");
        ChannelLogger.logToLoggingChannel("Initializing Channel Cleanup listener", api);
        api.addListener(new ChannelMessageListener());

        Thread killMessage = new Thread(() -> ChannelLogger.logToLoggingChannel("Bot is now kill", api));
        Runtime.getRuntime().addShutdownHook(killMessage);

        // Print the invite url of your bot
        log.info("[INVITE] You can invite the bot by using the following url: " + api.createBotInvite());
    }

}