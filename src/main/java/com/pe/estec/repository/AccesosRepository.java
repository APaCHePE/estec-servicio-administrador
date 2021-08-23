package com.pe.estec.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccesosRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public Boolean validarPass(String usuario, String clave)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("Select count(*) from pruebas..proveedor ");
		sql.append("where 1='1' ");
		sql.append(" and usuario = ? and pass = ? ");
		sql.append("  ");
		Object[] params = new Object[] {usuario, clave};
		boolean valido =false;
		Integer resultados = sqlServer.queryForObject(sql.toString(), Integer.class, params);
		if(resultados>0)
			valido=true;
		
		return valido;
	}
}
