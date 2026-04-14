package net.mochinekoserver.mc_game_template.status;

public enum ItemType {
    UNDROPPABLE("ドロップ不可能"),
    UNTRANSFERABLE("譲渡不可能"),
    KIT_ITEM("キットアイテム");

    private final String name; //あまり使わない

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
