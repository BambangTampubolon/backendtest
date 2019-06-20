package spring.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import spring.util.StaticVariable;

@Configuration
@EnableTransactionManagement
@PropertySource("file:" + StaticVariable.PROPERTIES_PATH)
public class InfrastructureConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSourceMitrais() throws SQLException {
		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setURL(environment.getProperty("javax.persistence.jdbc.url"));
		dataSource.setUser(environment.getProperty("javax.persistence.jdbc.user"));
		dataSource.setPassword(environment.getProperty("javax.persistence.jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public EntityManagerFactory dataSourceJPA() throws SQLException {
		Map<String, String> properties = new HashMap<String, String>();
//		properties.put("javax.persistence.jdbc.password", environment.getProperty("javax.persistence.jdbc.password"));
		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
//		properties.put("javax.persistence.jdbc.url", environment.getProperty("javax.persistence.jdbc.url"));
//		properties.put("javax.persistence.jdbc.user", environment.getProperty("javax.persistence.jdbc.user"));
		properties.put("javax.persistence.provider", environment.getProperty("javax.persistence.provider"));
		properties.put("javax.persistence.jdbc.driver", environment.getProperty("javax.persistence.jdbc.driver"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		emf.setDataSource(dataSourceMitrais());
		emf.setPackagesToScan("spring.entity");
		emf.setPersistenceUnitName("USER_PERSISTENCE");
		emf.setJpaPropertyMap(properties);
		emf.setLoadTimeWeaver(new ReflectiveLoadTimeWeaver());
		emf.afterPropertiesSet();
		return emf.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManagerBasar() throws SQLException {
		return new DataSourceTransactionManager(dataSourceMitrais());
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
	
}
