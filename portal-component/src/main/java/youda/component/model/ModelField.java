package youda.component.model;

import java.io.Serializable;
import java.util.List;

public class ModelField implements Serializable {

	private static final long serialVersionUID = -7093420764850637256L;

	private int fieldId;
	
	private int modelId;
	
	private int catId;
	
	private String belong;
	
	private String showName;
	
	private int listOrder;
	
	private int editable;
	
	private int locked;
	
	private Category linkCategory;
	
	private List<Category> childCategoryList;

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public Category getLinkCategory() {
		return linkCategory;
	}

	public void setLinkCategory(Category linkCategory) {
		this.linkCategory = linkCategory;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public int getEditable() {
		return editable;
	}

	public void setEditable(int editable) {
		this.editable = editable;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public List<Category> getChildCategoryList() {
		return childCategoryList;
	}

	public void setChildCategoryList(List<Category> childCategoryList) {
		this.childCategoryList = childCategoryList;
	}
}
