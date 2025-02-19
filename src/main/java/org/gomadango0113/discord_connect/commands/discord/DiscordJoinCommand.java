package org.gomadango0113.discord_connect.commands.discord;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.gomadango0113.discord_connect.manager.WhiteListManager;

import java.io.IOException;

public class DiscordJoinCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String cmd = event.getName();
        Member member = event.getMember();
        MessageChannelUnion channel = event.getChannel();

        if ("mc-join".equalsIgnoreCase(cmd)) {
            OptionMapping mcid_option = event.getOption("mcid");

            if (mcid_option != null) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(mcid_option.getAsString());
                try {
                    if (WhiteListManager.isWhiteList(player)) {
                        channel.sendMessage("すでに登録されています。").queue();
                    }
                    else {
                        WhiteListManager.addWhiteList(player, member.getId());
                        channel.sendMessage("マイクラ鯖へ参加できるようになりました。").queue();
                    }
                }
                catch (IOException e) {
                    channel.sendMessage("ファイルに問題が発生しました。").queue();
                    throw new RuntimeException(e);
                }
            }
            else {
                channel.sendMessage("引数がありません").queue();
            }
        }
    }

}
