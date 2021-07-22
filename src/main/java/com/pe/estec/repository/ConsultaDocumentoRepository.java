package com.pe.estec.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultaDocumentoRepository {

	JdbcTemplate sqlServer;
	
	public Integer consultaOrdenes() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  *   FROM [RSCONCAR].[dbo].[UT0030] order by 1 desc");
		sql.append("");
		sql.append("");
		Integer users = sqlServer.queryForObject(sql.toString(), Integer.class);
		return users;
	}
}
