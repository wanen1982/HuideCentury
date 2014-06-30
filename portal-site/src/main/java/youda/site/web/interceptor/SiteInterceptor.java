package youda.site.web.interceptor;

import java.util.List;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.model.ModelField;
import youda.component.service.ModelService;
import youda.site.web.annotation.Navigation;

@Component
public class SiteInterceptor extends ControllerInterceptorAdapter {
	
	@Autowired
	ModelService modelService;
	
	@Override
	protected Object after(Invocation inv, Object instruction) throws Exception {
		Class<? extends Object> controller = inv.getController().getClass();
		boolean isPresent = controller.isAnnotationPresent(Navigation.class) || inv.getMethod().isAnnotationPresent(Navigation.class);
		if(isPresent){
			//设置导航头信息
			List<ModelField> naviModelFieldList = modelService.getNavigationList();
			inv.addModel("navigationList", naviModelFieldList);
		}
		
		return super.after(inv, instruction);
	}
}
