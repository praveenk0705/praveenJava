package TestNGTests;

import org.junit.Assert;
import org.testng.annotations.Test;

import cs684.photoAlbum.model.User;

public class GetAlbumsTest {
  @Test
  public void f() {
	  User user = new User("Praveen", "PKU");
	  String albumName = "My Album" ;
	 boolean value= user.addAlbum(albumName);
	 System.out.println(user.getAlbums());
	 if(user.getAlbums()!= null){
		 value= true;
	 }
	 else
		 value =false;
	 System.out.println("the value is == " + value);
	 Assert.assertTrue("Returns the album that was added", value);
  }
}
