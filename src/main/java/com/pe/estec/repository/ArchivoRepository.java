package com.pe.estec.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Archivo;
import com.pe.estec.util.SqlReturning;

@Repository
public class ArchivoRepository {

	@Autowired
	private JdbcTemplate sqlServer;

	public Integer guardarArchivo(Archivo archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS..ARCHIVO  ");
		sql.append(
				" (ID_009_TIPO_ENTIDAD, ID_ENTIDAD, ID_ENTIDAD_ARCHIVO, NOMBRE_ARCHIVO, VERSION, FECHA_CREACION, ESTADO, TOKEN) ");
		sql.append(" VALUES( ");
		sql.append(archivo.getIdParametro());
		sql.append(", ");
		sql.append(archivo.getIdDocumento());
		sql.append(", ");
		sql.append(archivo.getIdDocumentoArchivo());
		sql.append(", '");
		sql.append(archivo.getNombreArchivo());
		sql.append("', 1, GETDATE(), 1, '");
		sql.append(archivo.getToken());
		sql.append("') ");
		//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
		SqlReturning db = new SqlReturning(sqlServer);
		Long idGenerado = db.insertaDataParams(sql.toString());
		return idGenerado.intValue();
	}

	public void guardarArchivoRepo(Archivo archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS..ARCHIVO_REPOSITORIO  ");
		sql.append(" (ID_ARCHIVO,ARCHIVO) ");
		sql.append(" VALUES(?,?) ");
		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
		sqlServer.update(sql.toString(), params);
	}

	public void versionarTemporal(String token, Integer idEntidad) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PRUEBAS..ARCHIVO ");
		sql.append(" SET TOKEN = NULL,  ID_ENTIDAD = " + idEntidad);
		sql.append(" WHERE TOKEN = '" + token + "' ");
		sqlServer.update(sql.toString());
	}

	public void actualizarTemporal(String token, Integer idEntidad) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PRUEBAS..ARCHIVO ");
		sql.append(" SET TOKEN = NULL,  ID_ENTIDAD = " + idEntidad);
		sql.append(" WHERE TOKEN = '" + token + "' ");
		sqlServer.update(sql.toString());
	}
}
