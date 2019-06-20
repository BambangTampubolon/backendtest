package spring.manager.impl;

import java.text.ParseException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.User;
import spring.manager.UserManager;
import spring.repository.UserRepository;
import spring.util.ErrorDetail;
import spring.util.StaticVariable;
import spring.viewmodel.UserViewModel;

@Service
public class UserManagerImpl extends ManagerImpl implements UserManager {

	@Autowired
	private UserRepository userRepository;

	public UserManagerImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isExistEmail(String email) {
		return userRepository.isExistEmail(email);
	}

	@Override
	public boolean isExistMobileNumber(String mobileNumber) {
		return userRepository.isExistMobileNumber(mobileNumber);
	}

	@Override
	@Transactional
	public void saveNewUser(UserViewModel viewModel) throws ParseException {
		try {
			User entity = new User();
			BeanUtils.copyProperties(viewModel, entity);
			entity.setDate_of_birth(
					StaticVariable.string2Date(viewModel.getDate_of_birth(), StaticVariable.DATE_PATTERN));
			User entitySaved = userRepository.saveNewUser(entity);

			ErrorDetail info = new ErrorDetail();
			if (null != entitySaved && entitySaved.getId() > 0) {
				info.setStatus(HttpStatus.OK.value());
				info.setMessage(HttpStatus.OK.name());
				info.setDetailInfo(HttpStatus.OK);
				info.setDetailmessage(entitySaved.getId());
			} else {
				info.setStatus(HttpStatus.CONFLICT.value());
				info.setMessage(HttpStatus.CONFLICT.name());
				info.setDetailInfo(HttpStatus.CONFLICT);
				info.setDetailmessage("FAILED SAVING DATA");
			}
			this.setInfo(info);
		} catch (Exception e) {
			String errMsg = StaticVariable.starckTraceToString(e);
			setInfoConflict(errMsg);
		}

	}

}
