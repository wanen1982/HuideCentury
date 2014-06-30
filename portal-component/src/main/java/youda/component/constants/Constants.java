package youda.component.constants;

public class Constants {
	
	public static String WWW_DOMAIN;
	public static String ADMIN_DOMAIN;
	
	public static String IMG_DOMAIN;
	
	static {
		WWW_DOMAIN = "123.smarttrans.cn";
		ADMIN_DOMAIN = "admin.123.smarttrans.cn";
		IMG_DOMAIN = "admin.123.smarttrans.cn";
//		WWW_DOMAIN = "localhost";
//		ADMIN_DOMAIN = "localhost";
//		IMG_DOMAIN = "localhost";
	}

	public static final String URL_WWW = "http://" + WWW_DOMAIN;
	public static final String URL_ADMIN = "http://" + ADMIN_DOMAIN;
	public static final String URL_IMG = "http://" + IMG_DOMAIN;
	
	public static final String ROOT_PATH = "/data/gummy/img";
	public static final String CAROUSEL_PATH = ROOT_PATH + "/gummy/carousel";
	public static final String IMG_PATH = ROOT_PATH + "/gummy/img";
	public static final String PARTNER_ICON_PATH = ROOT_PATH + "/gummy/partner";
	
	/**
	 * 存放web变量的key
	 */
	public static final String WEB_USER_INFO = "sessionUserInfo";
}
