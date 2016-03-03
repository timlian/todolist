package com.orange.magic.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.orange.magic.data.UserDB;
import com.orange.magic.domain.User;
import com.orange.magic.util.EmailUtil;
import com.orange.magic.util.ResponseUtil;

@WebServlet("/register")
public class RegisterService extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(request.getReader(), User.class);
		
		String emailAddress = user.getUsername();
		if (!EmailUtil.isValidEmailAddress(emailAddress)) {
			ResponseUtil.generateJsonResponse(response, 400, "Invalide email address.", null);
			return ;
		} 
		if (UserDB.emailExists(emailAddress)) {
			ResponseUtil.generateJsonResponse(response, 400, "This email address has already been registered.", null);
			return ;
		}
		int count = UserDB.insert(user);
		if (count < 1) {
			ResponseUtil.generateJsonResponse(response, 500, "Unable to store user.", null);
			return ;
		}
		ResponseUtil.generateJsonResponse(response, 200, "OK", null);
	}
}
