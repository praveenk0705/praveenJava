package cs684.photoAlbum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * 
 * @author Praveen AND Haneef
 * ID : MODEL_BACKEND_01
 */

// this backend class is used for storing data and photos in the data folder


public class Backend implements Serializable, BackendInterface {
	
	
	private static final long serialVersionUID = 1555081347938717291L;
	private static final String dirName = "data";
	private static final String fileName = "users.dat"; 
		
	
	private Hashtable<String,User> users;

	
	public Backend(){
		users = new Hashtable<String,User>();
	}
	
	
	public Backend readUser() throws FileNotFoundException, IOException, ClassNotFoundException{
		try{
		ObjectInputStream ois = 
			new ObjectInputStream(new FileInputStream(dirName + File.separator + fileName));
		return (Backend)ois.readObject();
		}
		catch(Exception e){
			Backend backend = new Backend();
			return backend;
		}
		
	}

	
	public boolean writeUser(BackendInterface backend) 
	throws IOException {
		
		ObjectOutputStream oos = 
			new ObjectOutputStream(new FileOutputStream(dirName + File.separator + fileName));
		oos.writeObject(backend);
		return true;
		
	}
	
	
	public boolean addUser(String id, String fullName){
		if(this.users.get(id)==null){
			User user = new User(id,fullName);
			this.users.put(id,user);
			return true;
		}
		return false;
	}
	
	
	public boolean deleteUser(String username){
		
		if(this.users.get(username)!=null){
			this.users.remove(username);
			return true;
		}
		
		return false;
	}
	
	
	public Hashtable<String,User> getUsers(){		
		return this.users;		
	}
	
	
	public User getUser(String username){
		
		if(this.users.get(username)!=null){
			return this.users.get(username);
		}
		
		return null;		
	}	
}
