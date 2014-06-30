package youda.component.model;

import java.io.Serializable;

public class AdminRole implements Serializable{

	private static final long serialVersionUID = 678303081984356318L;

	private int roleid;
	
	private String rolename;
	
	private String description;
	
	private int listorder;
	
	private int disabled;

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
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

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
}
