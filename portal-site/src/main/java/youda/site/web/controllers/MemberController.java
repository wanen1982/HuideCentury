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
 * ��Աģ�������
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
		//�Ѹ��µĶ���浽session��
		inv.getRequest().getSession().setAttribute(Constants.WEB_USER_INFO,userInfo);
		return "member/profile_info";
	}
	
	/**
	 * �ύ��������
	 */
	@Post("completeInfo")
	public String completeMemberInfo(MemberBase paraBean){
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		jsonBean.setBean(paraBean);
		String checkMsg = checkCompleteInfoParams(jsonBean);
		//����д�����Ϣ������
		if(!"".equals(checkMsg)){
			return checkMsg;
		}
		this.memberService.updateCompleteMemberInfo(paraBean);
		return getResJsonBean2JsonView(jsonBean, "����ɹ�", true);
	}

	@Post("verifyCode")
	@Get("verifyCode")
	public String getVerifyCode() {
		HttpServletResponse response = inv.getResponse();
		HttpServletRequest request = inv.getRequest();
		response.setContentType("image/jpeg");// ������Ӧ����,������������������ΪͼƬ
		response.setHeader("Pragma", "No-cache");// ������Ӧͷ��Ϣ�������������Ҫ���������
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response);// ���ͼƬ����
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
		//����д�����Ϣ������
		if(!"".equals(checkMsg)){
			return checkMsg;
		}
		//��¼�ɹ��Ѷ���浽session��
		inv.getRequest().getSession().setAttribute(Constants.WEB_USER_INFO,jsonBean.getBean());
		//��¼�ɹ�
		return getResJsonBean2JsonView(jsonBean, "��Ա�ɹ���¼", true);
	}
	
	/**
	 * �û��ǳ�
	 */
	@Post("loginOut")
	@Get("loginOut")
	public String loginOut(){
		inv.getRequest().getSession().removeAttribute(Constants.WEB_USER_INFO);
		inv.getRequest().getSession().invalidate();
		return "r:/index";
	}
	

	/**
	 * ����ע��
	 */
	@Get("toRegister")
	public String toRegister() {
		return "member/register";
	}
	
	/**
	 *����ע�� 
	 */
	@Post("register")
	public String register(MemberBase bean,@Param("randomCode") String randomCode){
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		jsonBean.setBean(bean);
		String checkMsg = checkRegisterParams(jsonBean, randomCode);
		//����д�����Ϣ������
		if(!"".equals(checkMsg)){
			return checkMsg;
		}
		/**����ע��ɹ���Ϣ˳���¼*/
		this.memberService.save(bean.getEmail(), bean.getNickname(), bean.getPwd());
		inv.getRequest().getSession().setAttribute(Constants.WEB_USER_INFO,this.memberService.getLoginMember(bean.getNickname(), bean.getPwd()));
		return getResJsonBean2JsonView(jsonBean, "ע��ɹ�", true);
	}
	
	/*******private methods*******/
	//У��������Ϣ����
	private String checkCompleteInfoParams(ResponseJSONBean<MemberBase> jsonBean){
		MemberBase bean  = jsonBean.getBean();
		if(null==bean){
			return getResJsonBean2JsonView(jsonBean, "δ���յ��������ϲ���!", false);
		}
		if(null==bean.getId() || "".equals(bean.getId())){
			return getResJsonBean2JsonView(jsonBean, "�����µ�¼!", false);
		}
		if(null==bean.getSex() || "".equals(bean.getSex())){
			return getResJsonBean2JsonView(jsonBean, "�Ա���Ϊ��!", false);
		}
		if(null==bean.getHomeland() || "".equals(bean.getHomeland())){
			return getResJsonBean2JsonView(jsonBean, "���ڵز���Ϊ��!", false);
		}
		if(null==bean.getBirthday() || "".equals(bean.getBirthday())){
			return getResJsonBean2JsonView(jsonBean, "�������ڲ���Ϊ��!", false);
		}
		return "";
	}
	//У��ע����Ϣ
	private String checkRegisterParams(ResponseJSONBean<MemberBase> jsonBean,String randomCode){
		MemberBase bean  = jsonBean.getBean();
		if(null==bean){
			return getResJsonBean2JsonView(jsonBean, "δ���յ�ע�����!", false);
		}
		if(null==bean.getEmail() || "".equals(bean.getEmail())){
			return getResJsonBean2JsonView(jsonBean, "email����Ϊ��!", false);
		}
		if(null==bean.getNickname() || "".equals(bean.getNickname())){
			return getResJsonBean2JsonView(jsonBean, "�û�������Ϊ��!", false);
		}
		if(null==bean.getPwd() || "".equals(bean.getPwd())){
			return getResJsonBean2JsonView(jsonBean, "���벻��Ϊ��!", false);
		}
		if(null==randomCode || "".equals(randomCode)){
			return getResJsonBean2JsonView(jsonBean, "��֤����������!", false);
		}
		Object vifyCode = inv.getRequest().getSession().getAttribute(RandomValidateCode.RANDOM_VERIFY_KEY);
		/**
		 * ��֤�벻���ڻ��������
		 */
		if(!(null!=vifyCode && randomCode.equalsIgnoreCase(vifyCode.toString()))){
			return getResJsonBean2JsonView(jsonBean, "��֤����������!", false);
		}
		if(this.memberService.existsRegisterEmail(bean.getEmail())){
			return getResJsonBean2JsonView(jsonBean, "ע�������Ѿ���ע��,�������������������ַ!", false);
		}
		if(this.memberService.existsRegisterNickname(bean.getNickname())){
			return getResJsonBean2JsonView(jsonBean, "��Ա�����Ѿ���ע��,����µ����ƽ���ע��!", false);
		}
		return "";
	}
	//У���¼��Ϣ
	private String checkLoginParams(ResponseJSONBean<MemberBase> jsonBean,String nickname,String pwd,String randomCode){
		if(null==randomCode || "".equals(randomCode)){
			return getResJsonBean2JsonView(jsonBean, "��֤����������!", false);
		}
		Object vifyCode = inv.getRequest().getSession().getAttribute(RandomValidateCode.RANDOM_VERIFY_KEY);
		/**
		 * ��֤�벻���ڻ��������
		 */
		if(!(null!=vifyCode && randomCode.equalsIgnoreCase(vifyCode.toString()))){
			return getResJsonBean2JsonView(jsonBean, "��֤����������!", false);
		}
		if(null==pwd || "".equals(pwd)){
			return getResJsonBean2JsonView(jsonBean, "���벻��Ϊ��!", false);
		}
		if(null==nickname || "".equals(nickname)){
			return getResJsonBean2JsonView(jsonBean, "�û�������Ϊ��!", false);
		}
		//Ч���û������������Ч��
		try{
			MemberBase memberBase = this.memberService.getLoginMember(nickname, pwd);
			if(null==memberBase){
				return getResJsonBean2JsonView(jsonBean, "�û��������벻����!", false);
			}
			//��bean���õ�������
			jsonBean.setBean(memberBase);
		}catch(Exception e){
			return getResJsonBean2JsonView(jsonBean, "�û��������벻����!", false);
		}
		return "";
	}
	
	/**
	 * ��ResponseJSONBeanת��jsonstring
	 */
	private String getResJsonBean2JsonView(ResponseJSONBean<MemberBase> jsonBean,String message,boolean success){
		jsonBean.setMessage(message);
		jsonBean.setSuccess(success);
		return "@json:"+BeanJsonUtils.convertToJson(jsonBean);
	}
	
	public static class RandomValidateCode {

		public static final String RANDOM_VERIFY_KEY = "random.verify.key";// �ŵ�session�е�key
		private static Random random = new Random();
		private final static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// ����������ַ���

		private int width = 80;// ͼƬ��
		private int height = 26;// ͼƬ��
		private int lineSize = 40;// ����������
		private int stringNum = 4;// ��������ַ�����

		/*
		 * �������
		 */
		private Font getFont() {
			return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
		}

		/*
		 * �����ɫ
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
		 * �������ͼƬ
		 */
		public void getRandcode(HttpServletRequest request,
				HttpServletResponse response) {
			HttpSession session = request.getSession();
			// BufferedImage���Ǿ��л�������Image��,Image������������ͼ����Ϣ����
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			Graphics g = image.getGraphics();// ����Image�����Graphics����,�Ķ��������ͼ���Ͻ��и��ֻ��Ʋ���
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
			g.setColor(getRandColor(110, 133));
			// ���Ƹ�����
			for (int i = 0; i <= lineSize; i++) {
				drowLine(g);
			}
			// ��������ַ�
			String randomString = "";
			for (int i = 1; i <= stringNum; i++) {
				randomString = drowString(g, randomString, i);
			}
			session.removeAttribute(RANDOM_VERIFY_KEY);
			session.setAttribute(RANDOM_VERIFY_KEY, randomString);
//			System.out.println(randomString);
			g.dispose();
			try {
				ImageIO.write(image, "JPEG", response.getOutputStream());// ���ڴ��е�ͼƬͨ��������ʽ������ͻ���
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * �����ַ���
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
		 * ���Ƹ�����
		 */
		private void drowLine(Graphics g) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(13);
			int yl = random.nextInt(15);
			g.drawLine(x, y, x + xl, y + yl);
		}

		/*
		 * ��ȡ������ַ�
		 */
		public String getRandomString(int num) {
			return String.valueOf(randString.charAt(num));
		}
	}
}
