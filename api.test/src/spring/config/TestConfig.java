package spring.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.manager.UserManager;
import spring.manager.impl.UserManagerImpl;
import spring.repository.UserRepository;
import spring.repository.impl.JdbcUserRepositoryImpl;

@Configuration
public class TestConfig {

	@Autowired
	DataSource dataSourceMitrais;
	
	@PersistenceUnit
	EntityManagerFactory dataSourceJPA;
	
	@Bean
	public UserRepository userRepository(){
		UserRepository repository = new JdbcUserRepositoryImpl(dataSourceJPA, dataSourceMitrais);
		return repository;
	}
	
	@Bean
	public UserManager userManager(){
		UserManager manager = new UserManagerImpl(userRepository());
		return manager;
	}
	
}
