package com.github.refracta.jclaw.awt.env;

import java.awt.*;

public class Env {
    public Env() {
    }

    public static String getOSVersion() {
        return System.getProperty("os.version");
    }

    public static OS getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("mac os x")) {
            return OS.MAC;
        } else if (os.startsWith("windows")) {
            return OS.WINDOWS;
        } else {
            return os.startsWith("linux") ? OS.LINUX : OS.NOT_SUPPORTED;
        }
    }

    public static boolean isWindows() {
        return getOS() == OS.WINDOWS;
    }

    public static boolean isLinux() {
        return getOS() == OS.LINUX;
    }

    public static boolean isMac() {
        return getOS() == OS.MAC;
    }

    public static String getSeparator() {
        return isWindows() ? ";" : ":";
    }

    public static boolean isLockOn(char key) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        switch (key) {
            case '\ue025':
                return tk.getLockingKeyState(145);
            case '\ue027':
                return tk.getLockingKeyState(20);
            case '\ue03b':
                return tk.getLockingKeyState(144);
            default:
                return false;
        }
    }

    public static int getHotkeyModifier() {
        return getOS() == OS.MAC ? 157 : 17;
    }
}
