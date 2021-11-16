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

@Repository
public class ArchivoBancoRepository {

	@Autowired
	private JdbcTemplate sqlServer;
	
	public Integer crearLoteArchivo(ArchivoBanco archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS..ARCHIVO_REPOSITORIO  ");
		sql.append(" (ID_ARCHIVO,ARCHIVO) ");
		sql.append(" VALUES(?,?) ");
//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
//		sqlServer.update(sql.toString(), params);
		return 1;
	}
	
	public List<ArchivoBanco> obtenerListaArchivos(Integer idLoteArchivo) {
		StringBuilder sql = new StringBuilder();
		sql.append ( " select la.id_lote_archivo as idArchivoBanco, id_007_tipo_comprobante id007TipoComprobante,	fec_programacion fechaProgramacion,	 ");
		sql.append ( " id_001_estado id001Estado, la.id_usuario_registro idUsuarioRegistro, usuario_registro usuarioRegistro,	fec_creacion	fechaCreacion, ");
		sql.append ( " fec_modificacion fechaModificacion,	id_009_banco id009Banco ");
		sql.append ( " , (select count(*) from  pruebas..lote_archivo_detalle aux where aux.id_lote_archivo = "+idLoteArchivo+" ) as cantidadRegistros ");
		sql.append ( " from pruebas..lote_archivo la ");
		sql.append ( " left join pruebas..lote_archivo_detalle lad on lad.id_lote_archivo = la.id_lote_archivo ");
//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
//		sqlServer.update(sql.toString(), params);
		List<ArchivoBanco> listaArchivos = sqlServer.query(sql.toString(), BeanPropertyRowMapper.newInstance(ArchivoBanco.class));
		return listaArchivos;
	}
	
	public List<ArchivoBancoDetalle> obtenerListaArchivosDetalle(ArchivoBanco archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO [dbo].[LOTE_ARCHIVO]  ");
		sql.append(" ([id_007_tipo_comprobante],[fec_programacion],[id_001_estado],[id_usuario_registro],[usuario_registro],[fec_creacion],[id_009_banco]) ");
		sql.append(" ([id_007_tipo_comprobante],[fec_programacion],[id_001_estado],[id_usuario_registro],[usuario_registro],[fec_creacion],[id_009_banco])");
		sql.append(" VALUES(?,?) ");
//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
//		sqlServer.update(sql.toString(), params);
		List<ArchivoBancoDetalle> listaArchivos = sqlServer.query(sql.toString(),new ArchivoBancoDetalleRowMapper());
		return null;
	}
	
}
