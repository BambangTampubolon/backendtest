package spring.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.entity.User;
import spring.repository.UserRepository;

@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

	private EntityManager entityManager;
	private JdbcTemplate jdbcTemplate;

	public JdbcUserRepositoryImpl(EntityManagerFactory entityManagerFactory, DataSource dataSource) {
		this.entityManager = entityManagerFactory.createEntityManager();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean isExistMobileNumber(String mobileNumber) {
		String sql = "SELECT COUNT(1) FROM user_data2 WHERE mobile_number = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mobileNumber);
		return null != count && count > 0;
	}

	@Override
	public boolean isExistEmail(String email) {
		String sql = "SELECT COUNT(1) FROM user_data2 WHERE email = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
		return null != count && count > 0;
	}

	@Override
	public User saveNewUser(User entity) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		return entity;
	}

}
