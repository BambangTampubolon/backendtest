package spring.manager;

import java.text.ParseException;

import spring.util.ErrorDetail;
import spring.viewmodel.UserViewModel;

public interface UserManager {
	public boolean isExistEmail(String email);
	public boolean isExistMobileNumber(String mobileNumber);
	public void saveNewUser(UserViewModel viewModel) throws ParseException;
	public ErrorDetail getInfo();
	public int getTotalRow();
}
