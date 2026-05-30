package net.mochinekoserver.car_game.command;

import net.mochinekoserver.car_game.util.PluginUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("global")) {
            PluginUtil.sendGlobalInfoMessage(send.getName() + ":" + args[0]);
        }
        return false;
    }

}
