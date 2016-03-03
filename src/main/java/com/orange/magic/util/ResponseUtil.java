package com.orange.magic.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ResponseUtil {
	
	public static void generateJsonResponse(HttpServletResponse response, int statusCode, String message, Map<String, Object> map) {
		try {
			response.setContentType("application/json");
			response.setStatus(statusCode);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("message", message);
			if (map != null) {
				for(String key : map.keySet()) {
					responseMap.put(key, map.get(key));
				}
			}
			mapper.writeValue(response.getWriter(), responseMap);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
