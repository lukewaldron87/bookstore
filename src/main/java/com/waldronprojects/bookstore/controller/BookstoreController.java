package com.waldronprojects.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookstoreController {

	@GetMapping("/")
	public String showHome() {
		System.out.println("in BookstoreController showHome");
		return "home";
	}

}
