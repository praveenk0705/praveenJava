package cs684.photoAlbum.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;

import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.BackendInterface;
import cs684.photoAlbum.model.Photo;
import cs684.photoAlbum.model.User;

/** 
 * @author Haneef and Praveen
 * 
 * 
 */


//This class implements the algorithmic logic of the program, decision making
// and data manipulation processes. It communicates with Model through
// BackendInterface to retrieve a specific user and manipulate it as needed. 

public class Control implements ControlInterface {

	BackendInterface backend;

	public User user;

	public Control(BackendInterface backend) {
		this.backend = backend;

	}

	public BackendInterface getBackend() {
		return backend;
	}

	public void setBackend(BackendInterface backend) {
		this.backend = backend;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean createAlbum(String albumName) {
		return this.user.addAlbum(albumName);
	}

	public boolean deleteAlbum(String albumName) {
		return this.user.deleteAlbum(albumName);
	}

	// public Hashtable<String,Album> listAlbums(){
	// return user.getAlbums();
	// }

	public Hashtable<String, Album> listAlbums() {

		return user.getAlbums();
	}

	public Hashtable<String, Photo> listPhotos(String albumName) {

		Hashtable<String, Photo> photosInAlbum = new Hashtable<String, Photo>();

		if (user.getAlbum(albumName) != null) {

			photosInAlbum = user.getAlbum(albumName).getPhotosInAlbum(user);
			return photosInAlbum;
		}

		return null;
	}

	public Hashtable<String, Photo> listPhotos() {
		return user.getPhotos();
	}

	public Photo addPhoto(String fileName, String caption, String albumName) {

		if (user.getAlbum(albumName).findPhotoInAlbum(fileName) == false) { // if
																			// does
																			// not
																			// exist
																			// in
																			// the
																			// album
																			// add
																			// it
			Album album = user.getAlbum(albumName);
			album.addPhoto(fileName, caption, user);
			return user.getPhoto(fileName);
		}

		return null;
	}

	public int movePhoto(String fileName, String oldAlbum, String newAlbum) {

		if (user.getAlbum(newAlbum).findPhotoInAlbum(fileName) == true) { // If
																			// file
																			// is
																			// already
																			// in
																			// new
																			// album
			return -1;
		}
		if (user.getAlbum(oldAlbum).findPhotoInAlbum(fileName) == false) { // If
																			// you
																			// didn't
																			// find
																			// the
																			// picture
																			// in
																			// the
																			// album
			return 0;
		}

		// If non of the previous, proceed to move the picture.
		// Delete 'pointer' from oldAlbum, add 'pointer' to new album
		user.getAlbum(oldAlbum).removePhotoInAlbum(fileName);
		user.getAlbum(newAlbum).addPhotoInAlbum(fileName);

		return (int) 1;
	}

	public boolean removePhoto(String fileName, String albumName) {
		/*
		 * If the photo to be deleted only exists in one album, then delete it's
		 * instance also, else just delete it's pointer in the album
		 */

		if (user.getAlbum(albumName).getPhotoInAlbum(fileName, user) != null) {

			if (user.getPhoto(fileName).numberOfAlbumsBelongingTo() > 1) { // belongs
																			// to
																			// more
																			// than
																			// one
																			// album,
																			// just
																			// delete
																			// its
																			// pointer
																			// at
																			// album.
				user.getAlbum(albumName).removePhotoInAlbum(fileName);
				return true;
			} else {
				user.getAlbum(albumName).removePhotoInAlbum(fileName); // Remove
																		// pointer
																		// in
																		// album.
				user.removePhoto(fileName); // Remove photo instance
											// permanently.
				return true;
			}
		}
		return false;
	}

	public boolean addTag(String fileName, String tagType, String tagValue) {

		if (user.getPhoto(fileName) != null) {
			return user.getPhoto(fileName).addTag(tagType, tagValue);
		}
		return false;
	}

	public boolean deleteTag(String fileName, String tagType, String tagValue) {

		if (user.getPhoto(fileName) != null) {
			return user.getPhoto(fileName).removeTag(tagType, tagValue);

		}
		return false;
	}

	public Photo listPhotoInfo(String fileName) {
		return user.getPhoto(fileName);
	}

	public ArrayList<Photo> getPhotosByDate(String start, String end)
			throws java.text.ParseException {

		ArrayList<Photo> photos = new ArrayList<Photo>();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-H:m:s");
		Date date = (Date) formatter.parse(start);
		Calendar startDate = Calendar.getInstance();
		startDate.set(Calendar.MILLISECOND, 0);
		startDate.setTime(date);
		date = (Date) formatter.parse(end);
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.MILLISECOND, 0);
		endDate.setTime(date);
		if (user.getPhotos() == null)
			return null;
		for (Photo photo : user.getPhotos().values()) {
			Calendar photoDate = photo.getCalendarDate();
			if (photoDate.after(startDate) && photoDate.before(endDate))
				photos.add(photo);
			else if (photo.getCalendarDate().equals(startDate))
				photos.add(photo);
			else if (photo.getCalendarDate().equals(endDate))
				photos.add(photo);
		}
		Collections.sort(photos);
		return photos;
	}

	public ArrayList<Photo> getPhotosByTag(ArrayList<String> tags) {

		ArrayList<Photo> photos = new ArrayList<Photo>();
		String splitted[];
		for (int i = 0; i < tags.size(); i++) {
			splitted = tags.get(i).split(":");
			if (splitted.length == 2) { // Complete Tag Information
				String tagType = splitted[0].toUpperCase();
				String tagValue = splitted[1].replaceAll("\"", "");
				tagValue = tagValue.toLowerCase();
				for (Photo photo : user.getPhotos().values()) {
					if (photo.getTags().get(tagType + tagValue) != null)
						photos.add(photo);
				}
			} else if (splitted.length == 1) { // Partial information (Only tag
												// value)
				String tagValue = splitted[0].toLowerCase();
				for (Photo photo : user.getPhotos().values()) {
					if (photo.getTagValues().get(tagValue) != null)
						photos.add(photo);
				}
			}
		}
		Collections.sort(photos);
		return photos;
	}

	public boolean logIn(String username) {

		if (backend.getUser(username) != null) {
			User user = backend.getUser(username);
			this.user = user;
			return true;
		}
		return false;
	}

	public boolean addUser(String username, String fullName) {
		return backend.addUser(username, fullName);
	}

	public boolean deleteUser(String username) {
		return backend.deleteUser(username);
	}

	public Hashtable<String, User> getUsers() {
		return backend.getUsers();
	}

}
