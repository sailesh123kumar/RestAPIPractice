package Day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathparamAndQueryParam {

	@Test
	void pathandqueryParameter() {
		
		given()
		.pathParam("path", "users")
		.queryParam("page",2)
		.queryParam("id",5)
		
		.when()
		.get("https://reqres.in/api/{path}")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
}
