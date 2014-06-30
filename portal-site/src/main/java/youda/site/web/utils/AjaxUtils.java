package youda.site.web.utils;

import java.util.HashMap;
import java.util.Map;

import net.paoding.rose.web.Invocation;

import com.google.gson.Gson;

public class AjaxUtils {
	
	private static Gson gson = new Gson();
	
	public static String printJson(Object dataMap, Invocation inv) {
    	String result = gson.toJson(dataMap);
    	inv.getResponse().setContentType("application/json;charset=utf-8");
        return "@" + result;
    }
	
	public static String printJson(int code, String message, Invocation inv){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code); 
		map.put("message", message);
		
		return printJson(map,inv);
	}
	
	public static String printErrorJson(String message, Invocation inv){
		return printJson(-1, message, inv);//-1表通用的error
	}
}
