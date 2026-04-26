package net.mochinekoserver.mc_game_template.manager;

import net.mochinekoserver.mc_game_template.status.KitType;

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
