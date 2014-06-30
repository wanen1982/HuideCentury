package youda.component.service;

import java.util.List;

import youda.component.model.Admin;
import youda.component.model.AdminRole;

public interface AdminService {
	
	public Admin getUser(String username, String password);
	
	public Admin getUserById(int userid);
	
	public int addUser(String username, String password, int roleid, String email, String realname);
	
	public int delUser(int userid);
	
	public List<Admin> getUserList();
	
	public int getUserTotalNum(int roleid);
	
	public int checkUserName(String username);
	
	public int updateUser(Admin user);
}
