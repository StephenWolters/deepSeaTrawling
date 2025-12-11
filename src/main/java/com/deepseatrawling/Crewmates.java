package com.deepseatrawling;

public class Crewmates {
    int uniqueId;
    String name;

    int index;

    enum Assignment
    {
        NONE(0),
        TRAWLING_NET_PORT(13),
        TRAWLING_NET_STARBOARD(14);

        private final int varbitId;

        Assignment(int varbitId) {
            this.varbitId = varbitId;
        }

        static Assignment fromId(int id) {
            for (Assignment assignment : values()) {
                if (assignment.varbitId == id) {
                    return  assignment;
                }
            }
            return NONE;
        }

        
    }

    Assignment assignment;

}
