package com.github.refracta.jclaw.searcher.factory;

import com.github.refracta.jclaw.searcher.ScreenSearcher;
import com.github.refracta.jclaw.searcher.impl.DefaultScreenSearcher;
import org.opencv.imgproc.Imgproc;

public class ScreenSearcherFactory {
    public static ScreenSearcher getRGBAScreenSearcher() {
        return new DefaultScreenSearcher();
    }

    public static ScreenSearcher getRGBScreenSearcher() {
        DefaultScreenSearcher screenSearcher = new DefaultScreenSearcher();
        screenSearcher.setThreshold(0.95);
        screenSearcher.setSearchMethod(Imgproc.TM_CCOEFF_NORMED);
        screenSearcher.setUseMask(false);
        screenSearcher.setNoiseMergeLevel(0);
        return screenSearcher;
    }
}
