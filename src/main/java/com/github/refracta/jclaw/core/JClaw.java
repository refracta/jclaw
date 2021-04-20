package com.github.refracta.jclaw.core;

import nu.pattern.OpenCV;

public class JClaw {
    private static boolean init = false;

    public static void init() {
        if (!init) {
            OpenCV.loadLocally();
            init = true;
        }
    }
}
