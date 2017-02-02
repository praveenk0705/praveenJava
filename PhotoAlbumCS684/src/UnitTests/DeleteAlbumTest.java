package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.User;
/**
 * 
 * @author Praveen Kumar
 *UseCase ID : MODEL_USER_01
 * TestCase ID : TEST_MODEL_USER_DELETEALBUM_01
 */
public class DeleteAlbumTest {

	@Test
	public void test() {
		System.out.println("Inside DeleteAlbumTest");
		User user = new User("praveenk0705" , "Praveen Kumar");
		user.addAlbum("ACDC");
		
		Album al = user.getAlbum("ACDC");
		
		String albumName = al.getAlbumName();
		boolean result = user.deleteAlbum(albumName);
		assertTrue("Album does not exists", result);
	}

}
