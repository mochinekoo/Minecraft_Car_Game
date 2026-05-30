package net.mochinekoserver.car_game.command;

import net.mochinekoserver.car_game.manager.CarManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CarCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("car")) {
            var carManager = CarManager.getInstance();
            var car = carManager.getCar((Player)send);
            car.ride();
        }
        return false;
    }
    
}
