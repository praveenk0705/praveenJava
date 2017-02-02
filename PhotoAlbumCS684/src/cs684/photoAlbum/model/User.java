package cs684.photoAlbum.model;

import java.io.Serializable;
import java.util.Hashtable;


 

/**
 * 
 *  @author Praveen
 * ID : MODEL_USER_01
 */

 
// this is the user class which has all the albums and all the photos in those albums

public class User implements Serializable {

	private static final long serialVersionUID = -3181581315631605580L;

	private String username;

	private String fullName;

	private Hashtable<String, Album> albums;

	private Hashtable<String, Photo> photos;

	public User(String username, String fullname) {
		this.username = username;
		this.fullName = fullname;
		albums = new Hashtable<String, Album>();
		photos = new Hashtable<String, Photo>();
	}

	public Album getAlbum(String albumName) {
		if (this.albums.get(albumName) != null) {
			return this.albums.get(albumName);
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Hashtable<String, Album> getAlbums() {
		return albums;
	}

	public Hashtable<String, Photo> getPhotos() {
		if (photos == null) {
			return null;
		}
		return photos;
	}

	public void setAlbums(Hashtable<String, Album> albums) {
		this.albums = albums;
	}

	public boolean findPhotoInUser(String fileName) {
		if (photos.get(fileName) != null) {
			return true;
		}
		return false;
	}

	public Photo getPhoto(String fileName) {
		if (photos.get(fileName) != null) {
			return photos.get(fileName);
		}
		return null;
	}

	public boolean removePhoto(String fileName) {
		if (photos.get(fileName) != null) {
			photos.remove(fileName);
			return true;
		}
		return false;
	}

	public boolean addAlbum(String albumName) {
		if (this.albums.get(albumName) != null) {
			return false;
		}
		Album newAlbum = new Album(albumName);
		albums.put(albumName, newAlbum);
		return true;
	}

	public boolean deleteAlbum(String albumName) {
		if (albums.get(albumName) != null) {
			albums.remove(albumName);
			return true;
		}
		return false;
	}

	public boolean renameAlbum(String newName, String oldName) {
		if (albums.get(oldName) != null) {
			albums.get(oldName).setAlbumName(newName);
			return true;
		}
		return false;
	}
}
