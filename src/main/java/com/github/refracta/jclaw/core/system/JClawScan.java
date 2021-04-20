package com.github.refracta.jclaw.core.system;

import com.github.refracta.jclaw.awt.AWTDesktop;
import com.github.refracta.jclaw.searcher.ScreenSearcher;
import com.github.refracta.jclaw.util.OpenCVUtil;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JClawScan {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static Mat capture(Rectangle rectangle) {
        return OpenCVUtil.bufferedImage2Mat(AWTDesktop.getRobot().createScreenCapture(rectangle));
    }

    public static List<Rectangle> scanMultiple(Mat image, Mat[] targets, ScreenSearcher searcher) {
        List<Rectangle> resultList = new ArrayList<>();
        for (Mat m : targets) {
            resultList.addAll(searcher.search(image, m, -1));
        }
        return resultList;
    }

    public static List<Rectangle> scanParallelMultiple(Mat image, Mat[] targets, ScreenSearcher searcher) {
        List<Rectangle> resultList = new ArrayList<>();
        List<Callable<List<Rectangle>>> tasks = new ArrayList<>();
        for (Mat t : targets) {
            tasks.add(new Callable<List<Rectangle>>() {
                @Override
                public List<Rectangle> call() throws Exception {
                    return searcher.search(image, t, -1);
                }
            });
        }
        try {
            List<Future<List<Rectangle>>> list = executorService.invokeAll((Collection) tasks);
            for (Future<List<Rectangle>> l : list) {
                resultList.addAll(l.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static List<Rectangle> scanMultipleFast(Mat image, Mat[] targets, ScreenSearcher searcher) {
        for (Mat t : targets) {
            List<Rectangle> search = searcher.search(image, t, 1);
            if (!search.isEmpty()) {
                return search;
            }
        }
        return new ArrayList<>();
    }

    public static List<Rectangle> scanParallelMultipleFast(Mat image, Mat[] targets, ScreenSearcher searcher) {
        List<Rectangle> resultList = new CopyOnWriteArrayList<>();
        Thread currentThread = Thread.currentThread();
        int count = targets.length;
        AtomicInteger counter = new AtomicInteger(0);
        for (Mat t : targets) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    List<Rectangle> searchResult = searcher.search(image, t, 1);
                    resultList.addAll(searchResult);
                    int c = counter.addAndGet(1);
                    if (!resultList.isEmpty() || c == count) {
                        synchronized (currentThread) {
                            currentThread.notify();
                        }
                    }
                }
            });
        }
        synchronized (currentThread) {
            try {
                currentThread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Rectangle> normalList = new ArrayList<>(resultList);
        return normalList;
    }
}
