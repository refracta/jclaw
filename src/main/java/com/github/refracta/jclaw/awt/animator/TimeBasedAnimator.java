package com.github.refracta.jclaw.awt.animator;

import java.util.Date;

public class TimeBasedAnimator implements Animator {
    protected long _begin_time = -1L;
    protected float _beginVal;
    protected float _endVal;
    protected float _stepUnit;
    protected long _totalMS;
    protected boolean _running = true;
    protected TimeValueFunction _func;

    public TimeBasedAnimator(TimeValueFunction func) {
        this._func = func;
    }

    public float step() {
        if (this._begin_time == -1L) {
            this._begin_time = (new Date()).getTime();
            return this._func.getValue(0L);
        } else {
            long now = (new Date()).getTime();
            long delta = now - this._begin_time;
            float ret = this._func.getValue(delta);
            this._running = !this._func.isEnd(delta);
            return ret;
        }
    }

    public boolean running() {
        return this._running;
    }
}