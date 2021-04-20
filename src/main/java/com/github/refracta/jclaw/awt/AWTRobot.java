package com.github.refracta.jclaw.awt;

import com.github.refracta.jclaw.awt.animator.Animator;
import com.github.refracta.jclaw.awt.animator.OutQuarticEase;
import com.github.refracta.jclaw.awt.animator.TimeBasedAnimator;
import com.github.refracta.jclaw.awt.env.Env;
import com.github.refracta.jclaw.awt.env.Key;
import com.github.refracta.jclaw.awt.env.OS;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AWTRobot extends Robot {
    static final int MAX_DELAY = 60000;
    public static double DelayBeforeDrop = 0.3D;
    public static double DelayAfterDrag = 0.3D;
    private static int _hold_buttons = 0;

    public AWTRobot(GraphicsDevice gdev) throws AWTException {
        super(gdev);
    }

    public AWTRobot() throws AWTException {
    }

    public static Point getCurrentMousePoint() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    public void delay(int ms) {
        if (ms < 0) {
            ms = 0;
        }

        while (ms > 60000) {
            super.delay(60000);
            ms -= 60000;
        }

        super.delay(ms);
    }

    public BufferedImage captureScreen(Rectangle rect) {
        return this.createScreenCapture(rect);
    }

    public void pressModifiers(int modifiers) {
        if ((modifiers & 1) != 0) {
            this.keyPress(16);
        }

        if ((modifiers & 2) != 0) {
            this.keyPress(17);
        }

        if ((modifiers & 8) != 0) {
            this.keyPress(18);
        }

        if ((modifiers & 4) != 0) {
            if (Env.getOS() == OS.WINDOWS) {
                this.keyPress(524);
            } else {
                this.keyPress(157);
            }
        }

    }

    public void releaseModifiers(int modifiers) {
        if ((modifiers & 1) != 0) {
            this.keyRelease(16);
        }

        if ((modifiers & 2) != 0) {
            this.keyRelease(17);
        }

        if ((modifiers & 8) != 0) {
            this.keyRelease(18);
        }

        if ((modifiers & 4) != 0) {
            if (Env.getOS() == OS.WINDOWS) {
                this.keyRelease(524);
            } else {
                this.keyRelease(157);
            }
        }

    }

    protected void doType(AWTRobot.KeyMode mode, int... keyCodes) {
        int i;
        if (mode == AWTRobot.KeyMode.PRESS_ONLY) {
            for (i = 0; i < keyCodes.length; ++i) {
                this.keyPress(keyCodes[i]);
            }
        } else if (mode == AWTRobot.KeyMode.RELEASE_ONLY) {
            for (i = 0; i < keyCodes.length; ++i) {
                this.keyRelease(keyCodes[i]);
            }
        } else {
            for (i = 0; i < keyCodes.length; ++i) {
                this.keyPress(keyCodes[i]);
            }

            for (i = 0; i < keyCodes.length; ++i) {
                this.keyRelease(keyCodes[i]);
            }
        }

    }

    public void typeChar(char character, AWTRobot.KeyMode mode) {
        this.doType(mode, Key.toJavaKeyCode(character));
    }

    public Object getDevice() {
        return null;
    }

    private void _move(int x, int y) {
        mouseMove(x, y);
        waitForIdle();
    }

    public void smoothMove(Point src, Point dest, int ms) {
        if (ms == 0) {
            this._move(dest.x, dest.y);
        } else {
            Animator aniX = new TimeBasedAnimator(new OutQuarticEase((float) src.getX(), (float) dest.getX(), ms));
            TimeBasedAnimator aniY = new TimeBasedAnimator(new OutQuarticEase((float) src.getY(), (float) dest.getY(), ms));

            while (aniX.running()) {
                float x = aniX.step();
                float y = aniY.step();
                this._move((int) x, (int) y);
                delay(50);
            }

        }
    }

    private void _click(int buttons, int modifiers, boolean dblClick) {
        pressModifiers(modifiers);
        mousePress(buttons);
        mouseRelease(buttons);
        if (dblClick) {
            mousePress(buttons);
            mouseRelease(buttons);
        }

        releaseModifiers(modifiers);
        waitForIdle();
    }

    public void move(Point dest) {
        this._move(dest.x, dest.y);
    }

    public void drag() {
        mousePress(16);
        waitForIdle();
    }

    public void drop() {
        int delay = 1;
        delay(delay * 1000);
        mouseRelease(16);
        waitForIdle();
    }

    public void rightClick() {
        this._click(4, 0, false);
    }

    public void doubleClick() {
        this._click(16, 0, true);
    }

    public void click() {
        this._click(16, 0, false);
    }

    public void wheel(int direction, int steps) {
        for (int i = 0; i < steps; ++i) {
            mouseWheel(direction);
            delay(50);
        }

    }

    public void mouseDown(int buttons) {
        _hold_buttons = buttons;
        mousePress(buttons);
        waitForIdle();
    }

    public void mouseUp() {
        this.mouseUp(0);
    }

    public void mouseUp(int buttons) {
        if (buttons == 0) {
            mouseRelease(_hold_buttons);
        } else {
            mouseRelease(buttons);
        }

        waitForIdle();
    }

    public enum KeyMode {
        PRESS_ONLY,
        RELEASE_ONLY,
        PRESS_RELEASE;

        KeyMode() {
        }
    }
}
