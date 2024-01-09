package Day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class HTTPrequest {
	int id;

	@Test(priority = 1)
	void getUser() {

		// Url with end point
		// GetRequest
		// https://reqres.in/api/users?page=2

		when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2)
	void CeateUser() {

		// postRequest
		// https://reqres.in/api/users
		// body
//		{
//		    "name": "morpheus",
//		    "job": "leader"
//		}

		HashMap data = new HashMap();
		data.put("name", "sailesh kumr");
		data.put("job", "Tester");

		id = given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
		System.out.println("the Id is : "+id);

//		.then()
//		.statusCode(201);
//		.log().all();
	}

	@Test(priority = 3, dependsOnMethods = { "CeateUser" })
	void Updateuser() {

		// putRequest
		// https://reqres.in/api/users/2
// body
//			{
//			    "name": "morpheus",
//			    "job": "zion resident"
//			}
//	}
		HashMap data = new HashMap();
		data.put("name", "sailesh kumar");
		data.put("job", "SoftwareTester");

		given().contentType("application/json").body(data)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200);//.log().all();

	}

	@Test(priority = 4)
	void DeleteUser() {

		// DeleteRequest
		// https://reqres.in/api/users/2
		// 204 response code

		given()

				.when().delete("https://reqres.in/api/users/" + id)

				.then().statusCode(204);//.log().all();
	}
}
