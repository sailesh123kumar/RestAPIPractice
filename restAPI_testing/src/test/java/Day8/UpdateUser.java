package Day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class UpdateUser {

	@Test
	void TestUpdateUser(ITestContext context) {

		Faker fake = new Faker();

		JSONObject data = new JSONObject();

		data.put("name", fake.name().fullName());
		data.put("gender", "female");
		data.put("email", fake.internet().emailAddress());
		data.put("status", "active");

		String bearertoken = "37eefa40fc7c87cd8f712f2127620e01f1322b020d354880f529650eeaff77fe";

		//int id = (Integer) context.getAttribute("id");
		int id = (Integer) context.getSuite().getAttribute("id");
		
		given().headers("Authorization", "Bearer " + bearertoken).contentType(ContentType.JSON).body(data.toString())
				.pathParam("id", id)

				.when().put("https://gorest.co.in/public/v2/users/{id}")

				.then().statusCode(200).log().all();

		
	}

}
