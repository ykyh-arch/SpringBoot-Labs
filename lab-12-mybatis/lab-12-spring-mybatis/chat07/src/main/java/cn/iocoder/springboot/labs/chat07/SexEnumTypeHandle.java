package cn.iocoder.springboot.labs.chat07;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SexEnumTypeHandle，源码参考：{@link org.apache.ibatis.type.TypeHandler<T>}，通过 {@link org.apache.ibatis.type.TypeHandlerRegistry } 完成对类型处理器的统一注册
 *
 * @author fw001
 * @date 2023/09/25 16:37
 **/
@Slf4j
public class SexEnumTypeHandle  extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, parameter.getValue());
        log.info("{}", parameter);
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        log.info("{}", columnName);
        Object object = resultSet.getObject(columnName);
        Integer sex = object != null && object instanceof Integer ? (Integer) object : null;
        return SexEnum.getByValue(sex);
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        log.info("{}", columnIndex);
        Object object = resultSet.getObject(columnIndex);
        Integer sex = object != null && object instanceof Integer ? (Integer) object : null;
        return SexEnum.getByValue(sex);
    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        log.info("{}", columnIndex);
        Object object = callableStatement.getObject(columnIndex);
        Integer sex = object != null && object instanceof Integer ? (Integer) object : null;
        return SexEnum.getByValue(sex);
    }

}
