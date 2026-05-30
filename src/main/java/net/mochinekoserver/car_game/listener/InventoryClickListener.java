package net.mochinekoserver.car_game.listener;

import net.mochinekoserver.car_game.gui.KitSelectGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        var view = event.getView();
        if (KitSelectGUI.isOpenInventory(event)) {
            KitSelectGUI.openInventory(player);
            event.setCancelled(true);
        }
    }

}
