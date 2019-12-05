package net.bluefsd.company;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bluefsd.main.BaseTestController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ AppConfig.class })
public class CompanyControllerTest extends BaseTestController {
	static int userId = 0;

	@Test
	public void u_1_add() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
 		CompanyManager.create_1(map);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/add");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
 		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
	//	userId = (Integer) object.get("ceoName");
		printResponse(matcher);
	}

 @Test
	public void u_2_add() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		CompanyManager.create_2(map);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/add");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		// request.content(content);
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
	 	String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		matcher.andExpect(jsonPath("$.status").value(0));
		printResponse(matcher);
	}

	@Test
	public void u_4_update() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/update");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		CompanyManager.update(map);
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}

	@Test
	public void u_4_listCompany() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/listcompany");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
	 		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}
	//@Test
	public void u_56_listCompanybyName() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/searchcompanyname");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		request.param("searchStr", "Inter");
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}
	@Test
	public void u_6_listCompanyName() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/listcompanyname");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		 
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}

	@Test
	public void u_7_companycdnames() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/companycdnames");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		 
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}
	@Test
	public void u_8_matchcompany() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/company/matchcompany");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		request.param("searchStr", "neu");
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}
}
