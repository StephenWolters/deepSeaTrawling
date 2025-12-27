package com.deepseatrawling;

import net.runelite.client.ui.overlay.infobox.InfoBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TrawlingNetInfoBox extends InfoBox {
    private final DeepSeaTrawling plugin;
    private final DeepSeaTrawlingConfig config;

    public TrawlingNetInfoBox(BufferedImage image, DeepSeaTrawling plugin, DeepSeaTrawlingConfig config)
    {
        super(image, plugin);
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public boolean render()
    {
        return (plugin.fishQuantity > 0 && config.infoboxIsEnabled());
    }

    @Override
    public String getText()
    {
        return String.valueOf(plugin.fishQuantity);
    }

    @Override
    public String getTooltip()
    {
        return "Fish in nets: " + plugin.fishQuantity;
    }

    @Override
    public Color getTextColor()
    {
        return config != null
                ? config.fishCounterTextColour()
                : Color.WHITE;
    }

}
