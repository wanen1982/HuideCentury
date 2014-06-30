package youda.admin.web.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import youda.admin.web.utils.UserUtils;
import youda.component.model.Admin;
import youda.component.service.AdminService;

@Path("")
public class MyPanelController {

	@Autowired
	Invocation inv;
	@Autowired
	AdminService adminService;
	
	@Get("mypanel/info")
	public String personalInfo(){
		inv.addModel("lastloginip", UserUtils.getUserLastLoginIP(inv));
		inv.addModel("lastlogintime", UserUtils.getUserLastLoginTime(inv));
		return "mypanel/personal-info";
	}
	
	@Get("mypanel/changePassword")
	public String changePassword(){
		String errorMSG = inv.getFlash().get("errorMSG");
		if(!StringUtils.isEmpty(errorMSG)){
			inv.addModel("errorMSG", errorMSG);
		}
		return "mypanel/change-password";
	}
	
	@Post("mypanel/changePassword")
	public String updatePassword(@Param("password") String password, @Param("re-password") String repassword){
		if(StringUtils.isEmpty(password)){
			inv.addFlash("errorMSG", "密码不能为空!");
			return "r:/mypanel/changePassword";
		}
		if(!password.equals(repassword)){
			inv.addFlash("errorMSG", "两次输入的密码不正确。");
			return "r:/mypanel/changePassword";
		}
		int userid = UserUtils.getUserId(inv);
		Admin admin = adminService.getUserById(userid);
		admin.setPassword(DigestUtils.md5Hex(password));
		adminService.updateUser(admin);
		
		inv.addModel("errorMSG", "密码修改成功!");
		
		return "mypanel/change-password";
	}
}
