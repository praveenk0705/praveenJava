package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs684.photoAlbum.model.Backend;
/**
 * 
 * @author Haneef
 *UseCase ID : MODEL_BACKEND_01
 * TestCase ID : TEST_MODEL_BACKEND_DELETEUSER_01
 */
public class DeleteUserTest {

	@Test
	public void testDeleteUser() {
		System.out.println("Inside testDeleteUser");
		Backend backend = new Backend();
		boolean result = backend.deleteUser("Avinash");
		assertEquals(false, result);
	}

}
