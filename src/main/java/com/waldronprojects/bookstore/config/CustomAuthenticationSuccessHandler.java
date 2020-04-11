package com.waldronprojects.bookstore.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.service.UserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) 
										throws IOException, ServletException {

		String userName = authentication.getName();
		User user = userService.findUserName(userName);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath());
	}

}
