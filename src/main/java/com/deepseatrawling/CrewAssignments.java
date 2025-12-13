package com.deepseatrawling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrewAssignments {
    private int slotVarbitId;
    private int uniqueId;
    private String name;
    private boolean isPlayer;

    private int netIndex = -1;
    private int netDepth = -1;

    enum Assignment
    {
        OTHER(-1),

        PLAYER_TRAWLING_NET_STARBOARD(8),
        PLAYER_TRAWLING_NET_PORT(9),

        TRAWLING_NET_STARBOARD(13),
        TRAWLING_NET_PORT(14);

        private final int id;

        Assignment(int id) {
            this.id = id;
        }

        static Assignment fromId(int id) {
            for (Assignment assignment : values()) {
                if (assignment.id == id) {
                    return  assignment;
                }
            }
            return OTHER;
        }

        public int getNetIndex()
        {
            switch (this) {
                case TRAWLING_NET_STARBOARD:
                case PLAYER_TRAWLING_NET_STARBOARD: return 0;
                case TRAWLING_NET_PORT:
                case PLAYER_TRAWLING_NET_PORT: return 1;
                default: return -1;
            }
        }
    }

    private Assignment assignment = Assignment.OTHER;

    public void setAssignment(int posVarbitValue)
    {
        this.assignment = Assignment.fromId(posVarbitValue);
        this.netIndex = assignment.getNetIndex();
    }

}
