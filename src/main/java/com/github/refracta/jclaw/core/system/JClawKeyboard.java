package com.github.refracta.jclaw.core.system;

import com.github.refracta.jclaw.awt.AWTDesktop;
import com.github.refracta.jclaw.awt.AWTRobot;
import com.github.refracta.jclaw.awt.Clipboard;
import com.github.refracta.jclaw.awt.env.Env;

import java.awt.event.KeyEvent;

public class JClawKeyboard {
    private static int modifiers;
    private static String _hold_keys = "";

    private static AWTRobot getRobot() {
        return AWTDesktop.getRobot();
    }

    public static String copy() {
        Clipboard.clear();

        int mod = Env.getHotkeyModifier();
        AWTRobot robot = getRobot();
        robot.keyPress(mod);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(mod);

        String text = Clipboard.getText();
        return text;
    }

    public static void paste(String text) {
        if (text == null) {
            return;
        }
        Clipboard.putText(Clipboard.PLAIN, Clipboard.UTF8, Clipboard.BYTE_BUFFER, text);
        int mod = Env.getHotkeyModifier();
        AWTRobot robot = getRobot();
        robot.keyPress(mod);
        robot.keyPress(86);
        robot.keyRelease(86);
        robot.keyRelease(mod);
    }

    public static void type(String text) {
        if (text == null) {
            return;
        }
        AWTRobot robot = getRobot();

        for (int i = 0; i < text.length(); ++i) {
            robot.pressModifiers(modifiers);
            robot.typeChar(text.charAt(i), AWTRobot.KeyMode.PRESS_RELEASE);
            robot.releaseModifiers(modifiers);
            robot.delay(20);
        }

        robot.waitForIdle();
    }

    public static void keyDown(int keycode) {
        getRobot().keyPress(keycode);
    }

    public static void keyUp(int keycode) {
        getRobot().keyRelease(keycode);
    }

    public static void keyDown(String keys) {
        if (keys != null) {
            for (int i = 0; i < keys.length(); ++i) {
                if (_hold_keys.indexOf(keys.charAt(i)) == -1) {
                    getRobot().typeChar(keys.charAt(i), AWTRobot.KeyMode.PRESS_ONLY);
                    _hold_keys = _hold_keys + keys.charAt(i);
                }
            }

            getRobot().waitForIdle();
        }
    }

    public static void keyUp() {
        keyUp(null);
    }

    public static void keyUp(String keys) {
        if (keys == null) {
            keys = _hold_keys;
        }

        for (int i = 0; i < keys.length(); ++i) {
            int pos;
            if ((pos = _hold_keys.indexOf(keys.charAt(i))) != -1) {
                getRobot().typeChar(keys.charAt(i), AWTRobot.KeyMode.RELEASE_ONLY);
                _hold_keys = _hold_keys.substring(0, pos) + _hold_keys.substring(pos + 1);
            }
        }

        getRobot().waitForIdle();
    }

    public static void keyDownAndUp(int keycode) {
        keyDown(keycode);
        keyUp(keycode);
    }

    public static void keyDownAndUp(String keys) {
        keyDown(keys);
        keyUp(keys);
    }
}
