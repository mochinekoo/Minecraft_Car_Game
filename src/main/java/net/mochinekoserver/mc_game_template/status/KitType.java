package net.mochinekoserver.mc_game_template.status;

import net.mochinekoserver.mc_game_template.manager.KitBase;

public enum KitType {

    ;

    private final Class<? extends KitBase> kit_class;

    KitType(Class<? extends KitBase> kit_class) {
        this.kit_class = kit_class;
    }


    public Class<? extends KitBase> getKitClass() {
        return kit_class;
    }
}
