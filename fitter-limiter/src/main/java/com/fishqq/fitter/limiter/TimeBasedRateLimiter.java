package com.fishqq.fitter.limiter;

public class TimeBasedRateLimiter implements Limiter {
    private final int max;
    private final long interval;

    private long start;
    private int count;

    public TimeBasedRateLimiter(int max, long intervalMs) {
        this.max = max;
        this.interval = intervalMs;
    }

    @Override
    public boolean isLimited() {
        long now = System.currentTimeMillis();
        if (start == 0 || now - start > interval) {
            this.start = now;
            this.count = 1;
            return false;
        } else {
            return ++count > max;
        }
    }
}
