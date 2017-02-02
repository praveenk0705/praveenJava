package TestNGTests;
// Praveen
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import cs684.photoAlbum.control.Control;
import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.Backend;
import cs684.photoAlbum.model.User;

public class RemovePhotoTest {
	
	@BeforeTest
	public void BeforeTest() {
		Backend backend = new Backend();
		  Control c = new Control(backend);
		  User user = new User ("pku" , "Praveen");
		  
		  String fileName = "dataPersistance.jpg";
		  String caption = "caption";
		  Album album = new Album("MyAlbum");
		 String aName = album.getAlbumName();
		 System.out.println("the album name is "+aName);
		  Boolean b=  album.addPhoto(fileName, caption, user);
	}
  @Test
  public void f() {
	  Backend backend = new Backend();
	  Control c = new Control(backend);
	  
	 Boolean cc= c.removePhoto("dataPersistance.jpg", "MyAlbum");
	  System.out.println(cc);
  }
}
