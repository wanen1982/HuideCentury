package youda.admin.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import youda.admin.web.utils.AntiSpamUtils;
import youda.admin.web.utils.BeanJsonUtils;
import youda.admin.web.utils.StaticParameters;
import youda.component.constants.ConsumeVariable;
import youda.component.model.Admin;
import youda.component.model.ConsumeNote;
import youda.component.model.CourseView;
import youda.component.model.MemberBase;
import youda.component.model.ResponseJSONBean;
import youda.component.model.Teacher;
import youda.component.service.AdminService;
import youda.component.service.CategoryService;
import youda.component.service.ConsumeNoteService;
import youda.component.service.MemberService;
import youda.component.service.NewsService;
import youda.component.service.TeacherService;
import youda.component.util.NumberComputeUtil;

@Path("")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	Invocation inv;
	@Autowired
	AdminService adminService;
	@Autowired
	MemberService memberService;
	@Autowired
	TeacherService teacherService;
	/**
	 * 消费记录服务
	 */
	@Autowired
	ConsumeNoteService consumeNoteService;
	/**
	 * 课程表
	 */
	@Autowired
	NewsService newsService;
	/**
	 * 课程类别
	 */
	@Autowired
	CategoryService categoryService;
	
	/**
	 * 退款操作
	 */
	@Post("user/student/moneyBack")
	public String moneyBack(@Param("studenId")Long studentId,@Param("moneyBackCost")Double moneyBackCost,
			@Param("remark")String remark){
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		/**
		 * 参数校验
		 */
		if(null==studentId){
			return getResJsonBean2JsonView(jsonBean, "缺少学生ID参数", false);
		}
		if(null==moneyBackCost || moneyBackCost.doubleValue() < 0){
			return getResJsonBean2JsonView(jsonBean, "退款金额参数不合法,应为正数", false);
		}
		if(null==remark || "".equals(remark)){
			return getResJsonBean2JsonView(jsonBean, "缺少退款原因参数", false);
		}
		try{
			MemberBase student = this.memberService.findById(studentId);
			student.setBalance(NumberComputeUtil.round(NumberComputeUtil.add(
					student.getBalance(), moneyBackCost), 2));
			this.memberService.updateBalance(student);
			ConsumeNote consumeNote = new ConsumeNote();
			consumeNote.setCost(moneyBackCost.floatValue());
			consumeNote.setStuId(studentId.intValue());
			consumeNote.setRemark(remark);
			this.consumeNoteService.moneyBack(consumeNote);
		}catch(Exception e){
			logger.error(e.getMessage(), e.getCause());
			return getResJsonBean2JsonView(jsonBean, "操作失败", false);
		}
		return getResJsonBean2JsonView(jsonBean, "操作成功", true);
	}
	
	/**
	 * 进入退款页
	 */
	@Get("user/student/moneyBack/{studentId:[0-9]+}")
	public String moneyBack(@Param("studentId")long studentId){
		MemberBase student = this.memberService.findById(studentId);
		inv.addModel("student", student);
		return "user/student/moneyBack";
	}
	
	/**
	 * 通过catId读取课程表信息
	 */
	@Get("user/student/getCourseList/{catId:[0-9]+}")
	public String getCourseList(@Param("catId")int catId){
		ResponseJSONBean<List<CourseView>> json = new ResponseJSONBean<List<CourseView>>();
		json.setBean(this.newsService.getCourseList(catId));
		json.setSuccess(true);
		return "@json:"+BeanJsonUtils.convertToJson(json);
	}
	
	/**
	 * 进入学生消费页
	 */
	@Get("user/student/consume/{studentId:[0-9]+}")
	public String consume(@Param("studentId")long studentId) {
		//初始化课程类型
		inv.addModel("courseCategoryList", categoryService.getCourseCategory());
		//通过学生id查询学生信息
		MemberBase student = this.memberService.findById(studentId);
		inv.addModel("student", student);
//		inv.addModel("userRoleMap", StaticParameters.USER_ROLE);
//		inv.addModel("userRoleKeySet", StaticParameters.USER_ROLE.keySet());
		return "user/student/consume_inc";
	}
	
	/**
	 * 账户消费
	 */
	@Post("user/student/consume")
	public String consume(@Param("consumeCost") float consumeCost,@Param("studentId") Long studentId,
			@Param("catId")Integer catId,@Param("newsId")String newsId,@Param("remark")String remark) {
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		/**
		 * 参数校验
		 */
		if(null==catId){
			return getResJsonBean2JsonView(jsonBean, "缺少择课程类型参数", false);
		}
		if(null==newsId || "".equals(newsId)){
			return getResJsonBean2JsonView(jsonBean, "缺少择课程参数", false);
		}
		if(consumeCost<0){
			return getResJsonBean2JsonView(jsonBean, "消费金额参数不能为负数", false);
		}
		if(null==studentId || "".equals(studentId)){
			return getResJsonBean2JsonView(jsonBean, "缺少学生ID参数", false);
		}
		try{
			MemberBase student = this.memberService.findById(studentId);
			student.setBalance(NumberComputeUtil.round(NumberComputeUtil.sub(
					student.getBalance(), consumeCost), 2));
			this.memberService.updateBalance(student);
			ConsumeNote consumeNote = new ConsumeNote();
			consumeNote.setCost(consumeCost);
			consumeNote.setStuId(studentId.intValue());
			consumeNote.setCatId(catId);
			consumeNote.setNewsId(newsId);
			if(null!=remark && !"".equals(remark)){
				consumeNote.setRemark(remark);
			}
			this.consumeNoteService.consume(consumeNote);
		}catch(Exception e){
			logger.error(e.getMessage(), e.getCause());
			return getResJsonBean2JsonView(jsonBean, "保存失败", false);
		}
		return getResJsonBean2JsonView(jsonBean, "保存成功", true);
	}

	/**
	 * 进入学生充值页
	 */
	@Get("user/student/charge/{studentId:[0-9]+}")
	public String charge(@Param("studentId")long studentId) {
		MemberBase student = this.memberService.findById(studentId);
		inv.addModel("student", student);
		return "user/student/charge_inc";
	}

	/**
	 * 学生充值
	 */
	@Post("user/student/charge")
	public String charge(@Param("chargeCost") float chargeCost,
			@Param("studentId") Long studentId) {
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		/**
		 * 参数校验
		 */
		if(chargeCost<0){
			return getResJsonBean2JsonView(jsonBean, "充值金额不能为负数", false);
		}
		try {
			MemberBase student = this.memberService.findById(studentId);
			student.setBalance(NumberComputeUtil.round(
					NumberComputeUtil.add(student.getBalance(), NumberComputeUtil.mul(chargeCost, ConsumeVariable.CONSUME_RATE)), 2));
			this.memberService.updateBalance(student);
			ConsumeNote consumeNote = new ConsumeNote();
			consumeNote.setCost(chargeCost);
			consumeNote.setStuId(studentId.intValue());
			this.consumeNoteService.charge(consumeNote);
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
			return getResJsonBean2JsonView(jsonBean, "充值失败", false);
		}
		return getResJsonBean2JsonView(jsonBean, "充值成功", true);
	}

	@Post("user/student/edit")
	public String updateStudent(MemberBase paraBean) {
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		jsonBean.setBean(paraBean);
		String checkMsg = checkUpdateStudentInfoParams(jsonBean);
		// 检查有错误信息即返回
		if (!"".equals(checkMsg)) {
			return checkMsg;
		}
		// 添加数据
		this.memberService.updateCompleteMemberInfo(paraBean);
		return getResJsonBean2JsonView(jsonBean, "保存成功", true);
	}

	@Get("user/student/edit/{sid:[0-9]+}")
	public String editStudent(@Param("sid") long sid) {
		MemberBase stuInfo = this.memberService.findById(sid);
		stuInfo.setPersonalDesc(StringUtils.replace(stuInfo.getPersonalDesc(),
				"\n", ""));
		stuInfo.setPersonalDesc(StringUtils.replace(stuInfo.getPersonalDesc(),
				"\r", ""));
		inv.addModel("stuInfo", stuInfo);
		return "user/student/studentEdit";
	}

	@Post("user/student/add")
	public String saveStudent(MemberBase paraBean) {
		ResponseJSONBean<MemberBase> jsonBean = new ResponseJSONBean<MemberBase>();
		jsonBean.setBean(paraBean);
		String checkMsg = checkAddStudentInfoParams(jsonBean);
		// 检查有错误信息即返回
		if (!"".equals(checkMsg)) {
			return checkMsg;
		}
		// 注册时间取当前系统时间
		paraBean.setRegTime(new BigInteger(String.valueOf(System
				.currentTimeMillis())));
		// 添加数据
		try {
			memberService.addStudent(paraBean);
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
			return getResJsonBean2JsonView(jsonBean, "保存失败", false);
		}
		return getResJsonBean2JsonView(jsonBean, "保存成功", true);
	}

	@Get("user/student/add")
	public String addStudent() {
		String errorMSG = inv.getFlash().get("errorMSG");
		if (!StringUtils.isEmpty(errorMSG)) {
			inv.addModel("errorMSG", errorMSG);
		}
		inv.addModel("studyNo", this.memberService.generatorStudyNo());
		return "user/student/studentAdd";
	}

	/**
	 * 删除学生
	 * 
	 * @param sid
	 * @return
	 */
	@Get("user/student/del/{sid:[0-9]+}")
	public String delStudent(@Param("sid") long sid) {
		this.memberService.deleteById(sid);
		return "r:/user/student/list/1";
	}

	/**
	 * 查询学生列表
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Get("user/student/list/{pageIndex:[0-9]+}")
	@Post("user/student/list/{pageIndex:[0-9]+}")
	public String listStudent(@Param("pageIndex") int pageIndex)
			throws UnsupportedEncodingException {
		// inv.getRequest().setCharacterEncoding("utf-8");
		MemberBase param = new MemberBase();
		if (null != inv.getParameter("studyNo")
				&& !"".equals(inv.getParameter("studyNo"))) {
			param.setStudyNo(inv.getParameter("studyNo"));
		}
		if (null != inv.getParameter("nickname")
				&& !"".equals(inv.getParameter("nickname"))) {
			param.setNickname(inv.getParameter("nickname"));
		}
		if (null != inv.getParameter("sex")
				&& !"".equals(inv.getParameter("sex"))) {
			param.setSex(inv.getParameter("sex"));
		}
		SimpleDateFormat dfor = new SimpleDateFormat("yyyy-MM-dd");
		if (null != inv.getParameter("regTimeBegin")
				&& !"".equals(inv.getParameter("regTimeBegin"))) {
			try {
				Date tmpTime = dfor.parse(inv.getParameter("regTimeBegin"));
				param.setRegTimeBegin(new BigInteger(String.valueOf(tmpTime
						.getTime())));
			} catch (ParseException e) {
				e.printStackTrace();
				inv.addModel("errorMSG", "[创建日期起始]请输入正确的日期格式");
				return "user/student/studentList";
			}
		}
		if (null != inv.getParameter("regTimeEnd")
				&& !"".equals(inv.getParameter("regTimeEnd"))) {
			try {
				Date tmpTime = dfor.parse(inv.getParameter("regTimeEnd"));
				param.setRegTimeEnd(new BigInteger(String.valueOf(tmpTime
						.getTime())));
			} catch (ParseException e) {
				e.printStackTrace();
				inv.addModel("errorMSG", "[创建日期截止]请输入正确的日期格式");
				return "user/student/studentList";
			}
		}
		param.setTotalRows(memberService.count4Pagination(param));
		param.setStart(param.countStart(pageIndex, param.getTotalPage()));
		int pi = pageIndex < 1 ? 1 : (pageIndex > param.getTotalPage() ? param
				.getTotalPage() : pageIndex);
		List<MemberBase> stuList = memberService.query4Pagination(param);
		inv.addModel("stuList", stuList);
		inv.addModel("pageInfo", param);
		inv.addModel("pageIndex", pi);
		inv.addModel("regTimeBegin", inv.getParameter("regTimeBegin"));
		inv.addModel("regTimeEnd", inv.getParameter("regTimeEnd"));
		return "user/student/studentList";
	}

	/**
	 * 查询老师列表
	 */
	@Get("user/teacher/list/{pageIndex:[0-9]+}")
	@Post("user/teacher/list/{pageIndex:[0-9]+}")
	public String listTeacher(@Param("pageIndex") int pageIndex) {
		Teacher param = new Teacher();
		if (null != inv.getParameter("educated")
				&& !"".equals(inv.getParameter("educated"))) {
			param.setEducated(inv.getParameter("educated"));
		}
		if (null != inv.getParameter("name")
				&& !"".equals(inv.getParameter("name"))) {
			param.setName(inv.getParameter("name"));
		}
		if (null != inv.getParameter("courseType")
				&& !"".equals(inv.getParameter("courseType"))) {
			param.setCourseType(NumberUtils.toInt(inv
					.getParameter("courseType")));
		}
		if (null != inv.getParameter("sex")
				&& !"".equals(inv.getParameter("sex"))) {
			param.setSex(inv.getParameter("sex"));
		}
		if (null != inv.getParameter("mobilephone")
				&& !"".equals(inv.getParameter("mobilephone"))) {
			param.setMobilephone(inv.getParameter("mobilephone"));
		}
		if (null != inv.getParameter("email")
				&& !"".equals(inv.getParameter("email"))) {
			param.setEmail(inv.getParameter("email"));
		}
		// 设置分页参数
		param.setTotalRows(teacherService.count4Pagination(param));
		param.setStart(param.countStart(pageIndex, param.getTotalPage()));
		int pi = pageIndex < 1 ? 1 : (pageIndex > param.getTotalPage() ? param
				.getTotalPage() : pageIndex);
		// 查询结果
		inv.addModel("teacherList", teacherService.query4Pagination(param));
		inv.addModel("pageInfo", param);
		inv.addModel("pageIndex", pi);
		// 查询条件
		inv.addModel("userRoleMap", StaticParameters.USER_ROLE);
		inv.addModel("userRoleKeySet", StaticParameters.USER_ROLE.keySet());
		return "user/teacher/teacherList";
	}

	/**
	 * 删除老师
	 */
	@Get("user/teacher/del/{tid:[0-9]+}")
	public String deleteTeacher(@Param("tid") int tid) {
		this.teacherService.deleteById(tid);
		inv.addModel("errorMSG", "删除成功!");
		return "r:/user/teacher/list/1";
	}

	/**
	 * 访问添加老师
	 */
	@Get("user/teacher/add")
	public String addTeacher() {
		inv.addModel("userRoleMap", StaticParameters.USER_ROLE);
		inv.addModel("userRoleKeySet", StaticParameters.USER_ROLE.keySet());
		return "user/teacher/teacherAdd";
	}

	/**
	 * 添加老师 提交保存
	 */
	@Post("user/teacher/add")
	public String addTeacher(Teacher bean,
			@Param("photo_url") MultipartFile file) {
		// check 校验参数
		if (!checkTeacherAdd(bean)) {
			return "user/teacher/teacherAdd";
		}
		// 存放照片
		if (null != file && file.getSize() > 0) {
			String prefixDir = "/photo";
			String photoUrl = "/" + System.currentTimeMillis()
					+ file.getOriginalFilename();
			String path = inv.getServletContext().getRealPath(prefixDir)
					+ photoUrl;
			bean.setPhotoUrl(prefixDir + photoUrl);
			try {
				FileUtils.writeByteArrayToFile(new File(path), file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// bean.setHomeland(inv.getParameter("province")+","+inv.getParameter("city"));
		teacherService.addTeacher(bean);
		inv.addModel("addFlag", true);
		return "user/teacher/teacherAdd";
	}

	/**
	 * 进入编辑老师视图
	 */
	@Get("user/teacher/edit/{tid:[0-9]+}")
	public String editTeacher(@Param("tid") int tid) {
		Teacher teacher = this.teacherService.findById(tid);
		inv.addModel("teacherInfo", teacher);
		inv.addModel("userRoleMap", StaticParameters.USER_ROLE);
		inv.addModel("userRoleKeySet", StaticParameters.USER_ROLE.keySet());
		return "user/teacher/teacherEdit";
	}

	/**
	 * 编辑老师
	 */
	@Post("user/teacher/edit/{tid:[0-9]+}")
	public String editTeacher(Teacher bean, @Param("tid") int tid,
			@Param("photo_url") MultipartFile file) {
		// check 校验参数
		if (!checkTeacherAdd(bean)) {
			return "user/teacher/teacherAdd";
		}
		bean.setId(tid);
		// 存放照片
		if (null != file && file.getSize() > 0) {
			String prefixDir = "/photo";
			String photoUrl = "/"
					+ System.currentTimeMillis()
					+ file.getOriginalFilename().substring(
							file.getOriginalFilename().lastIndexOf("."));
			String path = inv.getServletContext().getRealPath(prefixDir)
					+ photoUrl;
			bean.setPhotoUrl(prefixDir + photoUrl);
			try {
				FileUtils.writeByteArrayToFile(new File(path), file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// bean.setHomeland(inv.getParameter("province")+","+inv.getParameter("city"));
		teacherService.updateTeacherInfo(bean);
		inv.addModel("addFlag", true);
		return "user/teacher/teacherEdit";
	}

	/**
	 * 展示所有课程类型
	 */
	@Get("user/admin/listCourse")
	public String listCourse() {
		inv.addModel("userRoleMap", StaticParameters.USER_ROLE);
		inv.addModel("userRoleKeySet", StaticParameters.USER_ROLE.keySet());
		return "user/list-course";
	}

	@Get("user/admin/manage")
	public String adminManage() {
		List<Admin> adminList = adminService.getUserList();
		inv.addModel("adminList", adminList);
		return "user/admin-manage";
	}

	@Get("user/admin/add")
	public String addAdmin() {
		String errorMSG = inv.getFlash().get("errorMSG");
		if (!StringUtils.isEmpty(errorMSG)) {
			inv.addModel("errorMSG", errorMSG);
		}
		inv.addModel("userRoleMap", StaticParameters.USER_ROLE);
		inv.addModel("userRoleKeySet", StaticParameters.USER_ROLE.keySet());
		return "user/admin-add";
	}

	@Get("user/admin/edit/{uid:[0-9]+}")
	public String editAdmin(@Param("uid") int userid) {
		Admin user = adminService.getUserById(userid);
		inv.addModel("user", user);

		String errorMSG = inv.getFlash().get("errorMSG");
		if (!StringUtils.isEmpty(errorMSG)) {
			inv.addModel("errorMSG", errorMSG);
		}
		inv.addModel("userRoleMap", StaticParameters.USER_ROLE);
		inv.addModel("userRoleKeySet", StaticParameters.USER_ROLE.keySet());
		return "user/admin-edit";
	}

	@Get("user/admin/delete/{uid:[0-9]+}")
	public String delAdmin(@Param("uid") int userid) {
		// TODO 查看用户权限
		adminService.delUser(userid);
		return "r:/user/admin/manage";
	}

	@Post("user/admin/add")
	public String addAdminUser(@Param("username") String username,
			@Param("password") String password,
			@Param("repassword") String repassword,
			@Param("email") String email, @Param("realname") String realname,
			@Param("roleid") int roleid) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			inv.addFlash("errorMSG", "必须输入用户名和密码。");
			return "r:/user/admin/add";
		}

		if (adminService.checkUserName(username) > 0) {
			inv.addFlash("errorMSG", "用户名已存在。");
			return "r:/user/admin/add";
		}

		if (!password.equals(repassword)) {
			inv.addFlash("errorMSG", "两次输入的密码不正确。");
			return "r:/user/admin/add";
		}

		username = AntiSpamUtils.safeText(username);
		email = AntiSpamUtils.safeText(email);
		realname = AntiSpamUtils.safeText(realname);
		adminService.addUser(username, password, roleid, email, realname);

		return "r:/user/admin/manage";
	}

	@Post("user/admin/edit")
	public String editAdminUser(@Param("userid") int userid,
			@Param("username") String username,
			@Param("password") String password,
			@Param("repassword") String repassword,
			@Param("email") String email, @Param("realname") String realname,
			@Param("roleid") int roleid) {
		Admin user = adminService.getUserById(userid);

		if (StringUtils.isEmpty(username)) {
			inv.addFlash("errorMSG", "必须输入用户名。");
			return "r:/user/admin/edit/" + userid;
		}

		if (!user.getUsername().equals(username)) {
			if (adminService.checkUserName(username) > 0) {
				inv.addFlash("errorMSG", "用户名已存在。");
				return "r:/user/admin/edit/" + userid;
			}
			user.setUsername(AntiSpamUtils.safeText(username));
		}

		if (!StringUtils.isEmpty(password)) {
			if (!password.equals(repassword)) {
				inv.addFlash("errorMSG", "两次输入的密码不正确。");
				return "r:/user/admin/edit/" + userid;
			}
			user.setPassword(DigestUtils.md5Hex(password));
		}

		user.setEmail(AntiSpamUtils.safeText(email));
		user.setRealname(AntiSpamUtils.safeText(realname));
		user.setRoleid(roleid);
		adminService.updateUser(user);

		return "r:/user/admin/manage";
	}

	// private methods
	/**
	 * 添加老师 校验通过true,否则false
	 */
	private boolean checkTeacherAdd(Teacher bean) {
		if (null == bean.getName() || "".equals(bean.getName())) {
			inv.addModel("errorMSG", "请输入姓名!");
			return false;
		}
		if (null == bean.getAge() || "".equals(bean.getAge())) {
			inv.addModel("errorMSG", "请输入年龄!");
			return false;
		}
		if (null == bean.getEducated() || "".equals(bean.getEducated())) {
			inv.addModel("errorMSG", "请输入学历!");
			return false;
		}
		if (null == bean.getCourseType()) {
			inv.addModel("errorMSG", "请选择课程类型!");
			return false;
		}
		if (null == bean.getSex() || "".equals(bean.getSex())) {
			inv.addModel("errorMSG", "请选性别!");
			return false;
		}
		if (null == inv.getParameter("province")
				|| "".equals(inv.getParameter("province"))
				|| null == inv.getParameter("city")
				|| "".equals(inv.getParameter("city"))) {
			inv.addModel("errorMSG", "请选所在地!");
			return false;
		}
		if (null == bean.getMobilephone() || "".equals(bean.getMobilephone())) {
			inv.addModel("errorMSG", "请选入手机!");
			return false;
		}
		if (null == bean.getEmail() || "".equals(bean.getEmail())) {
			inv.addModel("errorMSG", "请选入邮箱地址!");
			return false;
		}
		return true;
	}

	/**
	 * 修改的校验
	 */
	private String checkUpdateStudentInfoParams(
			ResponseJSONBean<MemberBase> jsonBean) {
		MemberBase bean = jsonBean.getBean();
		if (null == bean) {
			return getResJsonBean2JsonView(jsonBean, "未接收到完善资料参数!", false);
		}
		if (null == bean.getId() || "".equals(bean.getId())) {
			return getResJsonBean2JsonView(jsonBean, "请重选择学生信息进行修改!", false);
		}
		if (null == bean.getSex() || "".equals(bean.getSex())) {
			return getResJsonBean2JsonView(jsonBean, "性别不能为空!", false);
		}
		if (null == bean.getHomeland() || "".equals(bean.getHomeland())) {
			return getResJsonBean2JsonView(jsonBean, "所在地不能为空!", false);
		}
		if (null == bean.getBirthday() || "".equals(bean.getBirthday())) {
			return getResJsonBean2JsonView(jsonBean, "出生日期不能为空!", false);
		}
		return "";
	}

	/**
	 * 校验添加学生
	 */
	private String checkAddStudentInfoParams(
			ResponseJSONBean<MemberBase> jsonBean) {
		MemberBase bean = jsonBean.getBean();
		if (null == bean) {
			return getResJsonBean2JsonView(jsonBean, "未接收到完善资料参数!", false);
		}
		if (null == bean.getNickname() || "".equals(bean.getNickname())) {
			return getResJsonBean2JsonView(jsonBean, "姓名不能为空!", false);
		}
		if (null == bean.getPwd() || "".equals(bean.getPwd())) {
			return getResJsonBean2JsonView(jsonBean, "密码不能为空!", false);
		}
		if (null == bean.getEmail() || "".equals(bean.getEmail())) {
			return getResJsonBean2JsonView(jsonBean, "电子邮箱不能为空!", false);
		}
		if (null == bean.getSex() || "".equals(bean.getSex())) {
			return getResJsonBean2JsonView(jsonBean, "性别不能为空!", false);
		}
		if (null == bean.getHomeland() || "".equals(bean.getHomeland())) {
			return getResJsonBean2JsonView(jsonBean, "所在地不能为空!", false);
		}
		if (null == bean.getBirthday() || "".equals(bean.getBirthday())) {
			return getResJsonBean2JsonView(jsonBean, "出生日期不能为空!", false);
		}
		return "";
	}

	/**
	 * 将ResponseJSONBean转成jsonstring
	 */
	private String getResJsonBean2JsonView(
			ResponseJSONBean<MemberBase> jsonBean, String message,
			boolean success) {
		jsonBean.setMessage(message);
		jsonBean.setSuccess(success);
		return "@json:" + BeanJsonUtils.convertToJson(jsonBean);
	}

}
