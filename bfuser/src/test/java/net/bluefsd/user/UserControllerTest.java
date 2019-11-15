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
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bluefsd.main.BaseTestController;
import net.bluefsd.security.AppConfig;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ AppConfig.class })
public class UserControllerTest extends BaseTestController {
	static int userId = 0;

	//@Test
	public void u_1_addAdmin() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/create");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		// UserManager.createUser(request);
		// UserManager.createUser1(request);
		// UserManager.createMentor(request);
		UserManager.createAdmin(request);
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.id").isNumber());
		matcher.andExpect(jsonPath("$.ret").value("success"));
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		userId = (Integer) object.get("id");
		printResponse(matcher);
	}

	//@Test
	public void u_2_addUser() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/create");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		UserManager.createUser(request);
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.id").isNumber());
		matcher.andExpect(jsonPath("$.ret").value("success"));
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		int userId = (Integer) object.get("id");
		printResponse(matcher);
	}

	//@Test
	public void u_4_update() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/update");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		UserManager.update(request, userId);
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.ret").value("success"));
	}
	
	@Test
	public void login() throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "111111";
		String enPwd = encoder.encode(rawPassword);
		
		ResultActions matcher = this.mockMvc
				.perform(post("/user/login").param("username", "usky").param("password", enPwd)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		 
		//matcher.andDo()
		// matcher.andExpect(jsonPath("$.id").value(2));
	}
	
	//@Test
	public void canAccess() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/canaccess");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);

		
		ResultActions matcher = this.mockMvc
				.perform( request)
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		// matcher.andExpect(jsonPath("$.id").value(2));
	}

}
