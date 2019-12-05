package net.bluefsd.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bluefsd.main.BaseTestController;
import net.bluefsd.user.AppConfig;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ AppConfig.class })
public class UserControllerTest extends BaseTestController {
	
	@Test
	public void u_1_addAdmin() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		UserManager.createAdmin(map);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/create");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
 		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		// userId = (Integer) object.get("id");
		printResponse(matcher);
	}

	@Test
	public void u_2_addUser() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		UserManager.createUser(map);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/create");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		//int userId = (Integer) object.get("id");
		printResponse(matcher);
		matcher.andExpect(jsonPath("$.status").value(0));

	}

	@Test
	public void u_4_update() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/update");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		UserManager.update(map);
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}

	@Test
	public void u_5_login() throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "111111";
		String enPwd = encoder.encode(rawPassword);
		ObjectMapper mapper = new ObjectMapper();
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/login");
		Map<String, Object> map = new HashMap<>();
		map.put("userName", "usky");
		map.put("password", rawPassword);
		request.content(mapper.writeValueAsString(map));
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);

		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}

	@Test
	public void u_6_canAccess() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/canaccess");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);

		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		matcher.andDo(MockMvcResultHandlers.print());
		 

	}

	@Test
	public void u_7_sendMail() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/mail");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		UserManager.update(map);
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);

		 
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}

	@Test
	public void u_8_activate() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/activate");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		request.param("verifyCode", "AVRMAAHMD4BA7X9K4VPEQM7NESDJWSRL");
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}

}
