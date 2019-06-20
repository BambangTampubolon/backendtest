package spring.repository;

import spring.entity.User;

public interface UserRepository{
	public boolean isExistMobileNumber(String mobileNumber);
	public boolean isExistEmail(String email);
	public User saveNewUser(User user);
}
