package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cs684.photoAlbum.control.Control;
import cs684.photoAlbum.model.Backend;
import cs684.photoAlbum.model.BackendInterface;
import cs684.photoAlbum.model.Photo;
import cs684.photoAlbum.model.User;

public class GetPhotoByTagTest {

	@Test
	public void test() {
		ArrayList<Photo> p= new ArrayList<Photo>();
		BackendInterface b = new Backend();
		b.addUser("praveenk0705", "Praveen Kumar");
		User u = b.getUser("praveenk0705");
		System.out.println(u.getFullName());
		String name = u.getUsername();
		System.out.println(name);
		
		Control c= new Control(b);
		boolean login = c.logIn(name);
		System.out.println(login);
		
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("t4:t4");
		
		p = c.getPhotosByTag(al);
		System.out.println(p);
		
	}

}
