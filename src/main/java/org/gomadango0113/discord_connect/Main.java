package org.gomadango0113.discord_connect;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.gomadango0113.discord_connect.listeners.mc.PlayerChatListener;
import org.gomadango0113.discord_connect.manager.DiscordManager;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListener();
        registerCommand();
        saveDefaultConfig();

        try {
            DiscordManager.startBot();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        getLogger().info( "[DiscordConnect] プラグインの準備ができました!");
    }

    @Override
    public void onDisable() {
        DiscordManager.stopBot();
    }

    private void registerCommand() {

    }

    private void registerListener() {
        PluginManager plm = getServer().getPluginManager();

        plm.registerEvents(new PlayerChatListener(), this);
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

}
