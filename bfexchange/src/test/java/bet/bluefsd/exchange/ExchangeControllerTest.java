package bet.bluefsd.exchange;

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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bluefsd.exchange.AppConfig;
import net.bluefsd.main.BaseTestController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ AppConfig.class })
public class ExchangeControllerTest extends BaseTestController {
	@Test
	public void u_1_add() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		ExchangeManager.create1(map);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/exchange/create");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);
	
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		//matcher.andExpect(jsonPath("$.exchCd").value("BSE"));
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		matcher.andExpect(jsonPath("$.status").value(0));
		printResponse(matcher);
	}

	@Test
	public void u_2_add() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		ExchangeManager.create2(map);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/exchange/add");
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		//matcher.andExpect(jsonPath("$.exchCd").value("NSE"));

		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		matcher.andExpect(jsonPath("$.status").value(0));
		printResponse(matcher);
	}

	@Test
	public void u_3_list() throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("fromserver", "MicroSVr test");
		ObjectMapper mapper = new ObjectMapper();
		RequestBuilder request = MockMvcRequestBuilders.post("/exchange/list").content(mapper.writeValueAsString(map))
				.header("Authorization", token).contentType(MediaType.APPLICATION_JSON_UTF8);

		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));// application/json;charset=UTF-8
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
		printResponse(matcher);
	}

	@Test
	public void u_5_list() throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("fromserver", "MicroSVr test");
		ObjectMapper mapper = new ObjectMapper();
		RequestBuilder request = MockMvcRequestBuilders.post("/exchange/listcompany").param("exchCd", "BSE")
				.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));// application/json;charset=UTF-8
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
		printResponse(matcher);
	}
	@Test
	public void u_4_update() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/exchange/update");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		ExchangeManager.update(map);
		request.content(mapper.writeValueAsString(map)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		matcher.andDo(MockMvcResultHandlers.print());
		matcher.andExpect(jsonPath("$.status").value(0));
	}

}
