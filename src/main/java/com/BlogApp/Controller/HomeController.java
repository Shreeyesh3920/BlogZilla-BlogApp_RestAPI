package com.BlogApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.ApiResponse;
@RestController
@RequestMapping("/api/")
public class HomeController {
	

		@GetMapping("/home")
		public ResponseEntity<ApiResponse> homeController()
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse("Welcome to Blogzilla!!",true),HttpStatus.OK);
		}
		
}
