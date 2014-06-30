package youda.component.model;

import java.math.BigInteger;

/**
 * 会员基本信息
 * @author we
 *
 */
public class MemberBase extends AbstractPagination{
	private static final long serialVersionUID = 3726388022552072797L;
	
	private Long id;
	private String email;
	private String nickname;
	private String pwd;
	private String sex;//性别
	private String homeland;//家乡所在地
	private String birthday;//出生日期
	private String personalDesc;//个人简介
	private BigInteger regTime;//注册时间
	private Double balance;//帐户余额
	private String mobilephone;//手机
	private String address;//家庭地址
	private String studyNo;//学号
	private String studentId;//学生ID,允许手动填写和修改
	
	/**
	 * 查询条件
	 */
	private BigInteger regTimeBegin,regTimeEnd;
	private String regTimeShow;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the homeland
	 */
	public String getHomeland() {
		return homeland;
	}
	/**
	 * @param homeland the homeland to set
	 */
	public void setHomeland(String homeland) {
		this.homeland = homeland;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the personalDesc
	 */
	public String getPersonalDesc() {
		return personalDesc;
	}
	/**
	 * @param personalDesc the personalDesc to set
	 */
	public void setPersonalDesc(String personalDesc) {
		this.personalDesc = personalDesc;
	}
	/**
	 * @return the regTime
	 */
	public BigInteger getRegTime() {
		return regTime;
	}
	/**
	 * @param regTime the regTime to set
	 */
	public void setRegTime(BigInteger regTime) {
		this.regTime = regTime;
	}
	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	/**
	 * @return the regTimeBegin
	 */
	public BigInteger getRegTimeBegin() {
		return regTimeBegin;
	}
	/**
	 * @param regTimeBegin the regTimeBegin to set
	 */
	public void setRegTimeBegin(BigInteger regTimeBegin) {
		this.regTimeBegin = regTimeBegin;
	}
	/**
	 * @return the regTimeEnd
	 */
	public BigInteger getRegTimeEnd() {
		return regTimeEnd;
	}
	/**
	 * @param regTimeEnd the regTimeEnd to set
	 */
	public void setRegTimeEnd(BigInteger regTimeEnd) {
		this.regTimeEnd = regTimeEnd;
	}
	/**
	 * @return the regTimeShow
	 */
	public String getRegTimeShow() {
		return regTimeShow;
	}
	/**
	 * @param regTimeShow the regTimeShow to set
	 */
	public void setRegTimeShow(String regTimeShow) {
		this.regTimeShow = regTimeShow;
	}
	/**
	 * @return the mobilephone
	 */
	public String getMobilephone() {
		return mobilephone;
	}
	/**
	 * @param mobilephone the mobilephone to set
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the studyNo
	 */
	public String getStudyNo() {
		return studyNo;
	}
	/**
	 * @param studyNo the studyNo to set
	 */
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
}
