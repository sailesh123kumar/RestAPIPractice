package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Parsexml {

	//@Test
	void parsexmldata() {

		// approach 1

		/*
		 * given() .contentType(ContentType.XML)
		 * 
		 * .when() .get("http://restapi.adequateshop.com/api/Traveler?page=1") .then()
		 * .statusCode(200) .header("content-Type", "application/xml; charset=utf-8")
		 * .body("TravelerinformationResponse.page", equalTo("1"))
		 * .body("TravelerinformationResponse.travelers.Travelerinformation.name[0]",
		 * equalTo("Developer"));
		 * 
		 */

		// approach 2
		Response res = given().contentType(ContentType.XML)

				.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("content-Type"), "application/xml; charset=utf-8");

		String traveller_name = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation.name[0]")
				.toString();
		Assert.assertEquals(traveller_name, "Developer");
	}

	
	@Test
	void parsexmldatabody() {

		// approach 3
		Response res = given().contentType(ContentType.XML)

				.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		XmlPath xml=new XmlPath(res.asString());
		
		List<String> list = xml.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(list.size(), 10);
		
		List<String> travellers_name = xml.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean presenceofname=false;
		for (String traveller_name : travellers_name) {
			
			System.out.println(traveller_name);
			
			if(traveller_name.equals("karen")) {
				presenceofname=true;
				break;
			}
			
		}
		Assert.assertEquals(presenceofname, true);
	}

}
