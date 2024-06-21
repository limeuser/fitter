package com.fishqq.fitter.mybatis;

import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Properties;

public class MybatisConfig {
    private Boolean cacheEnabled;
    private Boolean lazyLoadingEnabled;
    private Boolean aggressiveLazyLoading;
    private Boolean multipleResultSetsEnabled;
    private Boolean useColumnLabel;
    private Boolean useGeneratedKeys;

    private Boolean safeRowBoundsEnabled;
    private Boolean safeResultHandlerEnabled;
    private Boolean mapUnderscoreToCamelCase;
    private Boolean callSettersOnNulls;
    private Boolean useActualParamName;
    private Boolean returnInstanceForEmptyRow;
    private Boolean shrinkWhitespacesInSql;
    private Boolean nullableOnForEach;
    private Boolean argNameBasedConstructorAutoMapping;

    private AutoMappingBehavior autoMappingBehavior;
    private AutoMappingUnknownColumnBehavior autoMappingUnknownColumnBehavior;

    private String logPrefix;
    private LocalCacheScope localCacheScope;
    private JdbcType jdbcTypeForNull;
    private List<String> lazyLoadTriggerMethods;
    private Integer defaultStatementTimeout;
    private Integer defaultFetchSize;
    private ResultSetType defaultResultSetType;
    private ExecutorType defaultExecutorType;
    private Properties variables;

    public static MybatisConfig createDefault() {
        return new MybatisConfig();
    }

    public Boolean getCacheEnabled() {
        return cacheEnabled;
    }

    public MybatisConfig setCacheEnabled(Boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
        return this;
    }

    public Boolean getLazyLoadingEnabled() {
        return lazyLoadingEnabled;
    }

    public MybatisConfig setLazyLoadingEnabled(Boolean lazyLoadingEnabled) {
        this.lazyLoadingEnabled = lazyLoadingEnabled;
        return this;
    }

    public Boolean getAggressiveLazyLoading() {
        return aggressiveLazyLoading;
    }

    public MybatisConfig setAggressiveLazyLoading(Boolean aggressiveLazyLoading) {
        this.aggressiveLazyLoading = aggressiveLazyLoading;
        return this;
    }

    public Boolean getMultipleResultSetsEnabled() {
        return multipleResultSetsEnabled;
    }

    public MybatisConfig setMultipleResultSetsEnabled(Boolean multipleResultSetsEnabled) {
        this.multipleResultSetsEnabled = multipleResultSetsEnabled;
        return this;
    }

    public Boolean getUseColumnLabel() {
        return useColumnLabel;
    }

    public MybatisConfig setUseColumnLabel(Boolean useColumnLabel) {
        this.useColumnLabel = useColumnLabel;
        return this;
    }

    public Boolean getUseGeneratedKeys() {
        return useGeneratedKeys;
    }

    public MybatisConfig setUseGeneratedKeys(Boolean useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
        return this;
    }

    public Boolean getSafeRowBoundsEnabled() {
        return safeRowBoundsEnabled;
    }

    public MybatisConfig setSafeRowBoundsEnabled(Boolean safeRowBoundsEnabled) {
        this.safeRowBoundsEnabled = safeRowBoundsEnabled;
        return this;
    }

    public Boolean getSafeResultHandlerEnabled() {
        return safeResultHandlerEnabled;
    }

    public MybatisConfig setSafeResultHandlerEnabled(Boolean safeResultHandlerEnabled) {
        this.safeResultHandlerEnabled = safeResultHandlerEnabled;
        return this;
    }

    public Boolean getMapUnderscoreToCamelCase() {
        return mapUnderscoreToCamelCase;
    }

    public MybatisConfig setMapUnderscoreToCamelCase(Boolean mapUnderscoreToCamelCase) {
        this.mapUnderscoreToCamelCase = mapUnderscoreToCamelCase;
        return this;
    }

    public Boolean getCallSettersOnNulls() {
        return callSettersOnNulls;
    }

    public MybatisConfig setCallSettersOnNulls(Boolean callSettersOnNulls) {
        this.callSettersOnNulls = callSettersOnNulls;
        return this;
    }

    public Boolean getUseActualParamName() {
        return useActualParamName;
    }

    public MybatisConfig setUseActualParamName(Boolean useActualParamName) {
        this.useActualParamName = useActualParamName;
        return this;
    }

    public Boolean getReturnInstanceForEmptyRow() {
        return returnInstanceForEmptyRow;
    }

    public MybatisConfig setReturnInstanceForEmptyRow(Boolean returnInstanceForEmptyRow) {
        this.returnInstanceForEmptyRow = returnInstanceForEmptyRow;
        return this;
    }

    public Boolean getShrinkWhitespacesInSql() {
        return shrinkWhitespacesInSql;
    }

    public MybatisConfig setShrinkWhitespacesInSql(Boolean shrinkWhitespacesInSql) {
        this.shrinkWhitespacesInSql = shrinkWhitespacesInSql;
        return this;
    }

    public Boolean getNullableOnForEach() {
        return nullableOnForEach;
    }

    public MybatisConfig setNullableOnForEach(Boolean nullableOnForEach) {
        this.nullableOnForEach = nullableOnForEach;
        return this;
    }

    public Boolean getArgNameBasedConstructorAutoMapping() {
        return argNameBasedConstructorAutoMapping;
    }

    public MybatisConfig setArgNameBasedConstructorAutoMapping(Boolean argNameBasedConstructorAutoMapping) {
        this.argNameBasedConstructorAutoMapping = argNameBasedConstructorAutoMapping;
        return this;
    }

    public AutoMappingBehavior getAutoMappingBehavior() {
        return autoMappingBehavior;
    }

    public MybatisConfig setAutoMappingBehavior(AutoMappingBehavior autoMappingBehavior) {
        this.autoMappingBehavior = autoMappingBehavior;
        return this;
    }

    public AutoMappingUnknownColumnBehavior getAutoMappingUnknownColumnBehavior() {
        return autoMappingUnknownColumnBehavior;
    }

    public MybatisConfig setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior autoMappingUnknownColumnBehavior) {
        this.autoMappingUnknownColumnBehavior = autoMappingUnknownColumnBehavior;
        return this;
    }

    public String getLogPrefix() {
        return logPrefix;
    }

    public MybatisConfig setLogPrefix(String logPrefix) {
        this.logPrefix = logPrefix;
        return this;
    }

    public LocalCacheScope getLocalCacheScope() {
        return localCacheScope;
    }

    public MybatisConfig setLocalCacheScope(LocalCacheScope localCacheScope) {
        this.localCacheScope = localCacheScope;
        return this;
    }

    public JdbcType getJdbcTypeForNull() {
        return jdbcTypeForNull;
    }

    public MybatisConfig setJdbcTypeForNull(JdbcType jdbcTypeForNull) {
        this.jdbcTypeForNull = jdbcTypeForNull;
        return this;
    }

    public List<String> getLazyLoadTriggerMethods() {
        return lazyLoadTriggerMethods;
    }

    public MybatisConfig setLazyLoadTriggerMethods(List<String> lazyLoadTriggerMethods) {
        this.lazyLoadTriggerMethods = lazyLoadTriggerMethods;
        return this;
    }

    public Integer getDefaultStatementTimeout() {
        return defaultStatementTimeout;
    }

    public MybatisConfig setDefaultStatementTimeout(Integer defaultStatementTimeout) {
        this.defaultStatementTimeout = defaultStatementTimeout;
        return this;
    }

    public Integer getDefaultFetchSize() {
        return defaultFetchSize;
    }

    public MybatisConfig setDefaultFetchSize(Integer defaultFetchSize) {
        this.defaultFetchSize = defaultFetchSize;
        return this;
    }

    public ResultSetType getDefaultResultSetType() {
        return defaultResultSetType;
    }

    public MybatisConfig setDefaultResultSetType(ResultSetType defaultResultSetType) {
        this.defaultResultSetType = defaultResultSetType;
        return this;
    }

    public ExecutorType getDefaultExecutorType() {
        return defaultExecutorType;
    }

    public MybatisConfig setDefaultExecutorType(ExecutorType defaultExecutorType) {
        this.defaultExecutorType = defaultExecutorType;
        return this;
    }

    public Properties getVariables() {
        return variables;
    }

    public MybatisConfig setVariables(Properties variables) {
        this.variables = variables;
        return this;
    }


    public void merge(MybatisConfig other) {
        if (other == null) {
            return;
        }


        if (other.getCacheEnabled() != null) {
            this.cacheEnabled = other.getCacheEnabled();
        }
        if (other.getLazyLoadingEnabled() != null) {
            this.lazyLoadingEnabled = other.getLazyLoadingEnabled();
        }
        if (other.getAggressiveLazyLoading() != null) {
            this.aggressiveLazyLoading = other.getAggressiveLazyLoading();
        }
        if (other.getMultipleResultSetsEnabled() != null) {
            this.multipleResultSetsEnabled = other.getMultipleResultSetsEnabled();
        }
        if (other.getUseColumnLabel() != null) {
            this.useColumnLabel = other.getUseColumnLabel();
        }
        if (other.getUseGeneratedKeys() != null) {
            this.useGeneratedKeys = other.getUseGeneratedKeys();
        }

        if (other.getSafeRowBoundsEnabled() != null) {
            this.safeRowBoundsEnabled = other.getSafeRowBoundsEnabled();
        }
        if (other.getSafeResultHandlerEnabled() != null) {
            this.safeResultHandlerEnabled = other.getSafeResultHandlerEnabled();
        }
        if (other.getMapUnderscoreToCamelCase() != null) {
            this.mapUnderscoreToCamelCase = other.getMapUnderscoreToCamelCase();
        }
        if (other.getCallSettersOnNulls() != null) {
            this.callSettersOnNulls = other.getCallSettersOnNulls();
        }
        if (other.getUseActualParamName() != null) {
            this.useActualParamName = other.getUseActualParamName();
        }
        if (other.getReturnInstanceForEmptyRow() != null) {
            this.returnInstanceForEmptyRow = other.getReturnInstanceForEmptyRow();
        }
        if (other.getShrinkWhitespacesInSql() != null) {
            this.shrinkWhitespacesInSql = other.getShrinkWhitespacesInSql();
        }
        if (other.getNullableOnForEach() != null) {
            this.nullableOnForEach = other.getNullableOnForEach();
        }
        if (other.getArgNameBasedConstructorAutoMapping() != null) {
            this.argNameBasedConstructorAutoMapping = other.getArgNameBasedConstructorAutoMapping();
        }

        if (other.getAutoMappingBehavior() != null) {
            this.autoMappingBehavior = other.getAutoMappingBehavior();
        }
        if (other.getAutoMappingUnknownColumnBehavior() != null) {
            this.autoMappingUnknownColumnBehavior = other.getAutoMappingUnknownColumnBehavior();
        }

        if (other.getLogPrefix() != null) {
            this.logPrefix = other.getLogPrefix();
        }
        if (other.getLocalCacheScope() != null) {
            this.localCacheScope = other.getLocalCacheScope();
        }
        if (other.getJdbcTypeForNull() != null) {
            this.jdbcTypeForNull = other.getJdbcTypeForNull();
        }
        if (other.getLazyLoadTriggerMethods() != null) {
            this.lazyLoadTriggerMethods = other.getLazyLoadTriggerMethods();
        }
        if (other.getDefaultStatementTimeout() != null) {
            this.defaultStatementTimeout = other.getDefaultStatementTimeout();
        }
        if (other.getDefaultFetchSize() != null) {
            this.defaultFetchSize = other.getDefaultFetchSize();
        }
        if (other.getDefaultResultSetType() != null) {
            this.defaultResultSetType = other.getDefaultResultSetType();
        }
        if (other.getDefaultExecutorType() != null) {
            this.defaultExecutorType = other.getDefaultExecutorType();
        }
        if (other.getVariables() != null) {
            this.variables = other.getVariables();
        }
    }

    @Override
    public String toString() {
        return "MybatisConfig{" +
                "cacheEnabled=" + cacheEnabled +
                ", lazyLoadingEnabled=" + lazyLoadingEnabled +
                ", aggressiveLazyLoading=" + aggressiveLazyLoading +
                ", multipleResultSetsEnabled=" + multipleResultSetsEnabled +
                ", useColumnLabel=" + useColumnLabel +
                ", useGeneratedKeys=" + useGeneratedKeys +
                ", safeRowBoundsEnabled=" + safeRowBoundsEnabled +
                ", safeResultHandlerEnabled=" + safeResultHandlerEnabled +
                ", mapUnderscoreToCamelCase=" + mapUnderscoreToCamelCase +
                ", callSettersOnNulls=" + callSettersOnNulls +
                ", useActualParamName=" + useActualParamName +
                ", returnInstanceForEmptyRow=" + returnInstanceForEmptyRow +
                ", shrinkWhitespacesInSql=" + shrinkWhitespacesInSql +
                ", nullableOnForEach=" + nullableOnForEach +
                ", argNameBasedConstructorAutoMapping=" + argNameBasedConstructorAutoMapping +
                ", autoMappingBehavior=" + autoMappingBehavior +
                ", autoMappingUnknownColumnBehavior=" + autoMappingUnknownColumnBehavior +
                ", logPrefix='" + logPrefix + '\'' +
                ", localCacheScope=" + localCacheScope +
                ", jdbcTypeForNull=" + jdbcTypeForNull +
                ", lazyLoadTriggerMethods=" + lazyLoadTriggerMethods +
                ", defaultStatementTimeout=" + defaultStatementTimeout +
                ", defaultFetchSize=" + defaultFetchSize +
                ", defaultResultSetType=" + defaultResultSetType +
                ", defaultExecutorType=" + defaultExecutorType +
                ", variables=" + variables +
                '}';
    }
}
