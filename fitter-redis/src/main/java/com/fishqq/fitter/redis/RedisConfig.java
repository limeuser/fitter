package com.fishqq.fitter.redis;

public class RedisConfig {
    private String host;
    private String user;
    private String password;

    private Integer connectionTimeoutMillis;
    private Integer socketTimeoutMillis;
    private Integer blockingSocketTimeoutMillis;

    private Integer database;
    private String clientName;

    private Boolean ssl;

    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;

    public static RedisConfig createDefault() {
        return new RedisConfig()
                .setHost("loalhost:6379")
                .setConnectionTimeoutMillis(10 * 1000)
                .setSocketTimeoutMillis(10 * 1000)
                .setBlockingSocketTimeoutMillis(10 * 1000)
                .setDatabase(0)
                .setClientName("default-jedis")
                .setSsl(false)
                .setMaxTotal(16)
                .setMaxIdle(2)
                .setMinIdle(1);
    }

    public String getHost() {
        return host;
    }

    public RedisConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public String getUser() {
        return user;
    }

    public RedisConfig setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RedisConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getConnectionTimeoutMillis() {
        return connectionTimeoutMillis;
    }

    public RedisConfig setConnectionTimeoutMillis(Integer connectionTimeoutMillis) {
        this.connectionTimeoutMillis = connectionTimeoutMillis;
        return this;
    }

    public Integer getSocketTimeoutMillis() {
        return socketTimeoutMillis;
    }

    public RedisConfig setSocketTimeoutMillis(Integer socketTimeoutMillis) {
        this.socketTimeoutMillis = socketTimeoutMillis;
        return this;
    }

    public Integer getBlockingSocketTimeoutMillis() {
        return blockingSocketTimeoutMillis;
    }

    public RedisConfig setBlockingSocketTimeoutMillis(Integer blockingSocketTimeoutMillis) {
        this.blockingSocketTimeoutMillis = blockingSocketTimeoutMillis;
        return this;
    }

    public Integer getDatabase() {
        return database;
    }

    public RedisConfig setDatabase(Integer database) {
        this.database = database;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public RedisConfig setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public Boolean getSsl() {
        return ssl;
    }

    public RedisConfig setSsl(Boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public RedisConfig setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
        return this;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public RedisConfig setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
        return this;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public RedisConfig setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public void merge(RedisConfig other) {
        if (other == null) {
            return;
        }

        if (other.getHost() != null) {
            this.host = other.getHost();
        }
        if (other.getUser() != null) {
            this.user = other.getUser();
        }
        if (other.getPassword() != null) {
            this.password = other.getPassword();
        }

        if (other.getConnectionTimeoutMillis() != null) {
            this.connectionTimeoutMillis = other.getConnectionTimeoutMillis();
        }
        if (other.getSocketTimeoutMillis() != null) {
            this.socketTimeoutMillis = other.getSocketTimeoutMillis();
        }
        if (other.getBlockingSocketTimeoutMillis() != null) {
            this.blockingSocketTimeoutMillis = other.getBlockingSocketTimeoutMillis();
        }

        if (other.getDatabase() != null) {
            this.database = other.getDatabase();
        }
        if (other.getClientName() != null) {
            this.clientName = other.getClientName();
        }

        if (other.getSsl() != null) {
            this.ssl = other.getSsl();
        }

        if (other.getMaxTotal() != null) {
            this.maxTotal = other.getMaxTotal();
        }
        if (other.getMaxIdle() != null) {
            this.maxIdle = other.getMaxIdle();
        }
        if (other.getMinIdle() != null) {
            this.minIdle = other.getMinIdle();
        }
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", connectionTimeoutMillis=" + connectionTimeoutMillis +
                ", socketTimeoutMillis=" + socketTimeoutMillis +
                ", blockingSocketTimeoutMillis=" + blockingSocketTimeoutMillis +
                ", database=" + database +
                ", clientName='" + clientName + '\'' +
                ", ssl=" + ssl +
                ", maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                '}';
    }
}
