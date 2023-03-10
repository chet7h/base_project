package n.v.c.controller;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;

import net.minidev.json.JSONObject;

@RestController
public class BankController {

	@RequestMapping({ "/hello" })
	public String firstPage() throws JsonParseException, IOException {

		String createPersonUrl = "https://ebank.tpb.vn/gateway/api/auth/login";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>("{\"username\":\"00085118\", \"password\":\"Baongoc@2018\"}",
				headers);
		JSONObject answer = restTemplate.postForObject(createPersonUrl, request, JSONObject.class);
		return answer.getAsString("access_token");

	}
}
