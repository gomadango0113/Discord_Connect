package org.gomadango0113.discord_connect.listeners.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.gomadango0113.discord_connect.manager.DiscordManager;
import org.gomadango0113.discord_connect.manager.FormatManager;

import java.text.Format;

public class DiscordSendListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Member member = event.getMember();
        Message message = event.getMessage();
        TextChannel send_channel = DiscordManager.getSendChannel();
        JDA jda = event.getJDA();
        SelfUser self = jda.getSelfUser();

        if (member != null) {
            User user = member.getUser();
            if (self.getIdLong() != user.getIdLong()) {
                String format = FormatManager.getFormatMessage(user.getName(), message.getContentRaw());
                Bukkit.broadcastMessage("[Discord]" + format);
            }
        }
    }

}
