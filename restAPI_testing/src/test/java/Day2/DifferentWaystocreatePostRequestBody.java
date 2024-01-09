package Day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DifferentWaystocreatePostRequestBody {

	/*
	 * Post request with differnt ways 1.Hashmap 2.Using org.json POJO class(plain
	 * old java object) Using external json file
	 */

	// 1.Hashmap
	// @Test(priority=1)
	void testPostusingHashmap() {

		HashMap data = new HashMap();
		data.put("firstName", "Deepan");
		data.put("lastName", "Balaji");
		data.put("phoneNumber", "12233344445555");
		data.put("emailAddress", "Deepan@gmail.com");
		String coursesArr[] = { "RestAPI", "SOAP API" };
		data.put("courses", coursesArr);

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/users")

				.then().statusCode(201).body("firstName", equalTo("Deepan")).body("lastName", equalTo("Balaji"))
				.body("phoneNumber", equalTo("12233344445555")).body("emailAddress", equalTo("Deepan@gmail.com"))
				.body("courses[0]", equalTo("RestAPI")).body("courses[1]", equalTo("SOAP API")).log().all();
	}

	// 2.Json.org
	// @Test(priority=1)
	void testPostusingJsonorg() {

		JSONObject data = new JSONObject();

		data.put("firstName", "Deepan1");
		data.put("lastName", "Balaji1");
		data.put("phoneNumber", "122333444455551");
		data.put("emailAddress", "Deepan1@gmail.com");
		String coursesArr[] = { "RestAPI1", "SOAP API1" };
		data.put("courses", coursesArr);

		given().contentType("application/json").body(data.toString())

				.when().post("http://localhost:3000/users")

				.then().statusCode(201).body("firstname", equalTo("Deepan1")).body("lastname", equalTo("Balaji1"))
				.body("phoneNumber", equalTo("122333444455551")).body("emailAddress", equalTo("Deepan1@gmail.com"))
				.body("courses[0]", equalTo("RestAPI1")).body("courses[1]", equalTo("SOAP API1")).log().all();
	}

	// 3.POJO class
	//@Test(priority = 1)
	void testPostusingPOJO() {

		POJOclass data = new POJOclass();

		data.setFirstName("Deepan123");
		data.setLastName("Balaji123");
		data.setPhoneNumber("12233344445555123");
		data.setEmailAddress("Deepan123@gmail.com");
		String coursesArr[] = {"Rest API1","SOAP API1"};
		data.setCourses(coursesArr);

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/users")

				.then().statusCode(201).body("firstName", equalTo("Deepan123")).body("lastName", equalTo("Balaji123"))
				.body("phoneNumber", equalTo("12233344445555123")).body("emailAddress", equalTo("Deepan123@gmail.com"))
				.body("courses[0]", equalTo("Rest API1")).body("courses[1]", equalTo("SOAP API1")).log().all();
	}

	
	// 4.JSON File
		@Test(priority = 1)
		void testPostusingjsonfile() throws FileNotFoundException {

			File file=new File("E:\\restAPI_testing\\target\\jsonfiles\\postbody.json");
		//	File file=new File(".\\body.json");   //not working need to revisit
			FileReader fr=new FileReader(file);
			JSONTokener jt=new JSONTokener(fr);
			JSONObject data=new JSONObject(jt);
			

			given().contentType("application/json").body(data.toString())

					.when().post("http://localhost:3000/users")

					.then().statusCode(201).log().all();
		}
		
	@Test(priority = 2)
	void DeleteUser() {
		given().when().delete("http://localhost:3000/users/6").then().statusCode(200);
		
	}

}
