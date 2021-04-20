package com.github.refracta.jclaw.core.system;

import com.github.refracta.jclaw.awt.AWTDesktop;
import com.github.refracta.jclaw.awt.AWTRobot;

import java.awt.*;

public class JClawMouse {
    public static void move(Rectangle screenRectangle, int x, int y) {
        AWTDesktop.getRobot().mouseMove(screenRectangle.x + x, screenRectangle.y + y);
    }

    public static void moveByPoint(Rectangle screenRectangle, Point clickPoint) {
        move(screenRectangle, clickPoint.x, clickPoint.y);
    }

    public static void hover(Point dest) {
        Point src = AWTRobot.getCurrentMousePoint();
        AWTDesktop.getRobot().smoothMove(src, dest, 500);
    }

    public static void move(Point dest) {
        AWTDesktop.getRobot().move(dest);
    }

    public static void drag(Point screenPoint) {
        hover(screenPoint);
        AWTDesktop.getRobot().drag();
    }

    public static void drop(Point screenPoint) {
        hover(screenPoint);
        AWTDesktop.getRobot().drop();
    }

    public static void rightClick(Point screenPoint) {
        hover(screenPoint);
        AWTDesktop.getRobot().rightClick();
    }

    public static void rightClick() {
        AWTDesktop.getRobot().rightClick();
    }

    public static void doubleClick(Point screenPoint) {
        hover(screenPoint);
        AWTDesktop.getRobot().doubleClick();
    }

    public static void doubleClick() {
        AWTDesktop.getRobot().doubleClick();
    }

    public static void click() {
        AWTDesktop.getRobot().click();
    }

    public static void press() {
        AWTDesktop.getRobot().mouseDown(16);
    }

    public static void rightPress() {
        AWTDesktop.getRobot().mouseDown(4);
    }

    public static void release() {
        AWTDesktop.getRobot().mouseUp(16);
    }

    public static void rightRelease() {
        AWTDesktop.getRobot().mouseUp(4);
    }

    public static void wheel(int direction, int steps) {
        AWTDesktop.getRobot().wheel(direction, steps);
    }

    public static void mouseDown(int buttons) {
        AWTDesktop.getRobot().mouseDown(buttons);
    }

    public static void mouseUp() {
        AWTDesktop.getRobot().mouseUp(0);
    }

    public static void mouseUp(int buttons) {
        AWTDesktop.getRobot().mouseUp(buttons);
    }

    public static Point getLocation() {
        return AWTRobot.getCurrentMousePoint();
    }

    public void click(Point screenPoint) {
        if (screenPoint != null) {
            hover(screenPoint);
            AWTDesktop.getRobot().click();
        }
    }
}
