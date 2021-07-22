package com.pe.estec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pe.estec.model.Proveedor;

@SpringBootApplication
public class EstecServicioAdminitradorApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate dao;
	 
	public static void main(String[] args) {
		SpringApplication.run(EstecServicioAdminitradorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "Select id_proveedor, nombre from user_provee  ";
		List<Proveedor> proveedores = 
				dao.query(sql, BeanPropertyRowMapper.newInstance(Proveedor.class));
		proveedores.forEach(System.out :: println);
	}

}
