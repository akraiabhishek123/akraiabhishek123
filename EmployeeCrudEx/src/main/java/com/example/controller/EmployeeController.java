package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	/***
	 * if End user enters/register in address bar
	 * this method is called and loads
	 * EmployeeRegister.html page from /template folder
	 * @return
	 */
	@GetMapping("/register")
	public String showRegister() {
		return "EmployeeRegister";
		
	}
	
	/**
	 * on form submit (/save+post), Read data as Object using @ModelAttribute
	 * Call service layer with object, read ID back
	 * create message as String     
	 * use Model memory,send message to UI
	 * Return back to Employee.html
	 */
		@PostMapping("/save")
		public String saveEmployee(@ModelAttribute Employee employee,Model model) {
			Integer id=service.saveEmployee(employee);
			String message = "Employee '"+id+"' Created";
			model.addAttribute("message",message);
			return "EmployeeRegister";
			
		}
		/****
		 * Fetch data from DB using service
		 * send data to UI using Model
		 * Return to EmployeedData.html
		 */
		@GetMapping("/all")
			public String viewAllEmployees(Model model)
			{
			List<Employee> list  = service.getAllEmployees();
			model.addAttribute("list",list);	
			return "EmployeeData";
			}
		/***
		 * Read id from Request URL
		 * call service for delete 
		 * get latest data
		 * create success message
		 * send data to UI using Model
		 * return back to Employee.html
		 */
		@GetMapping("/delete")
		public String deleteEmployee(@RequestParam Integer id,Model model)
		{
			//call service
			service.deleteEmployee(id);
			String message="Employee '"+id+"' Deleted";
			model.addAttribute("message", message);
			
			//get latest data
			List<Employee> list=service.getAllEmployees();
			model.addAttribute("list", list);
			return "EmployeeData";
		}
		
		/**
		 * show edit
		 * read id from request param
		 * load DB row using findById
		 * send object to UI using Model
		 * use Thymeleaf From Reads data from object and fill it
		 */
		@GetMapping("/edit")
		public String showEmployeeEdit(@RequestParam Integer id,Model model) {
			//load object from DB
			Employee emp = service.getOneEmployee(id);
			//send object to UI
			model.addAttribute("employee", emp);
			return "EmployeeEdit";
		}
		 /**
		  * do update
		  * Read From date From Edit page
		  * call service
		  * redirect back to all
		  */
		@PostMapping("/update")
		public String updateEmployee(@ModelAttribute Employee employee) {
			service.updateEmployee(employee);
			return "redirect:all";
			
		}
}
		

