package youda.component.model;


/**
 * 老师实体类
 * @author we
 *
 */
public class Teacher extends AbstractPagination{
	private static final long serialVersionUID = 3611443877573230842L;
	
	private Integer id;//主键,自动增长
	private String  name;//姓名
	private Integer age;//年龄
	private String educated;//学历
	private Integer courseType;//课程类型
	private String  personalDesc;//描述
	private String photoUrl;//照片
	private String sex;//性别
	private String homeland;//籍贯
	private String mobilephone;//手机
	private String email;//电子邮箱
	private String hobby;//爱好 
	private Integer isDelete = Integer.valueOf(0);//删除标记,0未删除,非0表示删除
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return the educated
	 */
	public String getEducated() {
		return educated;
	}
	/**
	 * @param educated the educated to set
	 */
	public void setEducated(String educated) {
		this.educated = educated;
	}
	/**
	 * @return the courseType
	 */
	public Integer getCourseType() {
		return courseType;
	}
	/**
	 * @param courseType the courseType to set
	 */
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
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
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}
	/**
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
	 * @return the hobby
	 */
	public String getHobby() {
		return hobby;
	}
	/**
	 * @param hobby the hobby to set
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	/**
	 * @return the isDelete
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
