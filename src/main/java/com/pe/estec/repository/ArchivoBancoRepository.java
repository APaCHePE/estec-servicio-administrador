package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.ArchivoBanco;
import com.pe.estec.model.ArchivoBancoDetalle;
import com.pe.estec.model.Catalogo;
import com.pe.estec.rowmapper.ArchivoBancoDetalleRowMapper;
import com.pe.estec.util.SqlReturning;

@Repository
public class ArchivoBancoRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public Integer crearLoteArchivo(ArchivoBanco archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS.dbo.LOTE_ARCHIVO ");
		sql.append(" ( fec_programacion, id_001_estado, ");
		sql.append(" id_usuario_registro, usuario_registro, fec_creacion, ");
		sql.append(" id_009_banco) ");
		sql.append(" VALUES ");
		sql.append(" ( ?, 30, ?, 'CFF', GETDATE (), ?) ");
		Object[] params = new Object[] { archivo.getFechaProgramacion(),
				archivo.getIdUsuarioRegistro(), 
				archivo.getId009Banco()};

		SqlReturning db = new SqlReturning(sqlServer);
		Long idGenerado = db.insertaData(sql.toString(), params);
		return idGenerado.intValue();
	}
	
	public Integer crearLoteDetalleArchivo(ArchivoBancoDetalle archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS.dbo.LOTE_ARCHIVO_DETALLE ");
		sql.append(" (id_comprobante, id_lote_archivo, id_usuario_registro) ");
		sql.append(" VALUES ");
		sql.append(" (?,?,?) ");
		Object[] params = new Object[] { archivo.getIdComprobante(), archivo.getIdArchivoBanco(), archivo.getIdUsuarioRegistro()};
		SqlReturning db = new SqlReturning(sqlServer);
		Long idGenerado = db.insertaData(sql.toString(), params);
		return idGenerado.intValue();
	}
	
	public List<ArchivoBanco> obtenerListaArchivos(Integer idLoteArchivo) {
		StringBuilder sql = new StringBuilder();
		sql.append ( " select la.id_lote_archivo as idArchivoBanco, id_007_tipo_comprobante id007TipoComprobante,	fec_programacion fechaProgramacion,	 ");
		sql.append ( " id_001_estado id001Estado, la.id_usuario_registro idUsuarioRegistro, usuario_registro usuarioRegistro,	fec_creacion	fechaCreacion, ");
		sql.append ( " fec_modificacion fechaModificacion,	id_009_banco id009Banco ");
		sql.append ( " , (select count(*) from  pruebas..lote_archivo_detalle aux where aux.id_lote_archivo = la.id_lote_archivo ) as cantidadRegistros ");
		sql.append ( " from pruebas..lote_archivo la ");
//		sql.append ( " left join pruebas..lote_archivo_detalle lad on lad.id_lote_archivo = la.id_lote_archivo ");
//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
//		sqlServer.update(sql.toString(), params);
		List<ArchivoBanco> listaArchivos = sqlServer.query(sql.toString(), BeanPropertyRowMapper.newInstance(ArchivoBanco.class));
		return listaArchivos;
	}
	
	public List<ArchivoBancoDetalle> obtenerListaArchivosDetalle(Integer idArchivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lad.id_lote_archivo_detalle idArchivoBancoDetalle, lad.id_lote_archivo idArchivoBanco, lad.id_comprobante idComprobante, lad.id_usuario_registro idUsuarioRegistro ");
		sql.append("  from pruebas..lote_archivo_detalle lad   ");
		sql.append("  left join pruebas..lote_archivo la on la.id_lote_archivo = lad.id_lote_archivo  ");
		sql.append("  where lad.id_lote_archivo = "+idArchivo);
//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
//		sqlServer.update(sql.toString(), params);
		List<ArchivoBancoDetalle> listaArchivos = sqlServer.query(sql.toString(), BeanPropertyRowMapper.newInstance(ArchivoBancoDetalle.class));
		return listaArchivos;
	}
	
}
