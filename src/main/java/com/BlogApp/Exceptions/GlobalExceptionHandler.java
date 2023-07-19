package com.BlogApp.Exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.*;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.BlogApp.Payloads.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> allExceptionhandler(Exception ex)
	{
		String message=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> userNotFoundException(UserNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ApiResponse> categoryNotFoundException(CategoryNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse response=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String,String>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,HttpServletRequest request)
	{
		Map<String,String> response=new HashMap<>();
		
		response.put("Error", ex.getMessage());
		response.put("Path",request.getServletPath());
		response.put("timeStamp", new Date().toString());
		response.put("Status",HttpStatus.BAD_REQUEST.toString());
	
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> constraintViolationException(ConstraintViolationException ex,HttpServletRequest request)
	{
		Map<String,String> response =new HashMap<>();
		response.put("Error", ex.getMessage());
		response.put("Status",HttpStatus.BAD_REQUEST.toString());
		response.put("TimeStamp", new Date().toString());
		response.put("Path",request.getServletPath() );
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String,String>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex,HttpServletRequest request)
	{
		Map<String,String> response =new HashMap<>();
		response.put("Error", ex.getMessage());
		response.put("Status",HttpStatus.BAD_REQUEST.toString());
		response.put("TimeStamp", new Date().toString());
		response.put("Path",request.getServletPath() );
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<Map<String,String>> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex,HttpServletRequest request)
	{
		Map<String,String> response =new HashMap<>();
		response.put("Error", ex.getMessage());
		response.put("Status",HttpStatus.BAD_REQUEST.toString());
		response.put("TimeStamp", new Date().toString());
		response.put("Path",request.getServletPath() );
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_GATEWAY);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
		String fieldName= ((FieldError)error).getField();
		String message=error.getDefaultMessage();
		resp.put(fieldName, message);
		});
		
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	
}
