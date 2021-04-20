package com.github.refracta.jclaw.core.system;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JClawUtil {
    // Source from https://stackoverflow.com/questions/2234476/how-to-detect-the-current-display-with-java/2234640
    private static java.util.List<GraphicsConfiguration> getConfigurations() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice def = ge.getDefaultScreenDevice();

        java.util.List<GraphicsConfiguration> cfgs = new ArrayList<GraphicsConfiguration>();
        cfgs.add(def.getDefaultConfiguration());

        for (GraphicsDevice gd : ge.getScreenDevices()) {
            if (gd != def) {
                cfgs.add(gd.getDefaultConfiguration());
            }
        }
        return cfgs;
    }

    public static List<Rectangle> getAllScreenRectangle() {
        return getConfigurations().stream().map(c -> c.getBounds()).collect(Collectors.toList());
    }

    public static Rectangle getDefaultScreenRectangle() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
    }

    public static Rectangle getUnionScreenRectangle() {
        Rectangle unionRectangle = new Rectangle(0, 0, 0, 0);
        for (GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            unionRectangle = unionRectangle.union(gd.getDefaultConfiguration().getBounds());
        }
        return unionRectangle;
    }
}
