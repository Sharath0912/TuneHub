package com.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Users;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

//	@PostMapping("/register")
//	public String addUser(@RequestParam("username") String username, @RequestParam("email") String email,
//			@RequestParam("password") String password, @RequestParam("gender") String gender,
//			@RequestParam("role") String role, @RequestParam("address") String address) 
//	{
//		System.out.println(username+" "+email+" "+password+" "+gender+" "+role+" "+address);
//		return "home";
//							When you run it in console the data will be printed
//	}

	// Or USe this

//	@PostMapping("/register")
//	public String addUser(@ModelAttribute Users user) {
////		System.out.println(user.getUsername() + " " + user.getEmail() + " " + user.getPassword() + " "
////				+ user.getGender() + " " + user.getRole() + " " + user.getAddress());
//		
//		service.addUsers(user);
//		
//		return "home";
//	}

	@Autowired
	UsersService service;

	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus = service.emailExists(user.getEmail());
		if (userStatus == false) {
			service.addUser(user);
			System.out.println("User Added");
		} else {
			System.out.println("User Exists");
		}
		return "home";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		if (service.validateUser(email, password) == true) 
		{
			String role = service.getRole(email);

			session.setAttribute("email", email);

			if (role.equals("admin")) 
			{
				return "adminHome";
			}
			else 
			{
				Users user = service.getUser(email);
				
				if (user.isPremium() == true) {
					return "display";
				} 
				else {
					return "customerHome";
				}
			}
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

}
