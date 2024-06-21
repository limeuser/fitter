package com.fishqq.fitter.limiter;

import java.util.function.Supplier;

public interface Limiter {
    Limiter unlimited = () -> false;

    static Limiter limitByCondition(Supplier<Boolean> condition) {
        return condition::get;
    }

    boolean isLimited();

    default boolean limit(Runnable runnable) {
        if (isLimited()) {
            return false;
        } else {
            runnable.run();
            return true;
        }
    }
}
