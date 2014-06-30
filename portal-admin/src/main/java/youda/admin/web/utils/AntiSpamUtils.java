package youda.admin.web.utils;

public class AntiSpamUtils {

	public static String safeText(String content){
		return content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
