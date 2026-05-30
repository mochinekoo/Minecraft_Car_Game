package net.mochinekoserver.car_game.manager;

import net.mochinekoserver.car_game.status.KitType;

import java.util.UUID;

public abstract class KitBase {

    protected final UUID uuid;
    protected final KitType kitType;

    public KitBase(UUID uuid, KitType kitType) {
        this.uuid = uuid;
        this.kitType = kitType;
    }

    public UUID getUUID() {
        return uuid;
    }

    public KitType getKitType() {
        return kitType;
    }

    public abstract void release();

}
