package org.gomadango0113.discord_connect.manager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.gomadango0113.discord_connect.Main;

public class DiscordManager extends ListenerAdapter {

    private static JDA jda;
    private static final Main instance;
    private static final FileConfiguration config;

    static {
        instance = Main.getInstance();
        config = instance.getConfig();
    }

    public static void startBot() throws InterruptedException {
        if (jda == null) {
            String token = config.getString("bot_token");
            jda = JDABuilder.createDefault(token)
                    .addEventListeners(new DiscordManager())
                    .build();

            jda.updateCommands()
                    .queue();

            jda.awaitReady();

            Bukkit.getLogger().info("[DiscordConnect] Botの準備ができました!");
        }
    }

    public static void stopBot() {
        if (jda != null) {
            jda.shutdownNow();
        }
    }

    public static JDA getJDA() {
        return jda;
    }

    public static TextChannel getSendChannel() {
        String channel_id = config.getString("send_channel_id");
        return jda.getTextChannelById(channel_id);
    }

}
