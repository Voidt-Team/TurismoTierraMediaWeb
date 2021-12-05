package ttm.utils;
import org.junit.Assert;
import org.junit.Test;

public class CryptTests {
	String password = "Sergio";
	String passwordIncorrecto = "Sergi0";

	@Test
	public void testHash() {
		Object hashed = Crypt.hash(password);
		
		System.out.println(hashed.toString());
		Assert.assertFalse(hashed.equals(password));
		
	}

	@Test
	public void testMatch() {
		Assert.assertTrue(Crypt.match(password, Crypt.hash(password)));
		Assert.assertFalse(Crypt.match(passwordIncorrecto, Crypt.hash(password)));
	}

}
