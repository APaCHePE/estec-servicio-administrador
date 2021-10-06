package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Proveedor;
import com.pe.estec.rowmapper.ProveedorRowMapper;

@Repository
public class AccesoAdminRepository {
	@Autowired
	private JdbcTemplate sqlServer;
	
	public Boolean validarPass(String usuario, String clave)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("Select count(*) from pruebas..proveedor ");
		sql.append("where 1='1' ");
		sql.append(" and usuario = ? and pass = ? ");
		sql.append("  ");
		Object[] params = new Object[] {usuario, clave};
		boolean valido =false;
		Integer resultados = sqlServer.queryForObject(sql.toString(), Integer.class, params);
		if(resultados>0)
			valido=true;
		
		return valido;
	}
	
	public List<Proveedor> listarEmpleado(Integer estado, Integer tipoCuenta, String usuario,
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
		sql.append(" order by 1 asc ");
		List<Proveedor> listaProveedores = sqlServer.query(sql.toString(), new ProveedorRowMapper());
		return listaProveedores;
	}
}
