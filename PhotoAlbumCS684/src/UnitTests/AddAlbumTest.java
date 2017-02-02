package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.User;

/**
 * 
 * @author Praveen Kumar
 * UseCase ID : MODEL_USER_01
 * TestCase ID : TEST_MODEL_USER_ADDALBUM_01
 */
public class AddAlbumTest {

	@Test
	public void test() {
		
		System.out.println("Inside AddAlbumTest");
		User user = new User("praveenk0705" , "Praveen Kumar");
		boolean result = user.addAlbum("ACDC");
		
		Album al = user.getAlbum("ACDC");
		
		System.out.println(al.getAlbumName());
		
		assertTrue("Album already Exists",result) ;
	}

}
