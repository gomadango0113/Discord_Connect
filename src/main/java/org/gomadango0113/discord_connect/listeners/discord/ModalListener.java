package org.gomadango0113.discord_connect.listeners.discord;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.gomadango0113.discord_connect.manager.VerifyManager;
import org.gomadango0113.discord_connect.manager.WhiteListManager;

import java.io.IOException;

public class ModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        Member memebr = event.getMember();
        if (event.getModalId().equals("verify_modal")) {
            String mcid_input = event.getValue("mcid_input").getAsString();
            String verify_input = event.getValue("verify_input").getAsString();

            try {
                OfflinePlayer player = Bukkit.getOfflinePlayer(mcid_input);
                boolean stop_verify = VerifyManager.stopVerify(player, Integer.parseInt(verify_input));
                if (stop_verify) {
                    event.reply("参加できるようになりました").setEphemeral(true).queue();
                    WhiteListManager.addWhiteList(player, memebr.getId());
                }
                else {
                    event.reply("コードが違います").setEphemeral(true).queue();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
