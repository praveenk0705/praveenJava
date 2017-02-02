package cs684.photoAlbum.control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;

import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.Photo;
import cs684.photoAlbum.model.User;

/**
 * Interface implemented by Control to establish communication between control and view for processing purposes. 
 */




public interface ControlInterface {
		
	boolean createAlbum(String albumName);
	boolean deleteAlbum(String albumName);
	Hashtable<String,Album> listAlbums();
	//ArrayList<Album> listAlbums();
	
	//ArrayList<Photo> listPhotos();
	Hashtable<String,Photo> listPhotos();
	Hashtable<String,Photo> listPhotos(String albumName);
	Photo addPhoto(String fileName, String caption, String albumName) ;
	int movePhoto(String fileName, String oldAlbum, String newAlbum);
	boolean removePhoto(String fileName, String albumName);
	boolean addTag(String fileName, String tagType , String tagValue);
	boolean deleteTag(String fileName, String tagType , String tagValue);
	Photo listPhotoInfo(String fileName);
	ArrayList<Photo> getPhotosByDate(String start, String end) throws ParseException ;
	ArrayList<Photo> getPhotosByTag(ArrayList<String> tags);
	User getUser();
	boolean logIn(String username);
	boolean addUser(String username, String fullName);
	boolean deleteUser(String username);
	Hashtable<String,User> getUsers();
	
}
