package com.github.refracta.jclaw.searcher;

import org.opencv.core.Mat;

import java.awt.*;
import java.util.List;

public interface ScreenSearcher {
    List<Rectangle> search(Mat source, Mat template, int limit);
}
