package Day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Logdemo {
	
	
	@Test
	void logmethod() {
		
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.log().cookies()
		
		.log().headers()
		
		.log().body()
		
		.log().status()
		
		.log().all();
		
	}

}
