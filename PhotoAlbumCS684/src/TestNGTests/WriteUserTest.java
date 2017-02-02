package TestNGTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import cs684.photoAlbum.model.Backend;
import cs684.photoAlbum.model.BackendInterface;
import cs684.photoAlbum.model.User;

public class WriteUserTest {
  @Test
  public void f() throws IOException {
	  //This test can be considered as an integration test as it involves direct file IO
	  String user = "Praveen";
	  String UID = "pku";
	  User u = new User(UID, user);
	  BackendInterface bi= new Backend();
	 boolean value= bi.writeUser(bi);
	  Assert.assertTrue(value, "The user has been written onto the disk");
  }
}
