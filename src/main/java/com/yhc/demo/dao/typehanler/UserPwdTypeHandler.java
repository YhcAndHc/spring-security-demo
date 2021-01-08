package com.yhc.demo.dao.typehanler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 注册接口，密码自动加密，业务层无须加密处理
 * 
 * @author yhc
 * @date 2021-1-8
 */
public class UserPwdTypeHandler implements TypeHandler<String> {

	private static final Logger log = LoggerFactory.getLogger(UserPwdTypeHandler.class);

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		log.info("# typehandler," + parameter);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		ps.setString(i, passwordEncoder.encode(parameter));
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName);
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex);
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex);
	}

}
