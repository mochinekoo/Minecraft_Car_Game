package net.mochinekoserver.car_game.listener;

import net.mochinekoserver.car_game.Main;
import net.mochinekoserver.car_game.manager.GameManager;
import net.mochinekoserver.car_game.status.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        var gameManager = GameManager.getInstance();
        var status = gameManager.getStatus();
        var player = event.getEntity();
        var plugin = Main.getPlugin(Main.class);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.spigot().respawn();
        }, 5L);
    }

}
