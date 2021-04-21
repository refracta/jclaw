package com.github.refracta.jclaw.ui;

import com.sun.jna.Native;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;
import java.awt.*;

public class OverlayFrame extends JFrame {
    static {
        if (!Boolean.getBoolean("sun.java2d.noddraw")) {
            System.setProperty("sun.java2d.noddraw", "true");
        }
    }

    private final Color transparentColor = new Color(0, 0, 0, 0);

    public OverlayFrame() throws HeadlessException {
        setUndecorated(true);
        setAlwaysOnTop(true);
        this.pack();
        WindowUtils.setWindowTransparent(this, true);
        setTransparent();
    }

    @Override
    public void setBackground(Color bgColor) {
        if (!bgColor.equals(transparentColor)) {
            super.setBackground(bgColor);
        }
    }

    private void setTransparent() {
        WinDef.HWND hwnd = new WinDef.HWND();
        hwnd.setPointer(Native.getComponentPointer(this));
        int wl = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
        wl = wl | WinUser.WS_EX_LAYERED | WinUser.WS_EX_TRANSPARENT;
        User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, wl);
    }
}
