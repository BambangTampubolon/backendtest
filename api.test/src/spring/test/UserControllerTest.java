package spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.google.gson.Gson;
import spring.controller.UserController;
import spring.manager.impl.UserManagerImpl;
import spring.util.ErrorDetail;
import spring.viewmodel.MasterViewModel;	


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private UserManagerImpl userManager;
	
	@Test
	public void isNotExistMobileNumber() throws Exception{
		Mockito.when(userManager.isExistEmail(Mockito.anyString())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8081/api.test/v1/User/082133999623")
				.accept(MediaType.ALL_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		/*
		 * expected returned json
			{
			  "content": {
			    "status": 204,
			    "message": "NO_CONTENT",
			    "detailmessage": null,
			    "executiontime": 0,
			    "detailInfo": "NO_CONTENT"
			  }
			}
		*/ 
		MasterViewModel expectedReturnedData = new MasterViewModel();
		ErrorDetail info = new ErrorDetail();
		info.setMessage(HttpStatus.OK.name());
		info.setStatus(HttpStatus.OK.value());
		info.setDetailInfo(HttpStatus.OK);
		expectedReturnedData.setContent(info);
		
		Gson gson = new Gson();
		String expectedJson = gson.toJson(expectedReturnedData);
		JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void isExistMobileNumber() throws Exception{
		Mockito.when(userManager.isExistEmail(Mockito.anyString())).thenReturn(false);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8081/api.test/v1/User/082133999623")
				.accept(MediaType.ALL_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		/*excpected return json
		 * {
			  "content": {
			    "status": 200,
			    "message": "OK",
			    "detailmessage": null,
			    "executiontime": 0,
			    "detailInfo": "OK"
			  }
			}
		*/
		
		MasterViewModel expectedReturnedData = new MasterViewModel();
		ErrorDetail info = new ErrorDetail();
		info.setMessage(HttpStatus.NO_CONTENT.name());
		info.setStatus(HttpStatus.NO_CONTENT.value());
		info.setDetailInfo(HttpStatus.NO_CONTENT);
		expectedReturnedData.setContent(info);
		
		Gson gson = new Gson();
		String expectedJson = gson.toJson(expectedReturnedData);
		JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}
	
//	@Test
//	public void saveNewUser(){
//		ErrorDetail expectedInfo = new ErrorDetail();
//		expectedInfo.setStatus(HttpStatus.OK.value());
//		expectedInfo.setMessage(HttpStatus.OK.name());
//		expectedInfo.setDetailInfo(HttpStatus.OK);
//		expectedInfo.setDetailmessage(1);
//		
//		Mockito.when(userManager.isExistEmail(Mockito.anyString())).thenReturn(false);
//		Mockito.when(userManager.isExistMobileNumber(Mockito.anyString())).thenReturn(false);
//		
//		Mockito.when(userManager.getInfo()).thenReturn(expectedInfo);
//		MasterViewModel
//		Gson gson = new Gson();
//		String expectedJson = gson.
//	}
	
}
