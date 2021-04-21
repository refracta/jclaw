package com.github.refracta.jclaw.searcher.rectangle;

import java.awt.*;
import java.util.Objects;

public class ThresholdRectangle extends Rectangle {
    public double threshold;

    public ThresholdRectangle(double threshold) {
        this.threshold = threshold;
    }

    public ThresholdRectangle(Rectangle r, double threshold) {
        super(r);
        this.threshold = threshold;
    }

    public ThresholdRectangle(int x, int y, int width, int height, double threshold) {
        super(x, y, width, height);
        this.threshold = threshold;
    }

    public ThresholdRectangle(int width, int height, double threshold) {
        super(width, height);
        this.threshold = threshold;
    }

    public ThresholdRectangle(java.awt.Point p, Dimension d, double threshold) {
        super(p, d);
        this.threshold = threshold;
    }

    public ThresholdRectangle(java.awt.Point p, double threshold) {
        super(p);
        this.threshold = threshold;
    }

    public ThresholdRectangle(Dimension d, double threshold) {
        super(d);
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "ThresholdRectangle{" +
                "threshold=" + threshold +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ThresholdRectangle that = (ThresholdRectangle) o;
        return java.lang.Double.compare(that.threshold, threshold) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), threshold);
    }
}