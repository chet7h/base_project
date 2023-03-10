package n.v.c.support;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;

@Component
public class CourseraSupport {
	private static final String URL_LOGIN = "https://www.coursera.org/api/login/v3";

	public String getToken() {

		String createPersonUrl = URL_LOGIN;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		headers.set("x-csrftoken", "lNwN3daQsSnr343PN1GMkXvP");
		headers.set("x-csrf3-token", "1666253471.CKDneejnknAqLxtL");
		headers.set("x-csrf2-token", "AjE5IWN0e84SXjYzY1sS3XMt");

		JSONObject data = new JSONObject();
		data.appendField("email", "chet7h@gmail.com");
		data.appendField("password", "yk@_QESH2Lp#@/u");
		HttpEntity<String> request = new HttpEntity<>("{\"email\":\"chet7h@gmail.com\",\"password\":\"yk@_QESH2Lp#@/u\",\"recaptchaToken\":\"03AIIukzj8OuqMq1bD7YCKRL2u8INQ3lwEQPOmZEr3y4jZzQqaO--iP82-B_7Ma-En-nqNmE7WdHmfvbQhehZ22t75Z7ViKkqBu6HB-qDIGoo-iQ0Z9DKeRlafh8K2vOyr1Z0iSNZnZbJFaIi-kiqI2nqZJ10e8jRzN_3lvKWXngP42uZCoJ7UQAtYltYc5WJyigVo_nW19Xrt3qcxJGPNDkPiHYLYwZ1Aw8t3HGnC_6dZBXFGKOz6Auswri8geQZAB0MsZ_9f1xyJeQDs0gmvpfTDiv9QRwfpn-sC6cd1dBDkTqntzAtzz0Dk-zm0641ej2Z5n0SxLyHWc1-jfRZj0UyCQ2ma-I1QRx2zxeGnkm7lXmc0ofNkYSYk9uiDGYjL--Q54Jh_0uOoizY-l5kQlUYvmPOMB5gylkHgzFjeu1pGFZp08giCX8jeNyRsfXJosg3RaVqtLdlQzGQr13DlP4XuMA0zZ4BwfJZpJ9RmH9PgCyrsaLdAItWmq3UrP1girfc746ft3K9-LOfIxuvCM5dVZlj4bBth9voEj7cb6oESHnDxaLA6DZEzVtFWf5OJcjgNKK7xNps4exQ0JRilSXTg25mbSQBK33WDoSkhuHu7euWHMOcHTZo7Vml4c1oIPmfNOxOgT84fKCGMLxiyetCwr1-kRRvidqoDM8LNmW1V3DOFq1pvCW4Op2_Q8jmYMiuq96mvvSFx4oB4RMWD2WqXwioEAa2AhjIs9ObTT0vX1QsR5HY572BT6VEQcPwpBk1OJh3MVA9v0sH85qnwpzXSc9vEfBzyCrE700Mije6lx_DCEBc8PzPo0urh8vWJO0-g7EjpV-4eTW674vN9lj9gTBaNcdSs2WqGY2BC2fzKCFRUN-Oi8oVtdr8nObliDqfjdTe67vvq__7es4ZiMXSDjOt_Lc7-tlqJfGvgZS4ZGOlqQh91vfzfz2no3vH-fjI3WrjjN9pxlmXDuXt9ZJM-_apeu2r6TgIa85Xg2BNdV0PC0sdPrd8x0kNpZ_70O-BbCttagcB55_x1Xen7l1I7fp26fs3e9TgG4iLf6MaXghy874zu3T9YKThPtg6QRu1_PPqR79Wxi46H0Oj5UeLZb0IFClFLr1Wxxu9m9bEbjeAA6p5By29OvCz-Npzaj4J3jwnQdiOpq8335UqcLSHm7SEXkKa4Kefl0WLDQSdX1xI14xFRSFGmDtYVXiW7-KHpqoyxVWYWxWIi2yiJMtO-gj7AXBZBsUz_bSq8rutygbm_a561xnJDiA0nGJ95CmYIig6oE4Y8\",\"webrequest\":true}", headers);
		JSONObject answer = restTemplate.postForObject(createPersonUrl, request, JSONObject.class);

		return answer.getAsString("access_token");

	}
	public static void main(String[] args) {
		CourseraSupport v = new CourseraSupport();
		v.getToken();
	}
}
