package com.me.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game {

	private String name;
	private String description;
	private String publisher;
	private String picture;
	private int genre;
	private int platform;
	
	public Game() {}
	
	public Game(String name, String description, String publisher, String picture, int genre, int platform)
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

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

}
