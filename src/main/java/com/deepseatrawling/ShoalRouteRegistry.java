package com.deepseatrawling;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.coords.WorldPoint;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Singleton
public class ShoalRouteRegistry
{
    private static final String RESOURCE_NAME = "shoals.properties";

    @Getter
    private final Map<Integer, ShoalRoute> routesByWorldViewId = new HashMap<>();

    public void load() throws IOException
    {
        routesByWorldViewId.clear();

        Properties props = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/" + RESOURCE_NAME))
        {
            if (in == null)
            {
                throw new IOException("Could not find resource: " + RESOURCE_NAME);
            }
            props.load(in);
        }

        // Temporary accumulators so order doesn't matter
        Map<Integer, List<WorldPoint>> paths = new HashMap<>();
        Map<Integer, List<WorldPoint>> stops = new HashMap<>();

        for (String key : props.stringPropertyNames())
        {
            String value = props.getProperty(key);

            if (key.startsWith("shoalpath."))
            {
                int id = Integer.parseInt(key.substring("shoalpath.".length()));
                paths.put(id, parsePoints(value));
            }
            else if (key.startsWith("shoalstops."))
            {
                int id = Integer.parseInt(key.substring("shoalstops.".length()));
                stops.put(id, parsePoints(value));
            }
        }

        // Merge into final routes map
        Set<Integer> allIds = new HashSet<>();
        allIds.addAll(paths.keySet());
        allIds.addAll(stops.keySet());

        for (int id : allIds)
        {
            List<WorldPoint> p = paths.getOrDefault(id, List.of());
            List<WorldPoint> s = stops.getOrDefault(id, List.of());
            routesByWorldViewId.put(id, new ShoalRoute(p, s));
        }

        log.info("Loaded {} shoal routes from {}", routesByWorldViewId.size(), RESOURCE_NAME);
    }

    public ShoalRoute get(int worldViewId)
    {
        return routesByWorldViewId.getOrDefault(worldViewId, ShoalRoute.EMPTY);
    }

    private List<WorldPoint> parsePoints(String value)
    {
        if (value == null || value.trim().isEmpty())
        {
            return List.of();
        }

        return Arrays.stream(value.split("\\|"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(this::parsePoint)
                .collect(Collectors.toList());
    }

    private WorldPoint parsePoint(String token)
    {
        String[] parts = token.split(",\\s*");
        if (parts.length < 3)
        {
            throw new IllegalArgumentException("Invalid point token: " + token);
        }

        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        int plane = Integer.parseInt(parts[2].trim());
        return new WorldPoint(x, y, plane);
    }
}
