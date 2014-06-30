package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.Admin;

@DAO
public interface AdminDAO {

	final static String admin = "userid,username,password,roleid,lastloginip,lastlogintime,email,realname";
	
	@SQL("select " + admin + " from admin where username = :1 and password = :2")
	public Admin getUser(String username, String password);
	
	@SQL("select " + admin + " from admin where userid = :1")
	public Admin getUserById(int userid);
	
	@SQL("insert into admin(username,password,roleid,email,realname)" +
	 	  "values (:1.username,:1.password,:1.roleid,:1.email,:1.realname)")
	public int addUser(Admin admin);
	
	@SQL("delete from admin where userid = :1")
	public int delUser(int userid);
	
	@SQL("select " + admin + " from admin order by lastlogintime desc")
	public List<Admin> getUserList();
	
	@SQL("select count(*) from admin")
	public int countUserList(int roleid);
	
	@SQL("select count(*) from admin where username = :1")
	public int checkUserName(String username);
	
	@SQL("update admin set username=:1.username,password=:1.password,roleid=:1.roleid,lastloginip=:1.lastloginip," +
		 "lastlogintime=:1.lastlogintime,email=:1.email,realname=:1.realname where userid=:1.userid")
	public int updateUser(Admin user);
}
