package Day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Iterator;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class parsingJSONRespnsedata {

	 @Test
	void JSONResponse() {
		// Approach1

//		given()  .contentType("contentType.JSON") .contentType("application/json")
//				// both methods are working as expected
//
//				.when().get("http://localhost:3000/store")
//
//				.then().statusCode(200).header("Content-Type", "application/json; charset=utf-8").log().all()
//				.body("book[3].title", equalTo("The Lord of The Rings"));

		// Approach2
		Response res = given()

				.contentType("application/json") // both methods are working as expected

				.when().get("http://localhost:3000/store");

		// status code validation
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("status code from response is : " + statusCode);

		// validating the title from body by navigating thru jsonpath
		String bookname = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(res.jsonPath().get("book[3].title").toString(), "The Lord of The Rings");
		System.out.println(bookname);

	}

	@Test
	void JSONResponseBodydata() {

		// Approach3
		Response res = given()

				// .contentType("application/json") //both methods are working as expected
				.contentType(ContentType.JSON) // this is the method we should use when we pass in given section

				.when().get("http://localhost:3000/store");

		// to get all book title

		JSONObject jo = new JSONObject(res.asString());

		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(booktitle);
		}

		System.out.println("*************************************");
		boolean status = false;

		// to assert whether book title is avail from the list
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();

			if (booktitle.equals("The Lord of The Rings")) {
				status = true;
				break;
			}

		}
		Assert.assertEquals(status, true);

		System.out.println("*************************************");

		// Summing all price and assrting with total price
		double totalprice = 0;
		double booktotalprice = 526.0;

		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();

			totalprice = totalprice + Double.parseDouble(price);

		}
		Assert.assertEquals(booktotalprice, totalprice);
	}

}
