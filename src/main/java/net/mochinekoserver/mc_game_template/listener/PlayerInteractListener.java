package net.mochinekoserver.mc_game_template.listener;

import net.mochinekoserver.mc_game_template.gui.KitSelectGUI;
import net.mochinekoserver.mc_game_template.util.PluginItemFactory;
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
