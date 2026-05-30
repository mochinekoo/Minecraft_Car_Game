package net.mochinekoserver.car_game.listener;

import net.mochinekoserver.car_game.gui.KitSelectGUI;
import net.mochinekoserver.car_game.util.PluginItemFactory;
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
}
