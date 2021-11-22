package listeners;

import logging.ChannelLogger;
import org.javacord.api.DiscordApi;
import org.javacord.api.event.user.UserChangeStatusEvent;
import org.javacord.api.listener.user.UserChangeStatusListener;

public class UserStatusListener implements UserChangeStatusListener {
    private DiscordApi api;

    public UserStatusListener(DiscordApi discordApi) {
        api = discordApi;
    }

    @Override
    public void onUserChangeStatus(UserChangeStatusEvent userChangeStatusEvent) {

        String logStatement = "[STATUS CHANGE] " +  userChangeStatusEvent.getUserIdAsString() + " is now " +
                userChangeStatusEvent.getNewStatus().getStatusString();
        ChannelLogger.logToLoggingChannel(logStatement, api);
    }
}
