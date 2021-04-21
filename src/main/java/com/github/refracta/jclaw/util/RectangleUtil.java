package com.github.refracta.jclaw.util;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class RectangleUtil {
    public static Point getCenterPoint(Rectangle rectangle) {
        return new Point((int) rectangle.getCenterX(), (int) rectangle.getCenterY());
    }

    public static List<Point> getCenterPointList(List<Rectangle> rectangleList) {
        return rectangleList.stream().map(rectangle -> new Point((int) rectangle.getCenterX(), (int) rectangle.getCenterY())).collect(Collectors.toList());
    }
}
