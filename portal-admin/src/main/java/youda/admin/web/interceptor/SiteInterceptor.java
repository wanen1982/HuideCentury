package youda.admin.web.interceptor;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.admin.web.annotation.NotCareLogin;
import youda.admin.web.utils.UserUtils;
import youda.component.model.Admin;
import youda.component.service.AdminService;

import com.google.gson.JsonObject;

@Component
public class SiteInterceptor extends ControllerInterceptorAdapter {
	@Autowired
	AdminService adminService;
	
	@Override
	protected Object before(Invocation inv) throws Exception {
		Class<? extends Object> controller = inv.getController().getClass();
		boolean isPresent = controller.isAnnotationPresent(NotCareLogin.class) || inv.getMethod().isAnnotationPresent(NotCareLogin.class);
		if(isPresent){
			return true;
		}
		
		int userId = UserUtils.getUserId(inv);
		if(userId <= 0){
			return notLoginRedirect(inv);
		}
		
		Admin user = adminService.getUserById(UserUtils.getUserId(inv));
		inv.addModel("user", user);
		return true;
	}

	@Override
	protected Object after(Invocation inv, Object instruction) throws Exception {
		return super.after(inv, instruction);
	}
	
	Object notLoginRedirect(Invocation inv) {
		if(inv.getRequest().getHeader("Accept").contains("application/json")){
			JsonObject json = new JsonObject();
			json.addProperty("error_code", "1");
			return "@json:" + json.toString();
		}else{
			return "jump-to-login";
		}
	}
}
