package net.mochinekoserver.car_game.data;

import net.mochinekoserver.car_game.manager.DeserializedJson;

import java.util.Map;

public class AreaData extends DeserializedJson {

    public Map<String, GuardData> guard_data;

    public static class GuardData {
        public int[] start;
        public int[] end;
    }

}
