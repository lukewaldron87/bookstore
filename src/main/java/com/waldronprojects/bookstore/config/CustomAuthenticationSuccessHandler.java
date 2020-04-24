package com.waldronprojects.bookstore.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.waldronprojects.bookstore.entity.Role;
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
		String requestPath = getRedirectPathForRole(authentication,
													request);
		response.sendRedirect(requestPath);
	}
	
	private String getRedirectPathForRole(Authentication authentication,
										  HttpServletRequest request) {
		String userRoles = authentication.getAuthorities().toString();
		String requestPath = request.getContextPath();;
		if(userRoles.contains("EMPLOYEE")) {
			requestPath = "/bookstore/employee/showEmployeeMenu";
			//requestPath = "/bookstore/employee/employee-menu";
		}
		return requestPath;
	}

}
