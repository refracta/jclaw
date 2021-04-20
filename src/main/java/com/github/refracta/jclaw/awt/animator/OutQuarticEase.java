package com.github.refracta.jclaw.awt.animator;

public class OutQuarticEase extends TimeValueFunction {
    public OutQuarticEase(float beginVal, float endVal, long totalTime) {
        super(beginVal, endVal, totalTime);
    }

    public float getValue(long t) {
        if (t > this._totalTime) {
            return this._endVal;
        } else {
            double t1 = (double) t / (double) this._totalTime;
            double t2 = t1 * t1;
            return (float) ((double) this._beginVal + (double) (this._endVal - this._beginVal) * (-1.0D * t2 * t2 + 4.0D * t1 * t2 - 6.0D * t2 + 4.0D * t1));
        }
    }
}