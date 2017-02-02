package UnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cs684.photoAlbum.control.Control;
import cs684.photoAlbum.model.Backend;
import cs684.photoAlbum.model.BackendInterface;
import cs684.photoAlbum.model.User;

/**
 * 
 * @author Praveen Kumar
 *UseCase ID : MODEL_BACKEND_01
 * TestCase ID : TEST_MODEL_BACKEND_LOGIN_01
 */
public class LogInTest {

	BackendInterface bi = new Backend();
	
	@Before
    public void initObjects() {
		bi.addUser("praveenk0705", "Praveen Kumar");
    }
	@Test
	public void testUserLogIn() {
		
		System.out.println("Inside testUserLogIn");
		System.out.println(bi.getUser("praveenk0705"));
		User u = bi.getUser("praveenk0705");
		System.out.println(u.getFullName());
		String name = u.getUsername();
		
		Control control = new Control(bi);
		
		boolean result = control.logIn(name);
		System.out.println(result);
		assertEquals(true, result);
		assertTrue("The User Does Not Exists", result);
		
		
	}

}
