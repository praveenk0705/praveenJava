package cs684.photoAlbum.model;

import java.io.Serializable;

/**
 * 
 * @author Haneef
 */


//This class represents a tag and its information. 


public class Tag implements Serializable ,Comparable<Tag>{

	
	private static final long serialVersionUID = -9054235967448665716L;
	
	private String tagType;
	
	private String tagValue;
	
	
	public String getTagType() {
		return tagType;
	}
	
	
	public Tag(String tagType, String tagValue){
		this.tagType = tagType;
		this.tagValue = tagValue;
	}
	
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	
	
	public String getTagValue() {
		return tagValue;
	}
	
	
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	
	public int compareTo(Tag tag){
		
		if( (tag.tagType.equalsIgnoreCase(this.tagType)) && (tag.tagValue.equalsIgnoreCase(tag.tagValue)))
			return 0;
		
		return -1;		
	}

}
