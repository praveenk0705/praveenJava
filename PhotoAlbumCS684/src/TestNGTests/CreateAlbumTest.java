package TestNGTests;

import org.testng.annotations.Test;

import cs684.photoAlbum.model.User;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class CreateAlbumTest {
  @Test
  public void f() {
	  User user = new User("Praveen", "PKU");
	  String albumName = "My Album" ;
	 boolean value= user.addAlbum(albumName);
	 Assert.assertTrue(value,"The album with albumName has been added");
	  
  }
  @BeforeTest
  
  public void beforeTest() {
	 
	  
  }

}
