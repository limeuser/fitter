package com.fishqq.fitter.config;

public class DataSourceConfig {
    private String url;
    private String username;
    private String password;
    private String driver;

    public static DataSourceConfig createDefault() {
        return new DataSourceConfig();
    }

    public String getUrl() {
        return url;
    }

    public DataSourceConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataSourceConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DataSourceConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriver() {
        return driver;
    }

    public DataSourceConfig setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public void merge(DataSourceConfig other) {
        if (other == null) {
            return;
        }

        if (other.getUrl() != null) {
            this.url = other.getUrl();
        }
        if (other.getUsername() != null) {
            this.username = other.getUsername();
        }
        if (other.getPassword() != null) {
            this.password = other.getPassword();
        }
        if (other.getDriver() != null) {
            this.driver = other.getDriver();
        }
    }

    @Override
    public String toString() {
        return "DataSourceConfig{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }
}
