package com.github.refracta.jclaw.searcher.impl;

import com.github.refracta.jclaw.searcher.ScreenSearcher;
import com.github.refracta.jclaw.searcher.rectangle.ThresholdRectangle;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.util.List;
import java.util.*;

public class DefaultScreenSearcher implements ScreenSearcher {
    private Scalar fillColor = new Scalar(0, 0, 0);
    private int searchMethod = Imgproc.TM_CCORR_NORMED;
    // TM_CCORR_NORMED, TM_CCOEFF_NORMED
    private double threshold = 0.97;
    // 0.95~0.98
    private int noiseMergeLevel = 4;
    // 0, 2~10
    private boolean useMask = true;
    // true = Alpha Search, false = Normal Search
    private boolean useThreshold = false;
    private int threshMethod = Imgproc.THRESH_TOZERO;
    private double thresh = 0.1;
    private double threshMaxVal = 1;

    private boolean useThresholdSort = false;
    private int defaultLimit = 10000;

    public Scalar getFillColor() {
        return fillColor;
    }

    public void setFillColor(Scalar fillColor) {
        this.fillColor = fillColor;
    }

    public int getSearchMethod() {
        return searchMethod;
    }

    public void setSearchMethod(int searchMethod) {
        this.searchMethod = searchMethod;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public boolean isUseThreshold() {
        return useThreshold;
    }

    public void setUseThreshold(boolean useThreshold) {
        this.useThreshold = useThreshold;
    }

    public int getThreshMethod() {
        return threshMethod;
    }

    public void setThreshMethod(int threshMethod) {
        this.threshMethod = threshMethod;
    }

    public double getThresh() {
        return thresh;
    }

    public void setThresh(double thresh) {
        this.thresh = thresh;
    }

    public double getThreshMaxVal() {
        return threshMaxVal;
    }

    public void setThreshMaxVal(double threshMaxVal) {
        this.threshMaxVal = threshMaxVal;
    }

    public boolean isUseThresholdSort() {
        return useThresholdSort;
    }

    public void setUseThresholdSort(boolean useThresholdSort) {
        this.useThresholdSort = useThresholdSort;
    }

    public int getNoiseMergeLevel() {
        return noiseMergeLevel;
    }

    public void setNoiseMergeLevel(int noiseMergeLevel) {
        this.noiseMergeLevel = noiseMergeLevel;
    }

    public boolean isUseMask() {
        return useMask;
    }

    public void setUseMask(boolean useMask) {
        this.useMask = useMask;
    }

    public int getDefaultLimit() {
        return defaultLimit;
    }

    public void setDefaultLimit(int defaultLimit) {
        this.defaultLimit = defaultLimit;
    }

    @Override
    public String toString() {
        return "DefaultScreenSearcher{" +
                "fillColor=" + fillColor +
                ", searchMethod=" + searchMethod +
                ", threshold=" + threshold +
                ", noiseMergeLevel=" + noiseMergeLevel +
                ", useMask=" + useMask +
                ", useThreshold=" + useThreshold +
                ", threshMethod=" + threshMethod +
                ", thresh=" + thresh +
                ", threshMaxVal=" + threshMaxVal +
                ", useThresholdSort=" + useThresholdSort +
                ", defaultLimit=" + defaultLimit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultScreenSearcher that = (DefaultScreenSearcher) o;
        return searchMethod == that.searchMethod && Double.compare(that.threshold, threshold) == 0 && noiseMergeLevel == that.noiseMergeLevel && useMask == that.useMask && useThreshold == that.useThreshold && threshMethod == that.threshMethod && Double.compare(that.thresh, thresh) == 0 && Double.compare(that.threshMaxVal, threshMaxVal) == 0 && useThresholdSort == that.useThresholdSort && defaultLimit == that.defaultLimit && Objects.equals(fillColor, that.fillColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fillColor, searchMethod, threshold, noiseMergeLevel, useMask, useThreshold, threshMethod, thresh, threshMaxVal, useThresholdSort, defaultLimit);
    }

    public boolean checkAttached(Rectangle r1, Rectangle r2, int level) {
        int x = Math.abs(r1.x - r2.x);
        int y = Math.abs(r1.y - r2.y);
        return x <= level && y <= level && x + y <= 2 * level;
    }

    public boolean checkAttached(List<? extends Rectangle> attachedList, Rectangle target, int level) {
        for (Rectangle r : attachedList) {
            if (checkAttached(r, target, level)) {
                return true;
            }
        }
        return false;
    }

    public List<ThresholdRectangle> mergeNoiseRectangles(List<ThresholdRectangle> rectangleList) {
        List<ThresholdRectangle> resultList = new ArrayList<>();
        Set<ThresholdRectangle> excludedSet = new HashSet<>();
        for (ThresholdRectangle r : rectangleList) {
            if (excludedSet.contains(r)) {
                continue;
            }
            List<ThresholdRectangle> attachedList = new ArrayList<>();
            attachedList.add(r);
            double x = r.x;
            double y = r.y;
            double threshold = r.threshold;
            for (ThresholdRectangle t : rectangleList) {
                if (!t.equals(r) && (r.width == t.width && r.height == t.height)) {
                    if (checkAttached(attachedList, t, noiseMergeLevel)) {
                        attachedList.add(t);
                        x += t.x;
                        y += t.y;
                        threshold += t.threshold;
                    }
                }
            }
            int attachedSize = attachedList.size();
            x /= attachedSize;
            y /= attachedSize;
            threshold /= attachedSize;
            resultList.add(new ThresholdRectangle((int) x, (int) y, r.width, r.height, threshold));
            excludedSet.addAll(attachedList);
        }
        return resultList;
    }

    @Override
    public List<Rectangle> search(Mat image, Mat template, int limit) {
        List<ThresholdRectangle> resultList = new ArrayList<>();
        Mat output = new Mat();
        if (useMask) {
            Imgproc.matchTemplate(image, template, output, searchMethod, template);
        } else {
            Imgproc.matchTemplate(image, template, output, searchMethod);
        }
        if (useThreshold) {
            Imgproc.threshold(output, output, thresh, threshMaxVal, threshMethod);
        }
        while (true) {
            Core.MinMaxLocResult mmlr = Core.minMaxLoc(output);
            Point location = mmlr.maxLoc;
            double maxVal = mmlr.maxVal;
            if (maxVal >= threshold) {
                ThresholdRectangle findRectangle = new ThresholdRectangle((int) location.x, (int) location.y, template.cols(), template.rows(), maxVal);
                resultList.add(findRectangle);
                if ((!(limit < 0) && resultList.size() >= limit) || !(defaultLimit < 0) && resultList.size() >= defaultLimit) {
                    break;
                }
                Imgproc.rectangle(output, location, new Point(location.x + template.cols(), location.y + template.rows()), fillColor, -1);
            } else {
                break;
            }
        }
        resultList = mergeNoiseRectangles(resultList);
        if (useThresholdSort) {
            resultList.sort(new Comparator<ThresholdRectangle>() {
                @Override
                public int compare(ThresholdRectangle o1, ThresholdRectangle o2) {
                    return Double.compare(o1.threshold, o2.threshold);
                }
            });
        } else {
            resultList.sort(new Comparator<ThresholdRectangle>() {
                @Override
                public int compare(ThresholdRectangle o1, ThresholdRectangle o2) {
                    int yCompare = Integer.compare(o1.y, o2.y);
                    return yCompare != 0 ? yCompare : Integer.compare(o1.x, o2.x);
                }
            });
        }
        return (List<Rectangle>) (List<?>) resultList;
    }
}
