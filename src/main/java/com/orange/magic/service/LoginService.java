package com.orange.magic.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.orange.magic.data.UserDB;
import com.orange.magic.domain.User;
import com.orange.magic.util.ResponseUtil;

@WebServlet("/login")
public class LoginService extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User provideUser = mapper.readValue(request.getReader(), User.class);
		User user = UserDB.selectUser(provideUser.getUsername());
		if (user == null || !user.getPassword().equals(provideUser.getPassword())) {
			ResponseUtil.generateJsonResponse(response, 400, "Username or password incorrect.", null);
		}
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionid", session.getId());
		ResponseUtil.generateJsonResponse(response, 200, "OK", map);
	}
}

