package cs684.photoAlbum.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

/**
 * 
 * @author Haneef
 */


//This class represents a photo and all its corresponding information. 

public class Photo implements Serializable, Comparable<Photo> {
	
	private static final long serialVersionUID = 8866790839491002809L;

	private String filename=null;
	
	private Calendar date; 
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-H:m:s");
	private String dateString;
	
	private String caption;
	
	private Hashtable<String,Tag> tags;
	
//	private Hashtable<String,String> albums;
	
	private ArrayList<Album>albums;
	
	
	
	private Hashtable<String,String> tagValues;
	
	
	protected Photo(String fileName, String caption) {
		this.filename = fileName;
		this.caption = caption;		
		setDate(this.date);
		this.dateString = sdf.format(date.getTime());				
		this.tags = new Hashtable<String,Tag>();
		//this.albums = new Hashtable<String,String>();
		this.albums = new ArrayList<Album>();
		this.tagValues = new Hashtable<String,String>();
	}
	
	
	protected Photo(String fileName, String caption, Hashtable<String,Tag> tags){
		this.filename = fileName;
		this.tags = tags;
		this.caption = caption;
		setDate(this.date);
		this.tags = new Hashtable<String,Tag>();
		//this.albums = new Hashtable<String,String>();
		this.albums = new ArrayList<Album>();
	}
	
	
	public int numberOfAlbumsBelongingTo(){
		return this.albums.size();
	}
	
	
	public Hashtable<String,String> getTagValues(){
		return this.tagValues;
	}
	
	
	public String getFilename() {
		return filename;
	}
	
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	public String getStringDate() {
		return this.dateString;
	}
	
	public Calendar getCalendarDate() {
		return this.date;
	}
	
	
	private void setDate(Calendar date) {
		this.date = Calendar.getInstance();
	}
	
	
	public String getCaption() {
		return caption;
	}
	
	
	public void setCaption(String caption) {
		this.caption = caption;
	}

	
	public Hashtable<String,Tag> getTags() {
		return this.tags;
	}
	
	public void setTags(Hashtable<String,Tag> tags) {
		this.tags=tags;
	}
	
	
	public boolean addTag(String tagName, String tagValue){
		
	    tagName = tagName.toUpperCase();
	    tagValue = tagValue.toLowerCase();
	    
		String key = tagName+tagValue; //Key used to index a tag
		if(this.tags.get(key)!=null){
			return false;
		}
		Tag tag = new Tag(tagName,tagValue);
		this.tags.put(key,tag);
		tagValues.put(tagValue,tagValue);
		return true;
	}
	
	public boolean removeTag(String tagName, String tagValue){
		
		tagName = tagName.toUpperCase();
	    tagValue = tagValue.toLowerCase();
		
		String key = tagName+tagValue; //Key used to index a tag
		if(this.tags.get(key)==null){
			return false;
		}
	
		tags.remove(key);
		tagValues.remove(tagValue);
		return true;
	}	
	
	
	public int compareTo(Photo photo){
		
		if(this.date.before(photo.date))
			return -1;
		
		else if(this.date.after(photo.date))
			return 1;
			
		return 0;
	}
	
	
//	public Hashtable<String,String> getAlbums() {
//		return albums;
//	}
//	
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	
	
	
	public void addAlbums(String albumName) {
		this.albums.add(new Album(albumName));
	}

}
