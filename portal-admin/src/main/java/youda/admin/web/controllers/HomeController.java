package youda.admin.web.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import youda.admin.web.annotation.NotCareLogin;
import youda.component.model.Admin;
import youda.component.service.AdminService;

@Path("")
public class HomeController {

	@Autowired
	Invocation inv;
	@Autowired
	AdminService adminService;
	
	@NotCareLogin
	@Get("login")
	public String login(){
		String errorMSG = inv.getFlash().get("errorMSG");
		if(!StringUtils.isEmpty(errorMSG)){
			inv.addModel("errorMSG", errorMSG);
		}
		return "login";
	}
	
	@Get("logout")
	public String logout(){
		HttpSession userSession = inv.getRequest().getSession();
		userSession.removeAttribute("userId");
		return "r:/login";
	}
	
	@NotCareLogin
	@Post("login")
	public String userLogin(@Param("username") String username, @Param("password") String password){
		Admin user = adminService.getUser(username, password);
		if(user == null){
			inv.addFlash("errorMSG", "用户名或密码不正确！");
			return "r:/login";
		}
		HttpSession userSession = inv.getRequest().getSession();
		userSession.setAttribute("userId", user.getUserid());
		userSession.setAttribute("lastloginip", user.getLastloginip());
		userSession.setAttribute("lastlogintime", user.getLastlogintime());
		
		user.setLastloginip(inv.getRequest().getRemoteHost());
		user.setLastlogintime(new Date());
		adminService.updateUser(user);
		return "r:/index";
	}
	
	@Get("")
	public String none(){
		return "r:/index";
	}
	
	@Get("index")
    public String index() {
    	return "index";
    }
	
	@Get("menu/mypanel")
    public String mypanel() {
    	return "menu/mypanel";
    }
	
	@Get("menu/model-m")
    public String modelM() {
    	return "menu/model-manage";
    }
	
	@Get("menu/skin-m")
    public String skinM() {
    	return "menu/skin-manage";
    }
	
	@Get("menu/content-m")
    public String contentM() {
    	return "menu/content-manage";
    }
	
	@Get("menu/access-m")
    public String accessM() {
    	return "menu/access-manage";
    }
	
	@Get("menu/publish-m")
	public String publishM(){
		return "menu/publish-manage";
	}
	
	@Get("menu/extension")
    public String extension() {
    	return "menu/extension";
    }
	
	@Get("menu/setting")
    public String setting() {
    	return "menu/setting";
    }
	
	/**
	 * 老师管理
	 */
	@Get("menu/teacher-mgr")
	public String teacherMgr(){
		return "menu/teacherMgr";
	}
	
//	/**
//	 * 学生管理
//	 */
//	@Get("menu/student-mgr")
//	public String studentMgr(){
//		return "menu/studentMgr";
//	}
	
	@Get("empty")
    public String empty() {
    	return "empty";
    }
}
