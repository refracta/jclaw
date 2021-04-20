package com.github.refracta.jclaw.awt.animator;

public abstract class TimeValueFunction {
    protected float _beginVal;
    protected float _endVal;
    protected long _totalTime;

    public TimeValueFunction(float beginVal, float endVal, long totalTime) {
        this._beginVal = beginVal;
        this._endVal = endVal;
        this._totalTime = totalTime;
    }

    public boolean isEnd(long t) {
        return t >= this._totalTime;
    }

    public abstract float getValue(long var1);
}
