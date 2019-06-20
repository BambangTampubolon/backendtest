package spring.controller;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import spring.manager.UserManager;
import spring.util.ErrorDetail;
import spring.viewmodel.MasterViewModel;
import spring.viewmodel.UserViewModel;

@CrossOrigin(origins = "*")
@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@Api(value = "TestUser", description = "Operations pertaining to User", tags = "User")
public class UserController {
	@Autowired
	private UserManager userManager;

	@ApiOperation(value = "CHECK MOBILE NUMBER ISEXIST", response = MasterViewModel.class)
	@GetMapping("v1/User/{mobile_number}")
	public ResponseEntity<MasterViewModel> checkIsMobileNumberExist(@PathVariable("mobile_number") String mobileNumber) {
		MasterViewModel viewModel = new MasterViewModel();
		ErrorDetail info = new ErrorDetail();
		if (!userManager.isExistMobileNumber(mobileNumber)) {
			info.setMessage(HttpStatus.NO_CONTENT.name());
			info.setStatus(HttpStatus.NO_CONTENT.value());
			info.setDetailInfo(HttpStatus.NO_CONTENT);
			viewModel.setContent(info);
			return new ResponseEntity<MasterViewModel>(viewModel, (HttpStatus) HttpStatus.OK);
		}
		info.setMessage(HttpStatus.OK.name());
		info.setStatus(HttpStatus.OK.value());
		info.setDetailInfo(HttpStatus.OK);
		viewModel.setContent(info);
		return new ResponseEntity<MasterViewModel>(viewModel, (HttpStatus)info.getDetailInfo());
	}
	
	@ApiOperation(value = "CHECK EMAIL ISEXIST", response = MasterViewModel.class)
	@GetMapping("v2/User/")
	public ResponseEntity<MasterViewModel> checkIsEmailExist(@RequestParam String email) {
		MasterViewModel viewModel = new MasterViewModel();
		ErrorDetail info = new ErrorDetail();
		if (!userManager.isExistEmail(email)) {
			info.setMessage(HttpStatus.NO_CONTENT.name());
			info.setStatus(HttpStatus.NO_CONTENT.value());
			info.setDetailInfo(HttpStatus.NO_CONTENT);
			viewModel.setContent(info);
			return new ResponseEntity<MasterViewModel>(viewModel, (HttpStatus) HttpStatus.OK);
		}
		info.setMessage(HttpStatus.OK.name());
		info.setStatus(HttpStatus.OK.value());
		info.setDetailInfo(HttpStatus.OK);
		viewModel.setContent(info);
		return new ResponseEntity<MasterViewModel>(viewModel, (HttpStatus)info.getDetailInfo());
	}
	
	@ApiOperation(value = "SAVE NEW USER", response = MasterViewModel.class)
	@PostMapping("v1/User")
	public ResponseEntity<MasterViewModel> saveNewUser(@RequestBody UserViewModel viewModel, BindingResult bindingResult) throws ParseException{
		MasterViewModel result = new MasterViewModel<>();
		userManager.saveNewUser(viewModel);
		ErrorDetail info = userManager.getInfo();
		result.setContent(info);
		return new ResponseEntity<MasterViewModel>(result, (HttpStatus) info.getDetailInfo());
	}
}
