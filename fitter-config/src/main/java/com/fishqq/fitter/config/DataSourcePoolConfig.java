package com.fishqq.fitter.config;

public class DataSourcePoolConfig {
    private Integer minIdle;
    private Integer maxTotal;

    public static DataSourcePoolConfig createDefault() {
        return new DataSourcePoolConfig()
                .setMinIdle(1)
                .setMaxTotal(8);
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public DataSourcePoolConfig setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public DataSourcePoolConfig setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
        return this;
    }

    public void merge(DataSourcePoolConfig other) {
        if (other == null) {
            return;
        }

        if (other.getMinIdle() != null) {
            this.minIdle = other.getMinIdle();
        }
        if (other.getMaxTotal() != null) {
            this.maxTotal = other.getMaxTotal();
        }
    }

    @Override
    public String toString() {
        return "DataSourcePoolConfig{" +
                "minIdle=" + minIdle +
                ", maxTotal=" + maxTotal +
                '}';
    }
}
