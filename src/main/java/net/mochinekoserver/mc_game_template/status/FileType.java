package net.mochinekoserver.mc_game_template.status;

import net.mochinekoserver.mc_game_template.data.AreaData;
import net.mochinekoserver.mc_game_template.manager.DeserializedJson;

public enum FileType {

    AREA_DATA("area_data.json", AreaData.class);

    private final String name;
    private final Class<? extends DeserializedJson> deserialized_class;

    FileType(String name, Class<? extends DeserializedJson> deserialized_class) {
        this.name = name;
        this.deserialized_class = deserialized_class;
    }

    public String getName() {
        return name;
    }

    public Class<? extends DeserializedJson> getDeserializedClass() {
        return deserialized_class;
    }

}
