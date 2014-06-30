package youda.component.model;

import java.io.Serializable;

public class Partner implements Serializable{

	private static final long serialVersionUID = -1226223483596160362L;

	private int id;
	
	private String name;
	
	private String iconPath;
	
	private String linkUrl;
	
	private int listorder;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getListorder() {
		return listorder;
	}

	public void setListorder(int listorder) {
		this.listorder = listorder;
	}
	
}
