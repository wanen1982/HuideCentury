package youda.site.web.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import youda.component.constants.Constants;
import youda.component.model.MemberBase;
import youda.component.model.ResponseJSONBean;
import youda.component.service.MemberService;
import youda.site.web.utils.BeanJsonUtils;

/**
 * 会员模块控制器
 * 
 * @author we
 * 
 */
@Path("member")
public class MemberController {
	@Autowired
	Invocation inv;

	@Autowired
	private MemberService memberService;
	
	@Get("profile")
	public String toMemberProfile(){
		Object sesUserInfo = inv.getRequest().getSession().getAttribute(Constants.WEB_USER_INFO);
		MemberBase userInfo = (MemberBase) sesUserInfo;
		userInfo = this.memberService.findById(userInfo.getId());
		//把更新的对象存到session中
		inv.getRequest().getSession().setAttribute(Constants.WEB_USER_INFO,userInfo);
		return "member/profile_info";
	}
	
	/**
	 * 提交更新资料
	 */
	@Post("completeInfo")
	public String completeMemberInfo(MemberBase paraBean){
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		jsonBean.setBean(paraBean);
		String checkMsg = checkCompleteInfoParams(jsonBean);
		//检查有错误信息即返回
		if(!"".equals(checkMsg)){
			return checkMsg;
		}
		this.memberService.updateCompleteMemberInfo(paraBean);
		return getResJsonBean2JsonView(jsonBean, "保存成功", true);
	}

	@Post("verifyCode")
	@Get("verifyCode")
	public String getVerifyCode() {
		HttpServletResponse response = inv.getResponse();
		HttpServletRequest request = inv.getRequest();
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response);// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Get("toLogin")
	public String toLogin() {
		return "member/login";
	}
	
	@Post("login")
	public String login(@Param("nickname")String nickname,@Param("pwd") String pwd,@Param("randomCode") String randomCode){
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		String checkMsg = checkLoginParams(jsonBean, nickname, pwd, randomCode);
		//检查有错误信息即返回
		if(!"".equals(checkMsg)){
			return checkMsg;
		}
		//登录成功把对象存到session中
		inv.getRequest().getSession().setAttribute(Constants.WEB_USER_INFO,jsonBean.getBean());
		//登录成功
		return getResJsonBean2JsonView(jsonBean, "会员成功登录", true);
	}
	
	/**
	 * 用户登出
	 */
	@Post("loginOut")
	@Get("loginOut")
	public String loginOut(){
		inv.getRequest().getSession().removeAttribute(Constants.WEB_USER_INFO);
		inv.getRequest().getSession().invalidate();
		return "r:/index";
	}
	

	/**
	 * 进入注册
	 */
	@Get("toRegister")
	public String toRegister() {
		return "member/register";
	}
	
	/**
	 *进行注册 
	 */
	@Post("register")
	public String register(MemberBase bean,@Param("randomCode") String randomCode){
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		jsonBean.setBean(bean);
		String checkMsg = checkRegisterParams(jsonBean, randomCode);
		//检查有错误信息即返回
		if(!"".equals(checkMsg)){
			return checkMsg;
		}
		/**保存注册成功信息顺便登录*/
		this.memberService.save(bean.getEmail(), bean.getNickname(), bean.getPwd());
		inv.getRequest().getSession().setAttribute(Constants.WEB_USER_INFO,this.memberService.getLoginMember(bean.getNickname(), bean.getPwd()));
		return getResJsonBean2JsonView(jsonBean, "注册成功", true);
	}
	
	/*******private methods*******/
	//校验完善信息参数
	private String checkCompleteInfoParams(ResponseJSONBean<MemberBase> jsonBean){
		MemberBase bean  = jsonBean.getBean();
		if(null==bean){
			return getResJsonBean2JsonView(jsonBean, "未接收到完善资料参数!", false);
		}
		if(null==bean.getId() || "".equals(bean.getId())){
			return getResJsonBean2JsonView(jsonBean, "请重新登录!", false);
		}
		if(null==bean.getSex() || "".equals(bean.getSex())){
			return getResJsonBean2JsonView(jsonBean, "性别不能为空!", false);
		}
		if(null==bean.getHomeland() || "".equals(bean.getHomeland())){
			return getResJsonBean2JsonView(jsonBean, "所在地不能为空!", false);
		}
		if(null==bean.getBirthday() || "".equals(bean.getBirthday())){
			return getResJsonBean2JsonView(jsonBean, "出生日期不能为空!", false);
		}
		return "";
	}
	//校验注册信息
	private String checkRegisterParams(ResponseJSONBean<MemberBase> jsonBean,String randomCode){
		MemberBase bean  = jsonBean.getBean();
		if(null==bean){
			return getResJsonBean2JsonView(jsonBean, "未接收到注册参数!", false);
		}
		if(null==bean.getEmail() || "".equals(bean.getEmail())){
			return getResJsonBean2JsonView(jsonBean, "email不能为空!", false);
		}
		if(null==bean.getNickname() || "".equals(bean.getNickname())){
			return getResJsonBean2JsonView(jsonBean, "用户名不能为空!", false);
		}
		if(null==bean.getPwd() || "".equals(bean.getPwd())){
			return getResJsonBean2JsonView(jsonBean, "密码不能为空!", false);
		}
		if(null==randomCode || "".equals(randomCode)){
			return getResJsonBean2JsonView(jsonBean, "验证码输入有误!", false);
		}
		Object vifyCode = inv.getRequest().getSession().getAttribute(RandomValidateCode.RANDOM_VERIFY_KEY);
		/**
		 * 验证码不存在或输入错误
		 */
		if(!(null!=vifyCode && randomCode.equalsIgnoreCase(vifyCode.toString()))){
			return getResJsonBean2JsonView(jsonBean, "验证码输入有误!", false);
		}
		if(this.memberService.existsRegisterEmail(bean.getEmail())){
			return getResJsonBean2JsonView(jsonBean, "注册邮箱已经被注册,请重新输入其它邮箱地址!", false);
		}
		if(this.memberService.existsRegisterNickname(bean.getNickname())){
			return getResJsonBean2JsonView(jsonBean, "会员名称已经被注册,请更新的名称进行注册!", false);
		}
		return "";
	}
	//校验登录信息
	private String checkLoginParams(ResponseJSONBean<MemberBase> jsonBean,String nickname,String pwd,String randomCode){
		if(null==randomCode || "".equals(randomCode)){
			return getResJsonBean2JsonView(jsonBean, "验证码输入有误!", false);
		}
		Object vifyCode = inv.getRequest().getSession().getAttribute(RandomValidateCode.RANDOM_VERIFY_KEY);
		/**
		 * 验证码不存在或输入错误
		 */
		if(!(null!=vifyCode && randomCode.equalsIgnoreCase(vifyCode.toString()))){
			return getResJsonBean2JsonView(jsonBean, "验证码输入有误!", false);
		}
		if(null==pwd || "".equals(pwd)){
			return getResJsonBean2JsonView(jsonBean, "密码不能为空!", false);
		}
		if(null==nickname || "".equals(nickname)){
			return getResJsonBean2JsonView(jsonBean, "用户名不能为空!", false);
		}
		//效验用户名和密码的有效性
		try{
			MemberBase memberBase = this.memberService.getLoginMember(nickname, pwd);
			if(null==memberBase){
				return getResJsonBean2JsonView(jsonBean, "用户名或密码不存在!", false);
			}
			//把bean设置到对象中
			jsonBean.setBean(memberBase);
		}catch(Exception e){
			return getResJsonBean2JsonView(jsonBean, "用户名或密码不存在!", false);
		}
		return "";
	}
	
	/**
	 * 将ResponseJSONBean转成jsonstring
	 */
	private String getResJsonBean2JsonView(ResponseJSONBean<MemberBase> jsonBean,String message,boolean success){
		jsonBean.setMessage(message);
		jsonBean.setSuccess(success);
		return "@json:"+BeanJsonUtils.convertToJson(jsonBean);
	}
	
	public static class RandomValidateCode {

		public static final String RANDOM_VERIFY_KEY = "random.verify.key";// 放到session中的key
		private static Random random = new Random();
		private final static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串

		private int width = 80;// 图片宽
		private int height = 26;// 图片高
		private int lineSize = 40;// 干扰线数量
		private int stringNum = 4;// 随机产生字符数量

		/*
		 * 获得字体
		 */
		private Font getFont() {
			return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
		}

		/*
		 * 获得颜色
		 */
		private Color getRandColor(int fc, int bc) {
			if (fc > 255)
				fc = 255;
			if (bc > 255)
				bc = 255;
			int r = fc + random.nextInt(bc - fc - 16);
			int g = fc + random.nextInt(bc - fc - 14);
			int b = fc + random.nextInt(bc - fc - 18);
			return new Color(r, g, b);
		}

		/**
		 * 生成随机图片
		 */
		public void getRandcode(HttpServletRequest request,
				HttpServletResponse response) {
			HttpSession session = request.getSession();
			// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
			g.setColor(getRandColor(110, 133));
			// 绘制干扰线
			for (int i = 0; i <= lineSize; i++) {
				drowLine(g);
			}
			// 绘制随机字符
			String randomString = "";
			for (int i = 1; i <= stringNum; i++) {
				randomString = drowString(g, randomString, i);
			}
			session.removeAttribute(RANDOM_VERIFY_KEY);
			session.setAttribute(RANDOM_VERIFY_KEY, randomString);
//			System.out.println(randomString);
			g.dispose();
			try {
				ImageIO.write(image, "JPEG", response.getOutputStream());// 将内存中的图片通过流动形式输出到客户端
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * 绘制字符串
		 */
		private String drowString(Graphics g, String randomString, int i) {
			g.setFont(getFont());
			g.setColor(new Color(random.nextInt(101), random.nextInt(111),
					random.nextInt(121)));
			String rand = String.valueOf(getRandomString(random
					.nextInt(randString.length())));
			randomString += rand;
			g.translate(random.nextInt(3), random.nextInt(3));
			g.drawString(rand, 13 * i, 16);
			return randomString;
		}

		/*
		 * 绘制干扰线
		 */
		private void drowLine(Graphics g) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(13);
			int yl = random.nextInt(15);
			g.drawLine(x, y, x + xl, y + yl);
		}

		/*
		 * 获取随机的字符
		 */
		public String getRandomString(int num) {
			return String.valueOf(randString.charAt(num));
		}
	}
}
