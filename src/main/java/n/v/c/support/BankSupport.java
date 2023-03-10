package n.v.c.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import n.v.c.dto.HistoryBank;
import net.minidev.json.JSONObject;

@Component
public class BankSupport {

	private static final String URL_LOGIN = "https://ebank.tpb.vn/gateway/api/auth/login";
	private static final String URL_FIND = "https://ebank.tpb.vn/gateway/api/smart-search-presentation-service/v1/account-transactions/find";

	public String getToken() {

		String createPersonUrl = URL_LOGIN;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject data = new JSONObject();
		data.appendField("username", "00085118");
		data.appendField("password", "Baongoc@2018");
		HttpEntity<String> request = new HttpEntity<>(data.toJSONString(), headers);
		JSONObject answer = restTemplate.postForObject(createPersonUrl, request, JSONObject.class);

		return answer.getAsString("access_token");

	}

	public List<HistoryBank> getHistory() {
		String createPersonUrl = URL_FIND;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + getToken());

		JSONObject data = new JSONObject();
		data.appendField("accountNo", "00085118001");
		data.appendField("currency", "VND");
		data.appendField("fromDate", "20221001");
		data.appendField("toDate", "20221009");
		data.appendField("keyword", "");

		HttpEntity<String> request = new HttpEntity<>(data.toJSONString(), headers);
		JSONObject answer = restTemplate.postForObject(createPersonUrl, request, JSONObject.class);
		String allPays = answer.getAsString("transactionInfos").replace("[{", "").replace("}]", "");
		String[] values = allPays.split("\\}, \\{"); // moi json
		List<HistoryBank> historyPayEasymooc = new ArrayList<>();
		for (String value : values) {
			if (value.indexOf("easymooc") > 1) {
				HistoryBank historyBank = new HistoryBank();
				for (String tmp : value.split(", ")) {// tmp key = value
					String[] map = tmp.split("=");
					if ("id".equals(map[0])) {
						historyBank.setAmount(map[1]);
					} else if ("description".equals(map[0])) {
						historyBank.setDescription(map[1]);
					} else if ("amount".equals(map[0])) {
						historyBank.setAmount(map[1]);
					} else if ("ofsAcctNo".equals(map[0])) {
						historyBank.setOfsAcctNo(map[1]);
					} else if ("ofsAcctName".equals(map[0])) {
						historyBank.setOfsAcctName(map[1]);
					}
				}
				historyPayEasymooc.add(historyBank);
			}
		}
		return historyPayEasymooc;
	}

	public List<HistoryBank> checkPayByContent(String otp) {
		String yesterday = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String createPersonUrl = URL_FIND;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + getToken());

		JSONObject data = new JSONObject();
		data.appendField("accountNo", "00085118001");
		data.appendField("currency", "VND");
		data.appendField("fromDate", yesterday);
		data.appendField("toDate", today);
		data.appendField("keyword", otp);

		HttpEntity<String> request = new HttpEntity<>(data.toJSONString(), headers);
		JSONObject answer = restTemplate.postForObject(createPersonUrl, request, JSONObject.class);
		String allPays = answer.getAsString("transactionInfos").replace("[{", "").replace("}]", "");
		String[] values = allPays.split("\\}, \\{"); // moi json
		List<HistoryBank> historyPayEasymooc = new ArrayList<>();
		for (String value : values) {
			if (value.indexOf(otp) > 1) {
				HistoryBank historyBank = new HistoryBank();
				for (String tmp : value.split(", ")) {// tmp key = value
					String[] map = tmp.split("=");
					if ("id".equals(map[0])) {
						historyBank.setAmount(map[1]);
					} else if ("description".equals(map[0])) {
						historyBank.setDescription(map[1]);
					} else if ("amount".equals(map[0])) {
						historyBank.setAmount(map[1]);
					} else if ("ofsAcctNo".equals(map[0])) {
						historyBank.setOfsAcctNo(map[1]);
					} else if ("ofsAcctName".equals(map[0])) {
						historyBank.setOfsAcctName(map[1]);
					}
				}
				historyPayEasymooc.add(historyBank);
			}
		}

		return historyPayEasymooc;
	}

}
