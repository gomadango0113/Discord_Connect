package org.gomadango0113.discord_connect.listeners.mc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.gomadango0113.discord_connect.manager.WhiteListManager;

import java.io.IOException;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent event) throws IOException {
        Player player = event.getPlayer();

        if (!WhiteListManager.isWhiteList(player)) {
            event.setResult(PlayerLoginEvent.Result.KICK_WHITELIST);
            event.setKickMessage("あなたはホワイトリストに登録されていません。");
        }
    }

}
