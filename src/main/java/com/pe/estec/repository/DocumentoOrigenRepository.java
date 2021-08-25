package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.DocumentoOrigen;
import com.pe.estec.rowmapper.DocumentoOrigneContratoRowMapper;

@Repository
public class DocumentoOrigenRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public List<DocumentoOrigen> consultaDocumento(String idContrato){
		StringBuilder sql = new StringBuilder();
		sql.append("  Select * ");
		sql.append(" From pruebas..contrato ");
		sql.append(" where id_contrato = ?  ");
		sql.append(" Order by id_contrato ");
		Object[] params = new Object[] {idContrato };
		List<DocumentoOrigen> listaDocumentos= 
			sqlServer.query(sql.toString(),new DocumentoOrigneContratoRowMapper(), params );
		return listaDocumentos;
	}
}
