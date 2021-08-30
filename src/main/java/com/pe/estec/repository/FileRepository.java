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
	
	public Archivo recuperaEntidadArchivo(int tipoEntidad,int  idEntidad,int entidadArchivo, String token) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select a.id_archivo as idArchivo, a.nombre_archivo, ar.archivo ");
		sql.append(" FROM pruebas..archivo a ");
		sql.append(" join pruebas..archivo_repositorio ar on ar.id_archivo = a.id_archivo ");
		sql.append(" where 1='1' ");
		if(tipoEntidad!=0)sql.append(" and a.ID_009_TIPO_ENTIDAD = "+tipoEntidad);
		if(idEntidad!=0)sql.append("  and a.ID_ENTIDAD = "+idEntidad);
		if(entidadArchivo!=0)sql.append(" and a.ID_ENTIDAD_ARCHIVO = "+entidadArchivo);
		if(!token.equals("0"))sql.append(" and a.TOKEN = '"+token+"'");
		
		System.out.println(sql.toString());
		List<Archivo> listaArchivos= sqlServer.query(sql.toString(),new FileRowMapper());
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
