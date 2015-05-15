package org.deng.error;

import org.deng.controller.TestController;
import org.deng.domain.ErrorMsg;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes={TestController.class} )
public class ExampleResponseEntityExceptionHandler extends
		ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		String message = ex.getMessage();
		ErrorMsg errorMsg = new ErrorMsg(-99, message);

		return new ResponseEntity<Object>(errorMsg, headers, status);
		// return super.handleExceptionInternal(ex, body, headers, status,
		// request);
	}

	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<Object> handlerRuntimeException(Exception ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, null, headers, status, request);
	}
	
	@ExceptionHandler(value={ExampleException.class})
	public ResponseEntity<Object> handlerExampleException(ExampleException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, null, headers, status, request);
	}

}
