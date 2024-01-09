package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUser {

	
	@Test
	void TestCreateUser(ITestContext context) {
		
		Faker fake=new Faker();
		
		JSONObject data=new JSONObject();
		
		data.put("name", fake.name().fullName());
		data.put("gender", "male");
		data.put("email", fake.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearertoken="37eefa40fc7c87cd8f712f2127620e01f1322b020d354880f529650eeaff77fe";
		
		
		 int id = given()
		.headers("Authorization","Bearer "+bearertoken)
		.contentType(ContentType.JSON)
		.body(data.toString())
		
		.when()
		.post("https://gorest.co.in/public/v2/users")
		.jsonPath().getInt("id");
		
		System.out.println("Generated Id is: "+id);
		//context.setAttribute("id", id);
		context.getSuite().setAttribute("id", id);
	}
	
	
	
	
	
	
	
}
