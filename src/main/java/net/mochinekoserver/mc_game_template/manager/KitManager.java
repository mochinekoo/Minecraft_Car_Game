package net.mochinekoserver.mc_game_template.manager;

import net.mochinekoserver.mc_game_template.status.KitType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KitManager {

    private static KitManager instance;
    private final static Map<UUID, KitBase> kit_map = new HashMap<>();

    private KitManager() {}

    public static KitManager getInstance() {
        if (instance == null) instance = new KitManager();
        return instance;
    }

    public void setKit(@Nonnull UUID uuid, @Nonnull KitBase kit) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        kit_map.put(uuid, kit);
    }

    /**
     * 現在のキットを取得する関数
     * @param uuid 取得したいプレイヤー
     */
    public KitBase getKit(@Nonnull UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return kit_map.getOrDefault(uuid, null);
    }

}
