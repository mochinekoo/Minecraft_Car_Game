package net.mochinekoserver.car_game.data;

import net.mochinekoserver.car_game.Main;
import net.mochinekoserver.car_game.manager.CarManager;
import net.mochinekoserver.car_game.manager.ConfigManager;
import net.mochinekoserver.car_game.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.UUID;

public class Car {

    private final UUID uuid;
    private ArmorStand armorStand;
    private BukkitTask bukkitTask;

    public Car(UUID uuid) {
        var gameManager = GameManager.getInstance();
        var configManager = ConfigManager.getInstance();
        var world = configManager.getGameWorld();
        this.uuid = uuid;
        this.armorStand = world.spawn(getPlayer().getLocation(), ArmorStand.class);

        this.bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                var player = getPlayer();
                var playerLoc = player.getLocation();
                var playerDir = playerLoc.getDirection().clone();
                var playerVec = player.getVelocity();
                playerDir.setY(0);

                move(playerDir);
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 2L);
    }

    public void ride() {
        armorStand.addPassenger(getPlayer());
    }

    public void unRide() {
        armorStand.remove();
        armorStand = null;
    }

    public void move(Vector vector) {
        armorStand.setVelocity(vector);
    }

    public Location getCarLocation() {
        return armorStand.getLocation();
    }

    public Vector getCarVelocity() {
        return armorStand.getVelocity();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
