package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class BankController {
//
//	@Autowired
//	private CustomerService customerService ;
//
//	public BankController()
//	{
//		super();
//		this.customerService = new CustomerServiceImpl();
//	}
//
//	@GetMapping("/register")
//	public String showCustomerRegistration(Model model) {
//		Customer customer = new Customer();
//		model.addAttribute("customer", customer);
//		return "register_form";
//	}
//
//		//@PostMapping("/register")
//
//		@RequestMapping(method = RequestMethod.POST, path = "/register")
//		public String submitForm( @Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult)
//
//		//submitForm() method is used to submit the form data. It is annotated with @PostMapping annotation.
//		//It accepts two arguments:
//		//User object annotated with @ModelAttribute annotation.
//		//BindingResult object annotated with @Valid annotation.
//		//The BindingResult object is used to validate the form data.
//		//If the form data is valid, the submitForm() method will return the register_success.html page.
//		//If the form data is invalid, the submitForm() method will return the register_form.html page.
//		//The BindingResult object contains the error messages.
//		//The error messages are displayed on the register_form.html page.
//		//The BindingResult object is used to validate the form data.
//		//BindingResult purpose is to hold the result of the validation and binding and contains errors that may have occurred.
//		//ModelAttribute purpose is to bind the form data with the model object.
//
//		{
//			if(bindingResult.hasErrors())
//			{
//				System.out.println("Total count of errors are = "+bindingResult.getErrorCount());
//				 return "register_form";
//			}
//			System.out.println(customer);
//			customerService.addCustomer(customer);
//
//			return "register_success";
//		}
//
////		@GetMapping("/register/{id}" )
////		public String showUser( @PathVariable("id") int userId )
////		{
////			User user = new User("Snehal W","snehal@gmail.com", "12345", "Female", null, false, null, null);
////			return user;
////		}
}
