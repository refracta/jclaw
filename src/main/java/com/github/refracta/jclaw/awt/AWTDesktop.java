package com.github.refracta.jclaw.awt;

import java.awt.*;

public class AWTDesktop {
    private static AWTRobot robot;

    static {
        try {
            robot = new AWTRobot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static AWTRobot getRobot() {
        return robot;
    }
}
