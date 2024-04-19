package com.example.demo.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.DataNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> error = new HashMap<String, String>();

//		ex.getBindingResult().getFieldError().forEach(err->{
//			e
//		})

		return error;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataNotFoundException.class)
	public Map<String, String> handleBusinessEx(DataNotFoundException ex) {
		Map<String, String> error = new HashMap<String, String>();

		error.put("error msg", ex.getMessage());
		return error;

	}

}
