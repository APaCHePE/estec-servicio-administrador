package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Catalogo;
import com.pe.estec.model.CatalogoContabilidad;
import com.pe.estec.rowmapper.CatalogoContabilidadRowMappper;
import com.pe.estec.rowmapper.CatalogoRowMapper;

@Repository
public class CatalogoRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public List<Catalogo> consultaParametro(Integer idParametroTipo){
		StringBuilder sql = new StringBuilder();
		sql.append("  Select id_parametro, id_parametro_tipo, nombre, abreviatura, estado ");
		sql.append(" From PRUEBAS.DBO.parametro ");
		if(idParametroTipo != null)sql.append(" where id_parametro_tipo ="+idParametroTipo);
		List<Catalogo> listaCatalogo= 
				sqlServer.query(sql.toString(),new CatalogoRowMapper() );
		return listaCatalogo;
	}
	public List<CatalogoContabilidad> catalogoContabilidad(String idParametro){
		StringBuilder sql = new StringBuilder();
		sql.append("  Select * ,TCOD+TCLAVE AS CadeBus From pruebas.dbo.DETRACCIONES ");
		sql.append(" Where TCOD='"+idParametro+"' Order by TCOD+TCLAVE; ");
		List<CatalogoContabilidad> listaCatalogo=sqlServer.query(sql.toString(),new CatalogoContabilidadRowMappper());
		return listaCatalogo;
	}
}
