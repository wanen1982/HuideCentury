package youda.admin.web.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;


public class BeanJsonUtils {
    private static Gson gson = new Gson();
    
    private static Gson gsonBulider = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @SuppressWarnings("unchecked")
    public static <T> T convertToObject(String string, Class<T> clazz) {
        return (T) convertToObject(string, (Type) clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T convertToObject(String json, Type type) {
        try {
            return (T) gson.fromJson(json, type);
        } catch (Exception e) {
            throw new RuntimeException(String.format("json转换至%类型时发行错误",((Class)type).getName()));
        }
    }

    public static String convertToJson(Object obj) {
        return gson.toJson(obj);
    }

    public static String convertToJson(Object obj, Type type) {
        return gson.toJson(obj, type);
    }

    public static String convertToJsonWithGsonBuilder(Object obj) {
        return gsonBulider.toJson(obj);
    }

    public static String convertToJsonWithGsonBuilder(Object obj, Type type) {
        return gsonBulider.toJson(obj, type);
    }

//    public static String convertToJsonWithException(OpenSocialException oe) {
//        BaseResponseItem<String> obj = new BaseResponseItem<String>(oe.getErrorModel().getStatus(), oe.getErrorModel().getError_description());
//        Type type = new TypeToken<BaseResponseItem<String>>() {}.getType();
//
//        return convertToJson(obj, type);
//    }
    
    public static String getJson(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException("读取request对象中的InputStream出现问题.");
        }
        return sb.toString();
    }

}
