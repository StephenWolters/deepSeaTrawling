package com.deepseatrawling;

import java.util.HashMap;
import java.util.Map;

public enum ShoalTypes {
    GIANT_KRILL(26, 27, 28, 29),
    HADDOCK(23, 24, 25),
    YELLOWFIN(20, 21, 22),
    HALIBUT(18, 19),
    BLUEFIN(16, 17),
    MARLIN(14, 15);

    private static final Map<Integer, ShoalTypes> SHOAL_ID = new HashMap<>();

    static {
        for (ShoalTypes type : values()) {
            for (int id : type.ids) {
                SHOAL_ID.put(id, type);
            }
        }
    }

    private final int[] ids;

    ShoalTypes(int... ids) {
        this.ids = ids;
    }

    public int[] getIds() {
        return ids;
    }

    public static ShoalTypes fromId(int id) {
        return SHOAL_ID.get(id);
    }
}