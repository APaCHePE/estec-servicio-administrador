package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Proveedor;
import com.pe.estec.rowmapper.ProveedorRowMapper;

@Repository
public class ConsultaDocumentoRepository {

	@Autowired
	JdbcTemplate dao;
	
	public List<Proveedor> consultaOrdenes() {
		addElement();
		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT  *   FROM [RSCONCAR].[dbo].[UT0030] order by 1 desc");
		sql.append("Select id_proveedor, nombre from user_provee ");
		List<Proveedor> users = dao.query(sql.toString(),new ProveedorRowMapper());
		return users;
	}
	private void addElement() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into user_provee (id_proveedor, nombre) values(2, 'ESTECS')");
		dao.update(sql.toString());
	}
}
