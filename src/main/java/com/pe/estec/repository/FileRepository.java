package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Archivo;
import com.pe.estec.rowmapper.FileRowMapper;

@Repository
public class FileRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public Archivo recuperaEntidadArchivo(int tipoEntidad,int  idEntidad,int entidadArchivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select id_archivo as idArchivo, archivo as archivo ");
		sql.append(" FROM pruebas..archivo_repositorio ");
		sql.append(" where 1='1' ");
		if(tipoEntidad!=0)sql.append(" and id_tipo_parametro = ? ");
		if(idEntidad!=0)sql.append("  and id_documento = ? ");
		if(entidadArchivo!=0)sql.append(" and id_documento_auxiliar = ? ");		
		Object[] params = new Object[] { tipoEntidad,idEntidad,entidadArchivo};
				
		System.out.println(sql.toString());
		List<Archivo> listaArchivos= sqlServer.query(sql.toString(),new FileRowMapper(), params);
		return listaArchivos.get(0);	
	}
	
	public void guardarArchivoCabecera() {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select id_archivo as idArchivo, archivo as archivo ");
		sql.append(" FROM pruebas..archivo_repositorio ");
//		sql.append(" where 1=1");
		System.out.println(sql.toString());
		sqlServer.update(sql.toString());
	}
}
