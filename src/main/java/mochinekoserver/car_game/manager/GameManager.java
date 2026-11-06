package mochinekoserver.car_game.manager;

import mochinekoserver.car_game.Main;
import mochinekoserver.car_game.status.GameStatus;
import mochinekoserver.car_game.util.PluginUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.*;

public class GameManager extends GameBase {

    private static GameManager instance = null;

    private final Map<UUID, Car> ridePlayerTaskMap = new HashMap<>();

    public GameManager() {
        super("Game");
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void reset() {
        this.mainTask.cancel();
        this.mainTask = null;
        this.time = 0;
    }

    @Override
    public int start() {
        if (status != GameStatus.WAITING) return -1;
        this.mainTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (status == GameStatus.WAITING || status == GameStatus.COUNTING) {
                    updateWaitCountScene();
                }
                else if (status == GameStatus.RUNNING) {
                    updateRunningScene();
                }
                else if (status == GameStatus.ENDING) {
                    updateEndingScene();
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);

        return 0;
    }

    private void updateWaitCountScene() {
        if (status != GameStatus.WAITING && status != GameStatus.COUNTING) return;
        var configManager = ConfigManager.getInstance();
        var teamManager = TeamManager.getInstance();

        if (countTime <= 0) { //0秒以下
            //ゲーム開始の処理
            PluginUtil.sendGlobalInfoMessage("ゲーム開始!");
            status = GameStatus.RUNNING;
            teamManager.assignTeam(); //チームを割り当てる
            for (Player online : Bukkit.getOnlinePlayers()) {
                var scoreboardManager = ScoreboardManager.getInstance(online.getUniqueId());
                var joinTeam = teamManager.getJoinGameTeam(online);
                var teamSpawn = configManager.getTeamSpawnLocation(joinTeam);
                scoreboardManager.setScoreboard(); //スコアボードを設定する
                online.teleport(teamSpawn); //チームのスポーンにテレポート
            }
        }
        else {
            //カウントの処理
            status = GameStatus.COUNTING;
            PluginUtil.sendGlobalInfoMessage("ゲーム開始まであと" + countTime + "秒です。");
            countTime--;
        }
    }

    private void updateRunningScene() {
        if (status != GameStatus.RUNNING) return;
        time--;
        if (time <= 0) { //ゲーム時間が0秒以下になった場合
            status = GameStatus.ENDING;
        }
    }

    private void updateEndingScene() {
        if (status != GameStatus.ENDING) return;
        PluginUtil.sendGlobalInfoMessage("ゲーム終了!");
        reset();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean canStart() {
        return true;
    }

    public void ride(UUID uuid) {
        if (!ridePlayerTaskMap.containsKey(uuid)) {
            Car car = new Car(uuid);
            ridePlayerTaskMap.put(uuid, car);
            car.ride();
        }
    }

    public void ride(CommandSender send) {
        if (send instanceof Player player) {
            ride(player.getUniqueId());
        }
    }

    public void unRide(UUID uuid) {
        if (ridePlayerTaskMap.containsKey(uuid)) {
            ridePlayerTaskMap.get(uuid).unRide();
            ridePlayerTaskMap.remove(uuid);
        }
    }

    public void unRide(CommandSender send) {
        if (send instanceof Player player) {
            unRide(player.getUniqueId());
        }
    }

    public static class Car {

        private final UUID uuid;
        private ArmorStand armorStand;
        private BukkitTask task;

        public Car(UUID uuid) {
            this.uuid = uuid;
            this.armorStand = null;
            this.task = null;
        }

        public UUID getUUID() {
            return uuid;
        }

        public void ride() {
            Player player = getPlayer();
            if (player == null) return;
            if (armorStand == null) {
                World world = player.getWorld();
                armorStand = world.spawn(player.getLocation(), ArmorStand.class);
                armorStand.addPassenger(player);
            }
            if (task == null) {
                task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Vector dir = player.getLocation().getDirection().clone().setY(0);
                        if (dir.lengthSquared() > 0) dir.normalize();

                        final double speed = 2.0f;
                        Vector vel = dir.multiply(speed);
                        vel.setY(armorStand.getVelocity().getY());

                        Location front = armorStand.getLocation().add(dir.clone().multiply(0.8));
                        Block feet = front.getBlock();
                        Block up1 = feet.getRelative(BlockFace.UP);
                        Block up2 = up1.getRelative(BlockFace.UP);

                        if (armorStand.isOnGround()  && !feet.isPassable()  && up1.isPassable()  && up2.isPassable()) {
                            vel.setY(0.5);
                        }

                        armorStand.setVelocity(vel);

                        if (armorStand.getPassengers().isEmpty()) {
                            unRide();
                        }
                    }
                }.runTaskTimer(Main.getPlugin(Main.class), 0L, 2L);
            }
        }

        public void unRide() {
            task.cancel();
            task = null;
            armorStand.remove();
            armorStand = null;
        }

        public Player getPlayer() {
            return Bukkit.getPlayer(uuid);
        }
    }
}
