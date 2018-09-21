package com.example.demo.controllers;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Idea;

import com.example.demo.models.User;
import com.example.demo.services.IdeaService;
import com.example.demo.services.UserService;
import com.example.demo.validator.UserValidator;

@Controller
public class BeltController {
	private final UserService userService;
	private final UserValidator userValidator;
	private final IdeaService ideaService; 

	
	
	public BeltController(UserService userService, UserValidator userValidator, IdeaService ideaService) {
		this.userService=userService;
		this.userValidator=userValidator;
		this.ideaService=ideaService;

	}
	
	@RequestMapping("/registration")
	 public String registerForm(@ModelAttribute("user") User user) {
	     
		 
		 return "sign_in/signin.jsp";
	 }
	 @RequestMapping("/login")
	 public String login() {
	     
		 return "sign_in/signin.jsp";
	 }
	 
	 @RequestMapping(value="/registration", method=RequestMethod.POST)
	 public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
		 userValidator.validate(user, result);
		 if(result.hasErrors()) {
			 
			 model.addAttribute("errors", result);
			 return"/sign_in/signin.jsp";
		 }
		 else {
			
				 userService.registerUser(user);
			 session.setAttribute("user", user.getId());

		
		
			 
			 
			 
			 }
			
			 return"redirect:/ideas";
		 }
		 
		 
		 
		 // if result has errors, return the registration page (don't worry about validations just now)
	     // else, save the user in the database, save the user id in session, and redirect them to the /home route
	 
	 
	 	@RequestMapping(value="/login", method=RequestMethod.POST)
		public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,  Model model, HttpSession session) {
	 	
	 			
	 		boolean isAuthenticated = userService.authenticateUser(email, password);
	 			if(isAuthenticated) {
	 				User u = userService.findByEmail(email);
	 				session.setAttribute("user", u.getId() );
	 				model.addAttribute("user", new User());
	 				return "redirect:/ideas";
	 				}else {
	 					model.addAttribute("errors", "Invalid Credentials, please try again");
	 					model.addAttribute("user", new User());
	 					return "sign_in/signin.jsp";
	 				}
		
	 	}
	     // if the user is authenticated, save their user id in session
	     // else, add error messages and return the login page
	//
	 
	 @RequestMapping("/home")
	 public String home(HttpSession session, Model model) {
		 System.out.println(session.getAttribute("user"));
		 Long userId = (Long) session.getAttribute("user");
		 User u =userService.findUserById(userId);
		 model.addAttribute("user", u);
		 
		 

	     return"sign_in/home.jsp";
		 
		 
		
	 
	 }
	 
	 @RequestMapping(value="/logout/{id}")
 public String logout(HttpSession session,@PathVariable("id")Long id) {
		 User u = userService.findUserById(id);
		 session.getAttribute("user");
		 session.invalidate();
     return "redirect:/registration";
 		}
	 
		@RequestMapping("/ideas")
		public String dashboard(HttpSession session , Model model) {
			//if( session.getAttribute("User") == null) {
				//return "redirect:/registration";
			//}else {
			List<Idea> idea = ideaService.allIdea();
		Long userId= (Long) session.getAttribute("user");
		User u = userService.findUserById(userId);
		
		model.addAttribute("user", u);
		model.addAttribute("idea", idea);
			
			return "/ideas/dashboard.jsp";

		}
	//}

		@RequestMapping(value = "/ideas/new")
		public String createIdea(Model model) {
			
			Idea idea = new Idea();
			model.addAttribute("idea", idea);
			return "ideas/new.jsp";

		}
		@RequestMapping(value = "/ideas/new", method = RequestMethod.POST)
		public String process(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session,
				Model model) {
			if (result.hasErrors()) {

				model.addAttribute("errors", result);
				return "/ideas/new.jsp";
			} else {
				Long user_id = (Long) session.getAttribute("user");
				User u = userService.findUserById(user_id);
				
				ideaService.createIdea(idea);
				ideaService.joinIdea(idea, u);

				session.setAttribute("idea", idea.getId());
			}
			return "redirect:/ideas";
		}
		@RequestMapping(value = "/ideas/{id}")
		public String view(@PathVariable("id") Long id, Model model, HttpSession session) {
			Long user_id = (Long) session.getAttribute("user");
			User user = userService.findUserById(user_id);
			Optional<Idea> oneIdea = ideaService.oneIdea(id);
			Idea idea;

			if (oneIdea.isPresent()) {
				idea = oneIdea.get();

			} else {
				idea = null;
			}

			model.addAttribute("idea", idea);
			model.addAttribute("user", user);

			return "/ideas/view.jsp";
		}
		
		@RequestMapping(value = "/ideas/{id}", method = RequestMethod.POST)
		public String views(@PathVariable("id") Long id, Model model,
				HttpSession session) {
			Optional<Idea> oneIdea = ideaService.oneIdea(id);
			Idea idea;
			Long user_id = (Long) session.getAttribute("user");
			User user = userService.findUserById(user_id);

			if (oneIdea.isPresent()) {
				idea = oneIdea.get();

			} else {
				idea = null;
			}

			model.addAttribute("idea", idea);
			model.addAttribute("user", user);

			return "/ideas/view.jsp";
		}
		
		@RequestMapping(value = "/ideas/edit/{id}")
		public String edit(@PathVariable("id") Long id, @ModelAttribute("idea") Idea idea, Model model) {
			Optional<Idea> oneIdea = ideaService.oneIdea(id);
			List<User> user = userService.allUser();
			List<Idea> ideaAll = ideaService.allIdea();

			Idea i_dea;

			if (oneIdea.isPresent()) {
				i_dea = oneIdea.get();
			} else {
				i_dea= null;
			}

			model.addAttribute("current_idea", i_dea);
			model.addAttribute("user", user);
			model.addAttribute("ideas", ideaAll);

			return "/ideas/edit.jsp";
		}
		
		
		@RequestMapping(value = "/ideas/edit/{id}", method = RequestMethod.PUT)
		public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("idea") Idea idea,
				BindingResult result, Model model, HttpSession session) {

			if (result.hasErrors()) {
				System.out.println("has errors");
		
				model.addAttribute("errors", result);
				return "/ideas/edit.jsp";

			}

			else {

				Optional<Idea> oneIdea = ideaService.oneIdea(id);
				System.out.println("1");

				Long user_id = (Long) session.getAttribute("user");
				System.out.println("2");

				User user_critic = userService.findUserById(user_id);
				System.out.println("3");

				List<Idea> ideaAll = ideaService.allIdea();
				System.out.println("4");

				model.addAttribute("user", user_critic);
				model.addAttribute("ideas", oneIdea);

				ideaService.updateIdea(idea);

				
				return "redirect:/ideas";
			}
		}
		@RequestMapping(value = "ideas/delete/{id}", method = RequestMethod.DELETE)
		public String delete(@PathVariable("id") Long id) {
			ideaService.deleteIdea(id);
			return "redirect:/ideas";
		}
		@RequestMapping(value = "ideas/like/{id}")
		public String like(@PathVariable("id") Long id,HttpSession session  ) {
			Optional<Idea> oneIdea = ideaService.oneIdea(id);
			
			Idea i_dea;

			if (oneIdea.isPresent()) {
				i_dea = oneIdea.get();
			} else {
				i_dea= null;
			}
			
			
			Long user_id = (Long) session.getAttribute("user");
			User user = userService.findUserById(user_id);
			 ideaService.setLikes(i_dea, user);
			 return "redirect:/ideas";
			
		}

	}

