package org.gomadango0113.discord_connect.manager;

import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;
import org.gomadango0113.discord_connect.Main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class VerifyManager {

    private static final Map<UUID, Integer> verify_map = new HashMap<>();
    private static boolean whitelist_verify = true;

    public static void startVerify(OfflinePlayer player) throws IOException {
        if (!WhiteListManager.isWhiteList(player)) {
            UUID uuid = player.getUniqueId();
            if (!verify_map.containsKey(uuid)) {
                int code = new Random().nextInt(10000);

                verify_map.put(uuid, code);

                new BukkitRunnable() {
                    int restart_verify_time = 0;
                    @Override
                    public void run() {
                        if (verify_map.containsKey(uuid)) {
                            if (restart_verify_time == 60*15) {
                                int code = new Random().nextInt(10000);
                                verify_map.put(uuid, code);
                                restart_verify_time=0;
                            }
                            else {
                                restart_verify_time++;
                            }
                        }
                        else {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Main.getInstance(), 0L, 20L);
            }
        }
    }

    public static boolean stopVerify(OfflinePlayer player, int code) throws IOException {
        if (!WhiteListManager.isWhiteList(player) && verify_map.containsKey(player.getUniqueId())) {
            Integer player_verify = verify_map.get(player.getUniqueId());

            if (player_verify == code) {
                verify_map.remove(player.getUniqueId());
                return true;
            }
        }
        return false;
    }

    public static int getCode(OfflinePlayer player) {
        return verify_map.get(player.getUniqueId());
    }

    public static boolean isWhitelistVerify() {
        return whitelist_verify;
    }
}
