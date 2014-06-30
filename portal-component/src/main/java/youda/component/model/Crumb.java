package youda.component.model;

import java.io.Serializable;

public class Crumb implements Serializable{

	private static final long serialVersionUID = 7060721739552714520L;

	private String categoryName;
	private int categoryId;
	
	public Crumb(Category category){
		this.categoryName = category.getCatName();
		this.categoryId = category.getCatId();
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
