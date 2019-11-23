package net.bluefsd.upload;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.bluefsd.main.BaseTestController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ AppConfig.class })
public class UploadControllerTest extends BaseTestController {

	public void add5() {
		String filePath = "C:\\lydoc\\FSD\\finalassess\\500112-5.xlsx";

	}

	@Test
	public void add6() throws IOException {
		String filePath = "C:\\lydoc\\FSD\\finalassess\\500112-7.xlsx";
		testAdd(filePath);
	}

	public void testAdd(String filePath) throws IOException {

		// String filePath = "C:\\lydoc\\FSD\\finalassess\\500112-5.xlsx";

		int index = filePath.lastIndexOf("\\");
		String fileName = filePath.substring(index + 1, filePath.length());
		System.out.println("-------" + fileName);

		try {
			FileInputStream fis = new FileInputStream(filePath);

			MockMultipartFile file = new MockMultipartFile("file", fileName, "multipart/form-data", fis);

			ResultActions matcher = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload/excel").file(file));

			MvcResult mvcResult = matcher.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String result = mvcResult.getResponse().getContentAsString();

			matcher.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
