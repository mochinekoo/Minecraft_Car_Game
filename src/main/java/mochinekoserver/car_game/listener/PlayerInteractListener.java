package mochinekoserver.car_game.listener;

import mochinekoserver.car_game.gui.KitSelectGUI;
import mochinekoserver.car_game.util.PluginItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        var player = event.getPlayer();
        var playerInv = player.getInventory();
        var mainHand = playerInv.getItemInMainHand();
        var action = event.getAction();
        if (action == Action.PHYSICAL) return;

        if (mainHand.isSimilar(PluginItemFactory.createKitSelector())) {
            KitSelectGUI.openInventory(player);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        var player = event.getPlayer();
        var playerInv = player.getInventory();
        var playerLoc = player.getLocation();
        var mainHand = playerInv.getItemInMainHand();
        var action = event.getAction();
        Bukkit.getLogger().info(action.name());
        if (action == Action.PHYSICAL) return;

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            var playerDir = playerLoc.getDirection().normalize().clone();
            var vec = playerDir.multiply(2);

            var armorStand = player.getWorld().spawn(playerLoc, ArmorStand.class);
            armorStand.setVelocity(vec);
            Bukkit.getLogger().info(vec.toString());
        }
    }
}
