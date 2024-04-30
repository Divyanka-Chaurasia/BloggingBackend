package com.dollop.app.handler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.dollop.app.dtos.ApiResponseMessage;
import com.dollop.app.exception.BadApiRequestException;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.exception.ResourceNotFoundException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException e)
	{
		ApiResponseMessage response = ApiResponseMessage.builder() 
				.msg(e.getMessage()) 
				.status(HttpStatus.NOT_FOUND) 
				.success(true).build();
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		Map<String,Object> response = new HashMap<>();
		allErrors.stream().forEach(objectError->{
			String message = objectError.getDefaultMessage();
			String field =  ((FieldError) objectError).getField();
			response.put(field, message);
		});
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}	
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<ApiResponseMessage> duplicateEntryException(DuplicateEntryException ex)
	{
		ApiResponseMessage response = ApiResponseMessage.builder() 
				.msg(ex.getMessage()) 
				.status(HttpStatus.BAD_REQUEST) 
				.success(true).build();
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}	
	@ExceptionHandler(BadApiRequestException.class)
	public ResponseEntity<ApiResponseMessage> badResponseException(BadApiRequestException ex)
	{
		ApiResponseMessage response = ApiResponseMessage.builder() 
				.msg(ex.getMessage()) 
				.status(HttpStatus.BAD_REQUEST) 
				.success(true).build();
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}