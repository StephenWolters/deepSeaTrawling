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

    enum Assignment
    {
        OTHER(-1),
        TRAWLING_NET_PORT(13),
        TRAWLING_NET_STARBOARD(14);

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
                case TRAWLING_NET_PORT: return 0;
                case TRAWLING_NET_STARBOARD: return 1;
                default: return -1;
            }
        }

        public boolean isNet() {
            return this == TRAWLING_NET_PORT || this == TRAWLING_NET_STARBOARD;
        }
    }

    private Assignment assignment = Assignment.OTHER;

    public void setAssignment(int posVarbitValue)
    {
        this.assignment = Assignment.fromId(posVarbitValue);
        this.netIndex = assignment.getNetIndex();
    }

}
