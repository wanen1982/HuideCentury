package youda.component.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Model implements Serializable {

	private static final long serialVersionUID = -7401238127399843707L;
	
	private int modelId;
	
	private String name;
	
	private String description;
	
	private int minCategorynums;
	
	private int maxCategorynums;
	
	private int isNavigation;
	
	private Date updateTime;
	
	private int updateBy;

	private List<ModelField> modelFieldList;
	
	//自运营位数目
	private int manualNums;
	
	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
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

	public int getMinCategorynums() {
		return minCategorynums;
	}

	public void setMinCategorynums(int minCategorynums) {
		this.minCategorynums = minCategorynums;
	}

	public int getMaxCategorynums() {
		return maxCategorynums;
	}

	public void setMaxCategorynums(int maxCategorynums) {
		this.maxCategorynums = maxCategorynums;
	}

	public int getIsNavigation() {
		return isNavigation;
	}

	public void setIsNavigation(int isNavigation) {
		this.isNavigation = isNavigation;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public List<ModelField> getModelFieldList() {
		return modelFieldList;
	}

	public void setModelFieldList(List<ModelField> modelFieldList) {
		this.modelFieldList = modelFieldList;
	}

	public int getManualNums() {
		return manualNums;
	}

	public void setManualNums(int manualNums) {
		this.manualNums = manualNums;
	}
}
