package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Catalogo;
import com.pe.estec.rowmapper.CatalogoRowMapper;

@Repository
public class CatalogoRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public List<Catalogo> obtenerCatalogo(String idElemento, String idGrupo){
		StringBuilder sql = new StringBuilder();
		Boolean existe = false;
		sql.append("  Select case ");
		sql.append(" From RSCONCAR..CP0002MAES ");
		sql.append(" where trim(ac_cruc) = ? and ");
		sql.append(" Order by AC_CNOMBRE ");
		Object[] params = new Object[] {idElemento, idGrupo};
		List<Catalogo> listaCatalogo= 
				sqlServer.query(sql.toString(),new CatalogoRowMapper(), params );
		return listaCatalogo;
	}
}
