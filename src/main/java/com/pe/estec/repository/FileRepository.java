package com.pe.estec.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Archivo;

@Repository
public class FileRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public Archivo recuperaEntidadArchivo() {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select id_archivo as idArchivo, archivo as archivo ");
//		sql.append(" id_comprobante_detalle,id_comprobante,cantidad,unidad_medida ");
//		sql.append(" ,descripcion,valor_unitario,icbper ");
		sql.append(" FROM pruebas..archivo_repositorio ");
		sql.append(" SELECT * FROM pruebas..archivo_repositorio ");
		sql.append("ORDER BY id_archivo ");
		sql.append(" OFFSET 1 ROWS ");
		sql.append(" FETCH NEXT 1 ROWS ONLY");
//		sql.append(" where 1=1");
//		if(idComprobante!=null)sql.append(" and id_comprobante = '"+idComprobante+"' ");
		Archivo archivo = (Archivo) sqlServer.queryForMap(sql.toString(),new Archivo());
		return archivo;	
	}
}
