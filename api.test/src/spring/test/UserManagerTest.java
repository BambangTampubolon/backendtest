package spring.test;

import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import spring.entity.User;
import spring.manager.impl.UserManagerImpl;
import spring.repository.impl.JdbcUserRepositoryImpl;
import spring.util.StaticVariable;
import spring.viewmodel.UserViewModel;

@RunWith(SpringRunner.class)
public class UserManagerTest {
	@InjectMocks	
	private UserManagerImpl userManager;
	@Mock
	private JdbcUserRepositoryImpl userRepository;
	@Mock
	private User entity;
	
	@Test
	public void testSaveNewUser() throws ParseException {
		when(entity.getFirst_name()).thenReturn("bembeng");
		when(entity.getLast_name()).thenReturn("bartam");
		when(entity.getMobile_number()).thenReturn("6282133999623");
		when(entity.getEmail()).thenReturn("bembeng.bartam@gmail.com");
		when(entity.isGender()).thenReturn(true);
		when(entity.getDate_of_birth())
				.thenReturn(StaticVariable.string2Date("2019-06-11", StaticVariable.DATE_PATTERN));
		when(entity.getId()).thenReturn(new Long(1));

		when(userRepository.saveNewUser(Mockito.any(User.class))).thenReturn(entity);

		UserViewModel viewModel = new UserViewModel();
		viewModel.setFirst_name("bembeng");
		viewModel.setLast_name("bartam");
		viewModel.setMobile_number("6282133999623");
		viewModel.setEmail("bembeng.bartam@gmail.com");
		viewModel.setGender(true);
		viewModel.setDate_of_birth("2019-06-11");

		userManager.saveNewUser(viewModel);

		Assert.assertEquals(userManager.getInfo().getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(userManager.getInfo().getMessage(), HttpStatus.OK.name());
		Assert.assertEquals(userManager.getInfo().getDetailmessage(), entity.getId());
		Assert.assertEquals(userManager.getInfo().getDetailInfo(), HttpStatus.OK);
	}
}
