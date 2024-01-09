package Day7;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test

	void TestDatagenerator() {
		Faker fake = new Faker();
		String fullName = fake.name().fullName();
		String firstName = fake.name().firstName();
		String lastName = fake.name().lastName();
		String username = fake.name().username();
		String password = fake.internet().password();
		String cellPhone = fake.phoneNumber().cellPhone();
		String emailaddress = fake.internet().emailAddress();

		System.out.println("Full Name :" + fullName);
		System.out.println("firstName :" + firstName);
		System.out.println("lastName :" + lastName);
		System.out.println("username :" + username);
		System.out.println("password :" + password);
		System.out.println("cellPhone :" + cellPhone);
		System.out.println("emailaddress :" + emailaddress);
	}

}
