package youda.component.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.dao.AdminDAO;
import youda.component.model.Admin;
import youda.component.service.AdminService;


@Component
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDao;
	
	@Override
	public Admin getUser(String username, String password) {
		password = DigestUtils.md5Hex(password);
		return adminDao.getUser(username, password);
	}

	@Override
	public int addUser(String username, String password, int roleid,
			String email, String realname) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(DigestUtils.md5Hex(password));
		admin.setRoleid(roleid);
		admin.setEmail(email);
		admin.setRealname(realname);
		
		return adminDao.addUser(admin);
	}

	@Override
	public int delUser(int userid) {
		return adminDao.delUser(userid);
	}

	@Override
	public List<Admin> getUserList() {
		List<Admin> userList = adminDao.getUserList();
		return userList;
	}

	@Override
	public int getUserTotalNum(int roleid) {
		return adminDao.countUserList(roleid);
	}

	@Override
	public int checkUserName(String username) {
		return adminDao.checkUserName(username);
	}

	@Override
	public Admin getUserById(int userid) {
		return adminDao.getUserById(userid);
	}

	@Override
	public int updateUser(Admin user) {
		return adminDao.updateUser(user);
	}
	
}
