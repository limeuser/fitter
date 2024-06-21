package com.fishqq.fitter.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public class MapperExecutor {
    private final SqlSessionFactory sqlSessionFactory;

    public MapperExecutor(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public <R, T> R query(Class<T> mapperClass, Function<T, R> handler) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            T mapper = session.getMapper(mapperClass);
            return handler.apply(mapper);
        }
    }

    public <T> void exec(Class<T> mapperClass, Consumer<T> handler) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            T mapper = session.getMapper(mapperClass);
            handler.accept(mapper);
        }
    }
}
