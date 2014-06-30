package youda.component.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Category implements Serializable{

	private static final long serialVersionUID = 1040645765179246778L;

	private int catId;
	
	private int parentId;
	
	private int haveChild;
	
	private int level;
	
	private String catName;
	
	private String type;
	
	private int status;
	
	private String pathName;
	
	private int listOrder;
	
	private int needApprove;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int createBy;
	
	private int updateBy;
	
	private List<Category> childCategorys;
	
	private int lastFlag;
	
	private int startFlag;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getHaveChild() {
		return haveChild;
	}

	public void setHaveChild(int haveChild) {
		this.haveChild = haveChild;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public int getNeedApprove() {
		return needApprove;
	}

	public void setNeedApprove(int needApprove) {
		this.needApprove = needApprove;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public List<Category> getChildCategorys() {
		return childCategorys;
	}

	public void setChildCategorys(List<Category> childCategorys) {
		this.childCategorys = childCategorys;
	}

	public int getLastFlag() {
		return lastFlag;
	}

	public void setLastFlag(int lastFlag) {
		this.lastFlag = lastFlag;
	}

	public int getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(int startFlag) {
		this.startFlag = startFlag;
	}
}
