package listeners;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;


public class ChannelCleanUpListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessageContent().equalsIgnoreCase("^cleanup")) {
            messageCreateEvent.getChannel().sendMessage("Cleaning up messages!");
            TextChannel textChannel = messageCreateEvent.getChannel();
            textChannel.getMessagesAsStream().forEach(Message::delete);
            messageCreateEvent.getChannel().sendMessage("Channel messages deleted.");
        }
    }
}
