package utils.typeHandlers;

import enums.ValidDateEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidDateTypeHandler extends BaseTypeHandler<ValidDateEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ValidDateEnum validDateEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, validDateEnum.name());
    }

    @Override
    public ValidDateEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.getString(s).equals("now")){return ValidDateEnum.NOW;}
        if (resultSet.getString(s).equals("next")){return ValidDateEnum.NEXT;}
        return null;
    }

    @Override
    public ValidDateEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.getString(i).equals("now")){return ValidDateEnum.NOW;}
        if (resultSet.getString(i).equals("next")){return ValidDateEnum.NEXT;}
        return null;
    }

    @Override
    public ValidDateEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
