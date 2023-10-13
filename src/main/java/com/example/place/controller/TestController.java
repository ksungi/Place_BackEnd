package com.example.place.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.place.DTO.ResponseDTO;
import com.example.place.DTO.TestRequestBodyDTO;
import com.example.place.service.UserService;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private UserService service;
	
	
	
	@GetMapping
	public String testController(){
		return "<h1>이곳은 테스트 페이지 입니다.</h1>";
	}
	
	@GetMapping("/{id}")
	public String testContollerWithPathVariables(@PathVariable(required =false) String id){
		return "입력한 ID: " + id;
	}
	
	@GetMapping("/Param")
	public String testContollerRequestParam(@RequestParam(required =false) String id){
		return "입력한 ID Param:  "+ id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
		return "test Request complete! <br/>ID: " + testRequestBodyDTO.getId() + "<br/>Message : " +
				testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String>testControllerResponseBody(){
		List<String> list = new ArrayList<String>();
		list.add("Hello World! I'm ResponseDTO");
		list.add("See you!");
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		
		return response;
	}
	
	@GetMapping("/testResponseEntityOk")
	public ResponseEntity<?>testControllerResponseEntityOk(){
		List<String> list =new ArrayList<String>();
		list.add("Hello World! I'm ResponseEntity. And you get 200!");
		list.add("See you!");
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/testResponseEntityBad")
	public ResponseEntity<?>testControllerResponseEntityBad(){
		List<String> list =new ArrayList<String>();
		list.add("Hello World! I'm ResponseEntity. And you get 400!");
		list.add("See you!");
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		
		return ResponseEntity.badRequest().body(response);
	}

}