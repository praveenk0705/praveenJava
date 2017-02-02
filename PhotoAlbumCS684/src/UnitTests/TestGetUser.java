package UnitTests;



import java.util.Hashtable;

import cs684.photoAlbum.model.Backend;
import cs684.photoAlbum.model.User;
/**
 * 
 * @author Haneef
 *
 */
public class TestGetUser {
	public static void main(String []args)
	{
		
		System.out.println("Hello");
		Backend backend = new Backend();
		backend.addUser("2", "Myname");
		backend.addUser("3", "b");
		Hashtable<String, User> result = backend.getUsers();
		System.out.println(result);
		
		
		User user = backend.getUser("2");
		System.out.println(user);
		
	}

}
