package youda.component.model;

import java.io.Serializable;

public class Carousel implements Serializable{

	private static final long serialVersionUID = -1196691293501998427L;

	private int id;
	
	private String picpath;
	
	private String title;
	
	private String description;
	
	private int listorder;
	
	private String linkUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getListorder() {
		return listorder;
	}

	public void setListorder(int listorder) {
		this.listorder = listorder;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
