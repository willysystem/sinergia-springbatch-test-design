package com.sinergia.springbatch.test.domain.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.sinergia.springbatch.test.domain.MedicalHistory;
import com.sinergia.springbatch.test.domain.Person;



/**
 * 
 * @author willy
 *
 */
public class Generate {

	public static void main(String[] args) {
		
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://ec2-54-214-97-192.us-west-2.compute.amazonaws.com:3306/dcargo","root", "Monamis_123!");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spring_batch","postgres", "postgres");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		MetadataSources metadata = new MetadataSources(
			    new StandardServiceRegistryBuilder()
			        .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
			        .build());
		metadata.addAnnotatedClass(Person.class);
		metadata.addAnnotatedClass(MedicalHistory.class);
		
		SchemaExport export = new SchemaExport(
			    (MetadataImplementor) metadata.buildMetadata(),
			    conn // pre-configured Connection here
		);
		export.create(true, true);
		


			
	}

}
