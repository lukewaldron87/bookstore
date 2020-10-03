package com.waldronprojects.bookstore.config;

import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	UserService userService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) 
										throws IOException, ServletException {

		String username = authentication.getName();
		User user = userService.findUsername(username);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		String targetUrl = getRedirectPathForRole(authentication,
													request);
		redirectStrategy.sendRedirect(request, response, targetUrl);

	}
	
	private String getRedirectPathForRole(Authentication authentication,
										  HttpServletRequest request) {
		String userRoles = authentication.getAuthorities().toString();
		String requestPath = "";
		if(userRoles.contains("EMPLOYEE")) {
			requestPath = "/employee/product/list";
		}
		return requestPath;
	}

}
