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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Specialization;
import com.example.service.ISpecialization;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecialization service;

	/**
	 * 1. display all Specialization
	 */

	@GetMapping("/register")
	public String showRegisterPage() {
		return "specializationRegister";
	}

	@PostMapping("/save")
	public String addSpecialization(@ModelAttribute Specialization specialization, Model model) {
		Long id = service.saveSpecialization(specialization);
		model.addAttribute("msg", "specializatio with   (" + id + " )created");
		return "specializationRegister";

	}

	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}

	@GetMapping("/delete")
	public String reomoveSpecialization(@RequestParam Long id, RedirectAttributes attributes) {
		service.removeSpecialization(id);
		attributes.addAttribute("message", "specialization deleted with (" + id + " )");
		return "redirect:all";

	}

	@GetMapping("/edit")
	public String showEdit(@RequestParam Long id, Model model) {
		Specialization specialization = service.getoneSpecialization(id);
		model.addAttribute("specialization", specialization);
		return "specializationEdit";
	}

	@PostMapping("/update")
	public String updateSpec(@ModelAttribute Specialization specialization, RedirectAttributes attributes) {
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "specialization updated with (" + specialization.getId() + " updated");
		return "redirect:all";
	}
}
