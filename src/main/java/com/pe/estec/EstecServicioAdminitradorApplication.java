package com.pe.estec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import com.pe.estec.model.Catalogo;

@SpringBootApplication
//@EnableEurekaClient
public class EstecServicioAdminitradorApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate dao;
	 
	public static void main(String[] args) {
		SpringApplication.run(EstecServicioAdminitradorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = " select tcod as idCatalogo, tclave as idGrupo,TDESCRI as nombre from  RSCONCAR.dbo.CT0002TAGP where TCLAVE='06' "; 
		try {
			List<Catalogo> catalogo = 
					dao.query(sql, BeanPropertyRowMapper.newInstance(Catalogo.class));			
			catalogo.forEach(System.out :: println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
