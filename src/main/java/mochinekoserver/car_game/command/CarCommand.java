package mochinekoserver.car_game.command;

import mochinekoserver.car_game.manager.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("car")) {
            var gameManager = GameManager.getInstance();
            if (args[0].equalsIgnoreCase("ride")) {
                gameManager.ride(send);
            }
            else if (args[0].equalsIgnoreCase("unride")) {
                gameManager.unRide(send);
            }

        }
        return false;
    }
}
