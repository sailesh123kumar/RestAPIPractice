package Day8;

import static io.restassured.RestAssured.given;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void TesDeleteUser(ITestContext context) {

		//int id = (Integer) context.getAttribute("id");
		int id = (Integer) context.getSuite().getAttribute("id");
		
		String bearertoken = "37eefa40fc7c87cd8f712f2127620e01f1322b020d354880f529650eeaff77fe";

		given().headers("Authorization", "Bearer " + bearertoken).pathParam("id", id)

				.when().delete("https://gorest.co.in/public/v2/users/{id}").then().statusCode(204).log().all();

	}

}
