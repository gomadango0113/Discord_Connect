package org.gomadango0113.discord_connect.manager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.gomadango0113.discord_connect.Main;
import org.gomadango0113.discord_connect.commands.discord.DiscordJoinCommand;
import org.gomadango0113.discord_connect.listeners.discord.DiscordSendListener;
import org.gomadango0113.discord_connect.listeners.discord.ModalListener;

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
                    .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new DiscordManager())
                    .addEventListeners(new DiscordSendListener(), new ModalListener())
                    .addEventListeners(new DiscordJoinCommand())
                    .build();

            jda.updateCommands()
                    .addCommands(Commands.slash("mc-join", "マイクラ鯖へ参加するコマンド")
                            .addOption(OptionType.STRING, "mcid", "マイクラのID（例：Blockgrass）"))
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
