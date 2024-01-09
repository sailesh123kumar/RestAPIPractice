package Day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Day2.POJOclass;

public class SerialiZationAndDeserialization {
	
	@Test
	void pojotojson() throws JsonProcessingException {
		
//Created java object using pojo class
		POJOclass data = new POJOclass();

		data.setFirstName("Deepan123");
		data.setLastName("Balaji123");
		data.setPhoneNumber("12233344445555123");
		data.setEmailAddress("Deepan123@gmail.com");
		String coursesArr[] = {"Rest API1","SOAP API1"};
		data.setCourses(coursesArr);
		
		//convert java object to json object (serialization)
		
		ObjectMapper obj=new ObjectMapper();
		
		String jsondata = obj.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		System.out.println(jsondata);
	}
	
	@Test
	void jsontopojo() throws JsonProcessingException {
		
		

				String data="{\r\n"
						+ "  \"firstName\" : \"Deepan123\",\r\n"
						+ "  \"lastName\" : \"Balaji123\",\r\n"
						+ "  \"phoneNumber\" : \"12233344445555123\",\r\n"
						+ "  \"emailAddress\" : \"Deepan123@gmail.com\",\r\n"
						+ "  \"courses\" : [ \"Rest API1\", \"SOAP API1\" ]\r\n"
						+ "}";
				
				//convert json object to java (De-serialization)
				
				ObjectMapper obj=new ObjectMapper();
				POJOclass readValue = obj.readValue(data, POJOclass.class); //convert json to pojo
				
				
				System.out.println("FName :"+readValue.getFirstName());
				System.out.println("LName :"+readValue.getLastName());
				System.out.println("Email :"+readValue.getEmailAddress());
				System.out.println("Num :"+readValue.getPhoneNumber());
				System.out.println("course1 :"+readValue.getCourses()[0]);
				System.out.println("course2 :"+readValue.getCourses()[1]);
				
			}
	

}
