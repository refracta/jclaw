package com.github.refracta.jclaw.core.system;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class JClawDelay {
    private static final List<Thread> waitList = new CopyOnWriteArrayList<>();
    private static boolean pause = false;

    public static List<Thread> getWaitList() {
        return waitList;
    }

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        JClawDelay.pause = pause;
    }

    public static void resume() {
        for (Thread t : waitList) {
            synchronized (t) {
                t.notify();
            }
        }
    }

    public static void delay(double seconds) {
        try {
            if (pause) {
                Thread thread = Thread.currentThread();
                synchronized (thread) {
                    waitList.add(thread);
                    thread.wait();
                }
            }
            TimeUnit.MILLISECONDS.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
