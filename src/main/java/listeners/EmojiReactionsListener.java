package listeners;

import Util.EventParser;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.emoji.KnownCustomEmoji;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class EmojiReactionsListener implements ReactionAddListener {

    private final EventParser eventParser = new EventParser();

    //Yee board functionality
    @Override
    public void onReactionAdd(ReactionAddEvent reactionAddEvent) {
        //Move this to constructor? Dont need to grab emoji every time.
        Collection<KnownCustomEmoji> yeeEmojis = reactionAddEvent.getApi().getCustomEmojisByName("yee");
        Emoji yee = new ArrayList<>(yeeEmojis).get(0);
        if(reactionAddEvent.getEmoji().equalsEmoji(yee)){
        // create embed with message
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("YEE BOARD")
                    .setAuthor(reactionAddEvent.getMessageAuthor().get())
                    //.setImage(reactionAddEvent.getMessageAuthor().get().getAvatar())
                    .addField("Channel", eventParser.getChannelName(reactionAddEvent.getChannel()))
                    .addInlineField("Message", eventParser.getMessage(reactionAddEvent.getMessage()))
                    .setColor(Color.GREEN);
            // Send the embed
            TextChannel yeeBoardChannel =  reactionAddEvent.getApi().getTextChannelById("913990761102581772").get();
            // add to yee board
            new MessageBuilder().setEmbed(embed).send(yeeBoardChannel);
        }
    }
}
