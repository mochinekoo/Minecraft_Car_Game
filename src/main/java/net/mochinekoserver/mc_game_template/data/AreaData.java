package net.mochinekoserver.mc_game_template.data;

import net.mochinekoserver.mc_game_template.manager.DeserializedJson;

import java.util.Map;

public class AreaData extends DeserializedJson {

    public Map<String, GuardData> guard_data;

    public static class GuardData {
        public int[] start;
        public int[] end;
    }

}
