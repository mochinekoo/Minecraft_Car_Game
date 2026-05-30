package net.mochinekoserver.car_game.manager;

import net.mochinekoserver.car_game.data.Car;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CarManager {

    private final static CarManager instance = new CarManager();
    private final Map<UUID, Car> carMap = new HashMap<>();

    private CarManager() {}

    public static CarManager getInstance() {
        return instance;
    }

    public Car getCar(@Nonnull Player player) {
        if (!carMap.containsKey(player.getUniqueId())) {
            carMap.put(player.getUniqueId(), new Car(player.getUniqueId()));
        }
        return carMap.get(player.getUniqueId());
    }

}
