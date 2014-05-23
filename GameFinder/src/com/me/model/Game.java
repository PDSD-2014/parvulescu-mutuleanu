package com.me.model;

import java.io.Serializable;
import com.me.general.Constants;

public class Game implements Serializable {

	private String name;
	private String description;
	private String publisher;
	private String picture;
	private String genre;
	private String platform;
	
	public Game() {
		this.name = new String();
		this.description = new String();
		this.publisher = new String();
		this.picture = new String();
		this.genre = new String();;
		this.platform = new String();
	}
	
	public Game(String name, String description, String publisher, String picture, String genre, String platform)
	{
		this.name = name;
		this.description = description;
		this.publisher = publisher;
		this.picture = picture;
		this.genre = genre;
		this.platform = platform;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public Object get(String attribute) {
		if (attribute.equals(Constants.NAME_TAG))
			return name;
		else if (attribute.equals(Constants.DESCRIPTION_TAG))
			return description;
		else if (attribute.equals(Constants.PUBLISHER_TAG))
			return publisher;
		else if (attribute.equals(Constants.PICTURE_TAG))
			return picture;
		else if (attribute.equals(Constants.GENRE_TAG))
			return genre;
		else if (attribute.equals(Constants.PLATFORM_TAG))
			return platform;

		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public void set(String attribute, Object value) {
		if (attribute.equals(Constants.NAME_TAG))
			setName((String)value);
		else if (attribute.equals(Constants.DESCRIPTION_TAG))
			setDescription((String)value);
		else if (attribute.equals(Constants.PUBLISHER_TAG))
			setPublisher((String)value);
		else if (attribute.equals(Constants.PICTURE_TAG))
			setPicture((String)value);
		else if (attribute.equals(Constants.GENRE_TAG))
			setGenre((String)value);
		else if (attribute.equals(Constants.PLATFORM_TAG))
			setPlatform((String)value);
	}

}
