package org.gomadango0113.discord_connect.manager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.gomadango0113.discord_connect.Main;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class FormatManager {

    private static final Main instance;
    private static final FileConfiguration config;

    static {
        instance = Main.getInstance();
        config = instance.getConfig();
    }

    public static void setSendMessageFormat(String format) {
        config.set("send_format", format);
        instance.saveConfig();
    }

    public static String getRawFormat() {
        return config.getString("send_format", "<{#%PlayerName}>:{#%Message}");
    }

    public static String getFormatMessage(String name, String message) {
        String raw_format = getRawFormat();
        return raw_format.replace("{#%PlayerName}", name)
                .replace("{#%Message}", message);
    }

}
