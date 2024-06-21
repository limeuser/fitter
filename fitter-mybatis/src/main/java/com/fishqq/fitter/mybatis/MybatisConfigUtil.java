package com.fishqq.fitter.mybatis;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.HashSet;

public class MybatisConfigUtil {
    public static Configuration createDefault(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        return new Configuration(environment);
    }

    public static Configuration create(DataSource dataSource, MybatisConfig config) {
        Configuration configuration = createDefault(dataSource);
        copyConfig(configuration, config);
        return configuration;
    }

    public static void copyConfig(Configuration configuration, MybatisConfig config) {
        if (config.getCacheEnabled() != null) {
            configuration.setCacheEnabled(config.getCacheEnabled());
        }
        if (config.getLazyLoadingEnabled() != null) {
            configuration.setLazyLoadingEnabled(config.getLazyLoadingEnabled());
        }
        if (config.getAggressiveLazyLoading() != null) {
            configuration.setAggressiveLazyLoading(config.getAggressiveLazyLoading());
        }
        if (config.getMultipleResultSetsEnabled() != null) {
            configuration.setMultipleResultSetsEnabled(config.getMultipleResultSetsEnabled());
        }
        if (config.getUseColumnLabel() != null) {
            configuration.setUseColumnLabel(config.getUseColumnLabel());
        }
        if (config.getUseGeneratedKeys() != null) {
            configuration.setUseGeneratedKeys(config.getUseGeneratedKeys());
        }

        if (config.getSafeRowBoundsEnabled() != null) {
            configuration.setSafeRowBoundsEnabled(config.getSafeRowBoundsEnabled());
        }
        if (config.getSafeResultHandlerEnabled() != null) {
            configuration.setSafeResultHandlerEnabled(config.getSafeResultHandlerEnabled());
        }
        if (config.getMapUnderscoreToCamelCase() != null) {
            configuration.setMapUnderscoreToCamelCase(config.getMapUnderscoreToCamelCase());
        }
        if (config.getCallSettersOnNulls() != null) {
            configuration.setCallSettersOnNulls(config.getCallSettersOnNulls());
        }
        if (config.getUseActualParamName() != null) {
            configuration.setUseActualParamName(config.getUseActualParamName());
        }
        if (config.getReturnInstanceForEmptyRow() != null) {
            configuration.setReturnInstanceForEmptyRow(config.getReturnInstanceForEmptyRow());
        }
        if (config.getShrinkWhitespacesInSql() != null) {
            configuration.setShrinkWhitespacesInSql(config.getShrinkWhitespacesInSql());
        }
        if (config.getNullableOnForEach() != null) {
            configuration.setNullableOnForEach(config.getNullableOnForEach());
        }
        if (config.getArgNameBasedConstructorAutoMapping() != null) {
            configuration.setArgNameBasedConstructorAutoMapping(config.getArgNameBasedConstructorAutoMapping());
        }

        if (config.getAutoMappingBehavior() != null) {
            configuration.setAutoMappingBehavior(config.getAutoMappingBehavior());
        }
        if (config.getAutoMappingUnknownColumnBehavior() != null) {
            configuration.setAutoMappingUnknownColumnBehavior(config.getAutoMappingUnknownColumnBehavior());
        }

        if (config.getLogPrefix() != null) {
            configuration.setLogPrefix(config.getLogPrefix());
        }
        if (config.getLocalCacheScope() != null) {
            configuration.setLocalCacheScope(config.getLocalCacheScope());
        }
        if (config.getJdbcTypeForNull() != null) {
            configuration.setJdbcTypeForNull(config.getJdbcTypeForNull());
        }
        if (config.getLazyLoadTriggerMethods() != null) {
            configuration.setLazyLoadTriggerMethods(new HashSet<>(config.getLazyLoadTriggerMethods()));
        }
        if (config.getDefaultStatementTimeout() != null) {
            configuration.setDefaultStatementTimeout(config.getDefaultStatementTimeout());
        }
        if (config.getDefaultFetchSize() != null) {
            configuration.setDefaultFetchSize(config.getDefaultFetchSize());
        }
        if (config.getDefaultResultSetType() != null) {
            configuration.setDefaultResultSetType(config.getDefaultResultSetType());
        }
        if (config.getDefaultExecutorType() != null) {
            configuration.setDefaultExecutorType(config.getDefaultExecutorType());
        }
    }
}
