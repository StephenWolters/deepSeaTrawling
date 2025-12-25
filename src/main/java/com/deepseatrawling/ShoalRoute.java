package com.deepseatrawling;

import lombok.Value;
import net.runelite.api.coords.WorldPoint;

import java.util.List;

@Value
public class ShoalRoute
{
    List<WorldPoint> pathPoints;
    List<WorldPoint> stopPoints;

    public static final ShoalRoute EMPTY = new ShoalRoute(List.of(), List.of());
}