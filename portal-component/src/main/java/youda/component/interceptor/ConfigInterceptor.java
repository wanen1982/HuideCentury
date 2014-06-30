package youda.component.interceptor;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

import org.springframework.stereotype.Component;

import youda.component.constants.Constants;

@Component
public class ConfigInterceptor extends ControllerInterceptorAdapter {

    
    @Override
    public Object before(Invocation inv) throws Exception {
    	inv.addModel("siteUrl", Constants.URL_WWW);
    	inv.addModel("adminUrl", Constants.URL_ADMIN);
    	inv.addModel("imageUrl", Constants.URL_IMG);
        return true;
    }
    
}
