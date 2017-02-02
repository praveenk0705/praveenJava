package cs684.photoAlbum.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

/**
 * This class represents an album and all its corresponding information.
 * 
 * @author Haneef
 */
public class Album implements Serializable {
	
	private static final long serialVersionUID = -3314782389344816179L;
	
	private String albumName;
	
	Hashtable<String, Photo> photos;
	
	private Hashtable<String, String> photosInAlbum;

	
	public Album(String albumName) {
		this.albumName = albumName;
		this.photosInAlbum = new Hashtable<String, String>();
	}

	public String getAlbumName() {
		return albumName;
	}

	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	
	public int numberOfPhotosBelongingTo() {
		return this.photosInAlbum.size();
	}

	
	public void addPhotoInAlbum(String filename) {
		this.photosInAlbum.put(filename, filename);
	}

	public void removePhotoInAlbum(String filename) { // deletes pointer to
														// picture
		this.photosInAlbum.remove(filename);
	}

	
	public boolean addPhoto(String fileName, String caption, User user) {
		// If the photo is not in the album and its not in the user then we need
		// to create a new photo!
		System.out.println("File name" + fileName);
		if (photosInAlbum.get(fileName) == null
				&& user.getPhotos().get(fileName) == null) {
			photosInAlbum.put(fileName, fileName); // Add photo to list of
													// string
			Photo photo = new Photo(fileName, caption); // Creates actual photo
			photo.addAlbums(this.albumName); // Adds the name of the album to
												// the array of albums ( strings
												// )
			photos = user.getPhotos();
			photos.put(fileName, photo); // Add actual photo to array of photo
											// objects
			return true;
		} else { // The photo is not in the album but it is in the user!! then
					// we dont need to create a new photo!
			if (photosInAlbum.get(fileName) == null
					&& user.getPhotos().get(fileName) != null) {
				photosInAlbum.put(fileName, fileName); // Add photo to list of
														// string
				photos = user.getPhotos();
				Photo photo = photos.get(fileName); // Returns photo
				photo.addAlbums(this.albumName); // Adds the name of the album
													// to the array of albums (
													// strings )
				return true;
			}
		}
		return false;
	}

	
	public boolean findPhotoInAlbum(String fileName) {
		if (photosInAlbum.get(fileName) != null) {
			return true;
		}
		return false;
	}

	
	public Photo getPhotoInAlbum(String fileName, User user) {
		if (photosInAlbum.get(fileName) != null) {
			photos = user.getPhotos();
			if (photos.get(fileName) != null) {
				return photos.get(fileName);
			}
			return null;
		}
		return null;
	}

	
	public Hashtable<String, Photo> getPhotosInAlbum(User user) {
		Hashtable<String, Photo> photos = new Hashtable<String, Photo>();
		for (String fileName : photosInAlbum.values()) {
			Photo photo = getPhotoInAlbum(fileName, user);
			photos.put(photo.getFilename(), photo);
		}
		return photos;
	}

	
	public boolean deletePhoto(String fileName, User user) {
		if (photosInAlbum.get(fileName) != null) {
			photos = user.getPhotos();
			photos.remove(fileName); // Remove actual photo
			photosInAlbum.remove(fileName); // remove string filename from album
			return true;
		}
		return false;
	}

	 
	public boolean editPhoto(String fileName, String caption,
			Hashtable<String, Tag> tags, User user) {
		if (photosInAlbum.get(fileName) != null) {
			photos = user.getPhotos();
			photos.get(fileName).setCaption(caption); // Editing photo object -
														// caption
			photos.get(fileName).setTags(tags); // Editing photo object - tags
			return true;
		}
		return false;
	}

	
	public boolean recaptionPhoto(String fileName, String caption, User user) {
		if (photosInAlbum.get(fileName) != null) {
			photos = user.getPhotos();
			photos.get(fileName).setCaption(caption);
			return true;
		}
		return false;
	}

	
	public String minDate(User user) throws ParseException {
		Hashtable<String, Photo> photos = new Hashtable<String, Photo>();
		photos = getPhotosInAlbum(user);
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MILLISECOND, 0);
		String currentDate = sdf.format(date.getTime());
		if (photos.size() > 0) {
			for (Photo photo : photos.values()) {
				if (i == 0) {
					date = photo.getCalendarDate();
					currentDate = sdf.format(date.getTime());
				}
				if (photo.getCalendarDate().compareTo(date) < 0) {
					date = photo.getCalendarDate();
					currentDate = sdf.format(date.getTime());
				}
				i++;
			}
		}
		return currentDate;
	}

	
	public String maxDate(User user) throws ParseException {
		Hashtable<String, Photo> photos = new Hashtable<String, Photo>();
		photos = getPhotosInAlbum(user);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MILLISECOND, 0);
		String currentDate = sdf.format(date.getTime());
		int i = 0;
		if (photos.size() > 0) {
			for (Photo photo : photos.values()) {
				if (i == 0) {
					date = photo.getCalendarDate();
					currentDate = sdf.format(date.getTime());
				}
				if (photo.getCalendarDate().compareTo(date) > 0) {
					date = photo.getCalendarDate();
					currentDate = sdf.format(date.getTime());
				}
				i++;
			}
		}
		return currentDate;
	}
}