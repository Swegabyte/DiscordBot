package listeners;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.HashMap;
import java.util.Map;


public class ChannelMessageListener implements MessageCreateListener {

    public final Map<String, String> featuresList = initFeatures();

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessageContent().equalsIgnoreCase("^cleanup")) {
            cleanUpMessage(messageCreateEvent);
        }
        else if(messageCreateEvent.getMessageContent().equalsIgnoreCase("^help")){
            helpMessage(messageCreateEvent);
        }
    }

    private void cleanUpMessage(MessageCreateEvent event){
        event.getChannel().sendMessage("Cleaning up messages!");
        TextChannel textChannel = event.getChannel();
        textChannel.getMessagesAsStream().forEach(Message::delete);
        event.getChannel().sendMessage("Channel messages deleted.");
    }

    private void helpMessage(MessageCreateEvent event){
        //Send user a message listing out possible features
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Help results")
                .setDescription("List of features you can use!");

        for (Map.Entry<String,String> entry : featuresList.entrySet()) {
            embed.addField(entry.getKey(), entry.getValue());
        }

        event.getChannel().sendMessage(embed);
    }

    //TODO: Make sure this gets updated with each new functionality!!!!!!
    //OR PUT IT SOMEWHERE BETTER?
    private Map<String, String> initFeatures(){
        Map<String, String> featureMap = new HashMap<>();
        featureMap.put("^help", "Sends a list of possible commands.");
        featureMap.put("^ping", "Responds Pong.");
        featureMap.put("^cleanUp", "Cleans up messages in the channel.");
        return featureMap;
    }
}
