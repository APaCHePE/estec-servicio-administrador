package com.pe.estec.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Persona;
import com.pe.estec.model.Proveedor;
import com.pe.estec.rowmapper.ProveedorErpRowMapper;
import com.pe.estec.rowmapper.ProveedorRowMapper;
import com.pe.estec.util.SqlReturning;

@Repository
public class UsuarioRepository {

	@Autowired
	JdbcTemplate sqlServer;

	public Boolean validarUsuarioERP(String nroDocumento) throws Exception {
		StringBuilder sql = new StringBuilder();
		Boolean existe = false;
		sql.append(" select COUNT(AC_CCODIGO) ");
		sql.append(" From RSCONCAR..CP0002MAES ");
		sql.append(" where trim(AC_CCODIGO) = " + nroDocumento + "");
		// Object[] params = new Object[] {nroDocumento};
		Integer cantidad = sqlServer.queryForObject(sql.toString(), Integer.class);
		System.out.println("cantidad erp:" + cantidad);
		if (cantidad > 0)
			existe = true;
		return existe;
	}

	public Boolean validarPersona(String nroDocumento) throws Exception {
		StringBuilder sql = new StringBuilder();
		Boolean existe = false;
		sql.append(" select COUNT(*) ");
		sql.append(" From pruebas..persona ");
		sql.append(" where trim(NRO_DOCUMENTO) = ?");
		Object[] params = new Object[] { nroDocumento.trim() };
		;
		try {
			Integer cantidad = sqlServer.queryForObject(sql.toString(), Integer.class, params);
			System.out.println("cantidad:" + cantidad);
			if (cantidad > 0)
				existe = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No existe ruc");
		}
		return existe;
	}

	public Boolean validarProveedor(String nroDocumento) throws Exception {
		StringBuilder sql = new StringBuilder();
		Boolean existe = false;
		sql.append(" select COUNT(*) ");
		sql.append(" From pruebas..proveedor prov ");
		sql.append(" left join pruebas..persona per on per.id_persona = prov.id_persona ");
		sql.append(" where trim(per.NRO_DOCUMENTO) = ? ");
		Object[] params = new Object[] { nroDocumento.trim() };
		try {
			Integer cantidad = sqlServer.queryForObject(sql.toString(), Integer.class, params);
			System.out.println("cantidad:" + cantidad);
			if (cantidad > 0)
				existe = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No existe ruc");
		}
		return existe;
	}

	public Long insertarProveedor(Proveedor proveedor) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into pruebas..proveedor");
		sql.append(" ( id_persona, usuario, pass, tip_cuenta, "
				+ " estado, fec_registro) ");
		sql.append(" values(?,?,?,?,?,GETDATE ()) ");
		Object[] params = new Object[] { proveedor.getPersona().getIdPersona(), proveedor.getUsuario().toUpperCase(),
				(proveedor.getContrasenia()!= null)?proveedor.getContrasenia().toUpperCase():"123456"
				, proveedor.getTipoCuenta(), Constantes.ESTADO_PENDIENTE };
		SqlReturning db = new SqlReturning(sqlServer);

		Long idGenerado = db.insertaData(sql.toString(), params);
		return idGenerado;
	}
	public Long insertarPersona(Persona persona) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into pruebas..persona");
		sql.append(" (NRO_DOCUMENTO, TIPO_DOCUMENTO,NOMBRES_COMPLETOS,");
//		sql.append(" NOMBRES, APE_PATERNO, APE_MATERNO, ");
		sql.append(" DIRECCION, TELEFONO_PRINCIPAL, ID_SISTEMA_REGISTRO, ESTADO, FEC_REGISTRO) ");
		sql.append(" values(?,'"+persona.getTipoDocumento()+"','"+persona.getNombreCompleto()+"','"+
			persona.getDireccion()+"','"+persona.getTelefonoPrincipal()
			+"','"+persona.getIdSistema()+"',?,GETDATE ()) ");
		Object[] params =  new Object[] { persona.getNroDocumento(), Constantes.ESTADO_ACTIVO };
		
		SqlReturning db = new SqlReturning(sqlServer);

		Long idGenerado = db.insertaData(sql.toString(), params);
		return idGenerado;
	}

	public List<Proveedor> listarProveedor(Integer estado, Integer tipoCuenta, String usuario,
			String nroDocumento, Integer tipoDocumento ) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select prov.id_proveedor, prov.usuario, prov.pass, prov.tip_cuenta, prov.id_provee_asoc, prov.estado, ");
		sql.append(" prov.fec_registro, prov.fec_modifi, pers.id_persona, pers.nro_documento, ");
		sql.append(" pers.nombres_completos, pers.telefono_principal ");
		sql.append(" from pruebas..proveedor prov ");
		sql.append(" right join pruebas..persona pers on pers.id_persona = prov.id_persona  ");
		sql.append("  where '1'=1 ");
		if (estado != null)
			sql.append(" and prov.estado = " + estado);
		if (tipoCuenta != null)
			sql.append(" and prov.tip_cuenta = " + tipoCuenta);
		if (usuario != null)
			sql.append(" and prov.usuario = '" + usuario.toUpperCase() + "' ");
		if (nroDocumento != null)
			sql.append(" and pers.nro_documento = '" + nroDocumento + "' ");
		if (tipoDocumento != null)
			sql.append(" and pers.tipo_documento = '" + tipoDocumento + "' ");

		List<Proveedor> listaProveedores = sqlServer.query(sql.toString(), new ProveedorRowMapper());
		return listaProveedores;
	}
	public void activarProveedor(Integer idProveedor, Integer estado, String observacion) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update pruebas..proveedor ");
		sql.append(" set  estado= "+estado+", FEC_MODIFI = GETDATE() ");
		if(estado==Constantes.ESTADO_DENEGADO && observacion!=null)sql.append(" ,observacion = '"+observacion+"'");
		sql.append(" where id_proveedor=? ");
		Object[] params= new Object[] {idProveedor};
		sqlServer.update(sql.toString(), params);
	}
	
	public List<Proveedor> listarProveedorErp(String nroDocumento ) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ac_cnombre as nombre_completo, ac_cdirecc as direccion, "
				+ " AC_CTELEF1 as telefono_principal, AC_CRUC as nro_Documento ");
		sql.append(" 		");
		sql.append(" where '1'=1 ");
		if(nroDocumento!= null)sql.append(" and AC_CRUC = trim(?) ");
		
		Object[] params = new Object[] {nroDocumento};
		List<Proveedor> listaProveedores = sqlServer.query(sql.toString(), new ProveedorErpRowMapper(), params);
		return listaProveedores;
	}
}
