package net.mochinekoserver.mc_game_template.listener;

import net.mochinekoserver.mc_game_template.manager.ScoreboardManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        var scoreboardManager = ScoreboardManager.getInstance(player.getUniqueId());
        scoreboardManager.setScoreboard();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        var scoreboardManager = ScoreboardManager.getInstance(player.getUniqueId());
        scoreboardManager.release();
    }

}
