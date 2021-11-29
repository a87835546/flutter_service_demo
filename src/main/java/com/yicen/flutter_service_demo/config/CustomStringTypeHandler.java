package com.yicen.flutter_service_demo.config;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@MappedTypes({String.class,Date.class,Boolean.class})
@MappedJdbcTypes({JdbcType.VARCHAR,JdbcType.DATE,JdbcType.TINYINT})
public class CustomStringTypeHandler extends BaseTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i,o);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getObject(s) == null ? "" :resultSet.getObject(s);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getObject(i) == null ? "" : resultSet.getObject(i);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getObject(i) == null ? "" : callableStatement.getObject(i);
    }
}
