package com.buinam.youtubespringrestemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class YoutubeSpringRestemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeSpringRestemplateApplication.class, args);
	}

}
@SpringBootTest
class FetchApi {
	String getUrl = "https://dog.ceo/api/breeds/image/random";
	String postUrl = "https://postman-echo.com/post";
	String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJidWluYW0iLCJyb2xlcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODg4OC9hcGkvdjEvc2VjdXJpdHkvbG9naW4iLCJleHAiOjE2NTc5Mjc3ODN9.i2hFeLWEBY3YwsB1llREmZXRN53YPtIGFqPUQ4oLw6Q";

	@Test
	public void getRequest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);

			HttpEntity<String> entity = new HttpEntity<>("", headers);
			Object res = restTemplate.exchange(getUrl, HttpMethod.GET, entity, Object.class);
			System.out.println(res);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void postRequest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			headers.setContentType(MediaType.APPLICATION_JSON);

			Person body = new Person("Casey", 10);

			String json = new ObjectMapper().writeValueAsString(body);
			HttpEntity<String> entity = new HttpEntity<>(json, headers);

			Object res = restTemplate.exchange(postUrl, HttpMethod.POST, entity, Object.class);
			System.out.println(res);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
class Person {
	String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

