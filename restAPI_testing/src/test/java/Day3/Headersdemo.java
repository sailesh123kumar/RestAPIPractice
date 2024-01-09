package Day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Headersdemo {
	
	
	//@Test
	void testHeader() {

		given()

				.when().get("https://www.google.com/")

				.then()
				.header("Content-Type", "text/html; charset=ISO-8859-1")
				.and()  //optional not mandatory to use
				.header("Content-Encoding", "gzip")
				.and()
				.header("Server", "gws");
	}

	@Test
	void testHeadersinfo() {

		Response res=given()

				.when().get("https://www.google.com/");
		
		//to get value of single header
		String header_Value = res.getHeader("Content-Type");
		
		System.out.println("the header value is "+header_Value);
		System.out.println("*************************************");
		
		//to get the key and value of all header
		Headers header_Value1 = res.getHeaders();
		
		for (Header header : header_Value1) {
			System.out.println(header.getName()+"          "+header.getValue());
		}

		System.out.println("*************************************");		
	}

}
