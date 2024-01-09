package Day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Authentication {

	//@Test
	public void BasicAuth() {
	
	given()
	.auth().basic("postman", "password")
	
	.when().get("https://postman-echo.com/basic-auth")
	
	.then()
	.statusCode(200)
	.body("authenticated",equalTo(true))
	.log().all();
	}
	
	
	//@Test
	public void DigestAuth() {
	
	given()
	.auth().digest("postman", "password")
		
	.when().get("https://postman-echo.com/basic-auth")
	
	.then()
	.statusCode(200)
	.body("authenticated",equalTo(true))
	.log().all();
	
	}
	
	@Test
	public void PreemptiveAuth() {
	
	given()
	.auth().preemptive().basic("postman", "password")
		
	.when().get("https://postman-echo.com/basic-auth")
	
	.then()
	.statusCode(200)
	.body("authenticated",equalTo(true))
	.log().all();
	
	}

}
