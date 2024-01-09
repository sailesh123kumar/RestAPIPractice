package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Cookiesdemo {

	 @Test
	void testCookie() {

		given()

				.when().get("https://www.google.com/")

				.then()
			//	.cookie("AEC", "abcsd") // Test will fail bcz every time new value will be generating
				.log().all();

	}

	@Test
	void getCookiesinfo() {

		Response res = given()

				.when().get("https://www.google.com/"); // return entire response body,cookies,headers,statuscode
		
		
		
		System.out.println("To get Single cookie value");
		
		//To get single cookie
		String AECcookie = res.getCookie("AEC");
		System.out.println("value of cookie is " + AECcookie);
		
		System.out.println("***********************************");
		
		System.out.println("To get all cookies key");
		
		//To get al cookies
		Map<String, String> Allcookies = res.getCookies();
		
		System.out.println("Key set "+Allcookies.keySet());
		
		System.out.println("***********************************");
		
		System.out.println("To get all cookies key and value");
		
		for (String k :Allcookies.keySet() ) {
			
			String cookievalue = res.getCookie(k);
			System.out.println(k+"        "+cookievalue);
			
		}
	}

}
