package io.github.jzdayz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@SpringBootApplication
public class DemoDynamicDatasourceApplication {

	public static void main(String[] args) {
		try(
				ConfigurableApplicationContext context =
						SpringApplication.run(DemoDynamicDatasourceApplication.class, args);
				){
			DataSource ds = context.getBean(DataSource.class);
			DsDemo dsDemo = context.getBean(DsDemo.class);
			dsDemo.t1(ds);
		}

	}

}
