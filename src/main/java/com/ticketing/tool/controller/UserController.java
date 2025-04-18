package com.ticketing.tool.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.tool.entity.User;
import com.ticketing.tool.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController implements Serializable {

	private static final long serialVersionUID = 2119216600193006701L;

	@Autowired
	private UserService userService;

	@GetMapping("/userName")
	public ResponseEntity<?> getUserName(@RequestParam String companyName) {
		List<String> userNames = userService.getUserName(companyName);
		return new ResponseEntity<>(userNames, HttpStatus.OK);
	}

	@GetMapping("/getAllusers")
	public ResponseEntity<?> getAllUsers(){
         List<User> users =  userService.getAllUsers();
		 if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(users);
	}

}
