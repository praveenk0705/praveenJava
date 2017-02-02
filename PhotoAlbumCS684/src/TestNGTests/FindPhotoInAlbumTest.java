package TestNGTests;

import org.testng.annotations.Test;

import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.User;
import junit.framework.Assert;

public class FindPhotoInAlbumTest {
  @Test
  public void f() {
	  
User user = new User ("pku" , "Praveen");
	  
	  String fileName = "dataPersistance.jpg";
	  String caption = "caption";
	  Album album = new Album("MyAlbum");
	Boolean b=  album.addPhoto(fileName, caption, user);
	Boolean value = false;
	System.out.println(user.findPhotoInUser(fileName));
	//we need to test if the function returns the photo name we just stored
	//if the return value is not null, the test passed
	
	if (user.findPhotoInUser(fileName))
		value = true;
	else
		value = false;
	System.out.println(value);
	Assert.assertTrue("Returns the album that was added", value);
  }
}
