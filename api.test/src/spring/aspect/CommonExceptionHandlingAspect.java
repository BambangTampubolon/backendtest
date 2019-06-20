package spring.aspect;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import spring.util.ErrorDetail;
import spring.util.StaticVariable;

@Aspect
@Component
@ControllerAdvice
public class CommonExceptionHandlingAspect extends ResponseEntityExceptionHandler{
	public final static String BEFORE = "'Before'";
	public final static String AROUND = "'Around'";
	public final static String AFTER = "'After'";
	public final static String AFTERTHROWING = "'After Throwing'";
	public final static String AFTERRETURNING = "'After Returning'";
	public final static String LOG4J_PROPERTIES = StaticVariable.LOG4J_PATH;

	private Logger logger = Logger.getLogger(getClass());

	private ErrorDetail errorDetail;

	public ErrorDetail getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(ErrorDetail errorDetail) {
		this.errorDetail = errorDetail;
	}

	// ############ RETRIEVE CATCH ERROR ############
	@AfterThrowing(value = "execution(* find*(..))", throwing = "e")
	public void retrieveErrorLog(JoinPoint joinPoint, Exception e) {
		PropertyConfigurator.configure(LOG4J_PROPERTIES);
		ErrorDetail _fillErrorDetail = new ErrorDetail();
		_fillErrorDetail.setDetailmessage("Retrieve Failed - " + e.getMessage());
		_fillErrorDetail.setStatus(HttpStatus.CONFLICT.value());
		_fillErrorDetail.setMessage(HttpStatus.CONFLICT.name());
		this.setErrorDetail(_fillErrorDetail);
		logger.info(AFTERTHROWING + " advice implementation - " + joinPoint.getTarget().getClass() + //
				"; Executing before " + joinPoint.getSignature().getName() + //
				"() method");
		logger.info(AFTERTHROWING + " return the following reward object : " + e.getMessage());
	}

	// ############ INSERT CATCH ERROR ############
	@AfterThrowing(value = "execution(* save*(..))", throwing = "e")
	public void insertErrorLog(JoinPoint joinPoint, Exception e) {
		PropertyConfigurator.configure(LOG4J_PROPERTIES);
		ErrorDetail _fillErrorDetail = new ErrorDetail();
		_fillErrorDetail.setDetailmessage(joinPoint.getTarget().getClass() + " - function "
				+ joinPoint.getSignature().getName() + " - Insert Data Failed - " + e.getMessage());
		_fillErrorDetail.setStatus(HttpStatus.CONFLICT.value());
		_fillErrorDetail.setMessage(HttpStatus.CONFLICT.name());
		this.setErrorDetail(_fillErrorDetail);
		logger.info(AFTERTHROWING + " advice implementation - " + joinPoint.getTarget().getClass() + //
				"; Executing before " + joinPoint.getSignature().getName() + //
				"() method");
		logger.info(AFTERTHROWING + " return the following reward object : " + e.getMessage());
	}

	// ############ UPDATE CATCH ERROR ############
	// 20170927 - di value di set 'edit' karena kalau pake 'update' malah error,
	// aneh !!!!!! --> kemungkinan karena 'update' adalah keyword
	@AfterThrowing(value = "execution(* update_*(..))", throwing = "e")
	public void updateErrorLog(JoinPoint joinPoint, Exception e) {
		PropertyConfigurator.configure(LOG4J_PROPERTIES);
		ErrorDetail _fillErrorDetail = new ErrorDetail();
		_fillErrorDetail.setDetailmessage("Update Failed - " + e.getMessage());
		_fillErrorDetail.setStatus(HttpStatus.CONFLICT.value());
		_fillErrorDetail.setMessage(HttpStatus.CONFLICT.name());
		this.setErrorDetail(_fillErrorDetail);
		logger.info(AFTERTHROWING + " advice implementation - " + joinPoint.getTarget().getClass() + //
				"; Executing before " + joinPoint.getSignature().getName() + //
				"() method");
		logger.info(AFTERTHROWING + " return the following reward object : " + e.getMessage());
	}

	// ############ CALL SP CATCH ERROR ############
	@AfterThrowing(value = "execution(* callSp*(..))", throwing = "e")
	public void callSpErrorLog(JoinPoint joinPoint, Exception e) {
		PropertyConfigurator.configure(LOG4J_PROPERTIES);
		ErrorDetail _fillErrorDetail = new ErrorDetail();
		_fillErrorDetail.setDetailmessage("Call SP Failed - " + e.getMessage());
		_fillErrorDetail.setStatus(HttpStatus.CONFLICT.value());
		_fillErrorDetail.setMessage(HttpStatus.CONFLICT.name());
		this.setErrorDetail(_fillErrorDetail);
		logger.info(AFTERTHROWING + " advice implementation - " + joinPoint.getTarget().getClass() + //
				"; Executing before " + joinPoint.getSignature().getName() + //
				"() method");
		logger.info(AFTERTHROWING + " return the following reward object : " + e.getMessage());
	}

	// ############ EXCEPTION HANDLING ############
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> retrieveHandleConflict(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex, this.getErrorDetail(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
