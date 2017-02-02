package TestNGTests;

/**
 * Author Praveen
 */
import org.testng.annotations.Test;

import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.Photo;
import cs684.photoAlbum.model.User;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AddPhotoTest {
	 @BeforeTest
	  public void beforeTest() {
		  
	  }

  @Test
  public void f() {
	  
	  User user = new User ("pku" , "Praveen");
	  
	  String fileName = "dataPersistance.jpg";
	  String caption = "caption";
	  Album album = new Album("MyAlbum");
	Boolean b=  album.addPhoto(fileName, caption, user);
	Assert.assertTrue(b, "The photo has been successfully added");
	//Assert.assertFalse(b,"The function returned with an error");
	
	System.out.println(b);
	
	  
  }
 
  @AfterTest
  public void afterTest() {
  }

}
