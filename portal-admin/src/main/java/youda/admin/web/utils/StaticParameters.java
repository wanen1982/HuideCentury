package youda.admin.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 静太参数
 * @author wanen
 *
 */
public class StaticParameters {
	public static final Map<Integer,String> USER_ROLE = new HashMap<Integer, String>();
	static {
		USER_ROLE.put(0,"超级管理员");
		USER_ROLE.put(1,"学校主任");
		USER_ROLE.put(2,"双语早教");
		USER_ROLE.put(3,"妙趣英语");
		USER_ROLE.put(4,"芭蕾形体");
		USER_ROLE.put(5,"中国舞");
		USER_ROLE.put(6,"瑜伽");
		USER_ROLE.put(7,"综合体能");
		USER_ROLE.put(8,"艺术手工");
		USER_ROLE.put(9,"创意绘画");
		USER_ROLE.put(10,"小小美厨");
		USER_ROLE.put(11,"钢琴一对一");
	}
}
