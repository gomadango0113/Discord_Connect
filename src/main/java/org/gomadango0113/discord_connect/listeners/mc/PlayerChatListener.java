package org.gomadango0113.discord_connect.listeners.mc;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.gomadango0113.discord_connect.manager.DiscordManager;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (DiscordManager.getJDA() != null) {
            TextChannel send_channel = DiscordManager.getSendChannel();

            send_channel.sendMessage("[Minecraft]<" + player.getName() + ">: " + message).queue();
        }
    }
}
