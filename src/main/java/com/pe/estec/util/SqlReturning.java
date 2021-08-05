package com.pe.estec.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class SqlReturning {
	JdbcTemplate jdbctemplate;
	
	public SqlReturning(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	
	public long insertaData(String sql, Object[] params) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbctemplate.update(con ->queryDb(con, sql, params), keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	private PreparedStatement queryDb(Connection con, String sql, Object[] params)throws SQLException {
		int indice = 1;
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		for (Object parametro : params) {
			if(parametro instanceof String) {
				ps.setString(indice, parametro.toString());
			}else if(parametro instanceof Integer) {
				ps.setInt(indice, Integer.parseInt(parametro.toString()));
			}else if(parametro instanceof Double) {
				ps.setDouble(indice, Double.parseDouble(parametro.toString()));
			}else if(parametro instanceof Long) {
				ps.setLong(indice, Long.parseLong(parametro.toString()));
			}else if(parametro instanceof Boolean) {
				ps.setBoolean(indice, Boolean.parseBoolean(parametro.toString()));
			}
			indice++;
		}
		return ps;
	}

}
