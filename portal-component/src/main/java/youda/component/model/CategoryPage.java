package youda.component.model;

import java.io.Serializable;

public class CategoryPage implements Serializable{

	private static final long serialVersionUID = -8127960741442143864L;

	private int pid;
	
	private int catId;
	
	private String pageContent;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
}
