package Day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class GetUser {

	@Test
	void TesGetUser(ITestContext context) {

		//int id = (Integer) context.getAttribute("id");
		int id = (Integer) context.getSuite().getAttribute("id");
		String bearertoken = "37eefa40fc7c87cd8f712f2127620e01f1322b020d354880f529650eeaff77fe";

		given().headers("Authorization", "Bearer " + bearertoken).pathParam("id", id)

				.when().get("https://gorest.co.in/public/v2/users/{id}").then().statusCode(200).log().all();

	}

}
