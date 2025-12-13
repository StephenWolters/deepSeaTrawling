package com.deepseatrawling;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.gameval.VarbitID;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class CrewAssignmentTracker {


    private final Client client;


    public CrewAssignmentTracker(Client client) {
        this.client = client;
    }
}

