package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs684.photoAlbum.model.Backend;

/**
 * 
 * @author Haneef
 * UseCase ID : MODEL_BACKEND_01
 * TestCase ID : TEST_MODEL_BACKEND_ADDUSER_01
 */
public class AddUserTest {

	@Test
	public void testAddUser() {
		System.out.println("Inside testAddUser");
		Backend backend = new Backend();
		boolean result = backend.addUser("01", "Avinash");
		assertEquals(true, result);
		
	}

}
