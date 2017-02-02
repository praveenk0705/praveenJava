package TestNGTests;

import org.testng.annotations.Test;

import cs684.photoAlbum.model.Backend;
import cs684.photoAlbum.model.User;

import org.testng.annotations.BeforeTest;
import org.junit.Assert;
import org.testng.annotations.AfterTest;

public class GetUserTest {
  @Test
  public void f() {
	  boolean value;
	  Backend b= new Backend();
	  User u = b.getUser("Avinash");
	  System.out.println(u);
	  
	  
	  
	  
  }
  @BeforeTest
  public void beforeTest() {
	  Backend backend = new Backend();
		boolean result = backend.addUser("01", "Avinash");
		System.out.println(result);
  }

  @AfterTest
  public void afterTest() {
  }

}
