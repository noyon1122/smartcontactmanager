package com.smart.main.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.main.entities.User;
import com.smart.main.helper.Message;
import com.smart.main.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {

	@Autowired
	private UserRepo userRepo;
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	@GetMapping("/about")
	public String about()
	{
		return "about";
	}
	@GetMapping("/signup")
	public String signup(Model model){
		
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user,@RequestParam(value ="aggrement",defaultValue = "false")  boolean aggrement, Model model,HttpSession session )
	{
		
		try {
			if(!aggrement)
			{
				System.out.println("You have not agreed terms and conditions");
				throw new Exception("You have not agreed terms and conditions");
				
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			this.userRepo.save(user);
			model.addAttribute("user",new User());
			
			session.setAttribute("message", new Message("Successfully Registered !! ", "alert-success" ));
			return "signup";	 
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong"+e.getMessage(), "alert-danger" ));	
			return "signup";	
		}
		
	}
}
