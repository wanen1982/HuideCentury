package youda.component.dao;

import java.util.List;

import youda.component.model.MemberBase;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

@DAO
public interface MemberDAO {
	static final String SQL_FIELDS="id,email,nickname,pwd,sex,homeland,birthday,personal_desc,reg_time,balance,mobilephone,address,study_no,student_id";
	static final String SQL_TABLE="member_base";
	
	/**
	 *根据id删除一条记录 
	 * @param id
	 * @return
	 */
	@SQL("delete from "+SQL_TABLE+" where id=:1")
	public int deleteById(Long id);
	
	@SQL("select "+SQL_FIELDS+" from "+SQL_TABLE+" where id=:1")
	public MemberBase findById(Long id);
	
	@ReturnGeneratedKeys
	@SQL("INSERT INTO "+SQL_TABLE+"(email,nickname,pwd,reg_time) VALUES(:1.email,:1.nickname,:1.pwd,:1.regTime)")
	public int addMemberBase(MemberBase bean);
	
	/**
	 * 添加学生
	 * @param bean
	 * @return
	 */
	@ReturnGeneratedKeys
	@SQL("INSERT INTO "+SQL_TABLE+"(email,nickname,pwd,sex,homeland,birthday,personal_desc,reg_time,study_no"
			+ "#if(:1.balance !=null){,balance}"
			+ "#if(:1.mobilephone !=null){,mobilephone}"
			+ "#if(:1.address !=null){,address}"
			+ "#if(:1.studentId !=null){,student_id}"
			+ ") "
			+ "values(:1.email,:1.nickname,:1.pwd,:1.sex,:1.homeland,:1.birthday,:1.personalDesc,:1.regTime,:1.studyNo"
			+ "#if(:1.balance !=null){,:1.balance}"
			+ "#if(:1.mobilephone !=null){,:1.mobilephone}"
			+ "#if(:1.address !=null){,:1.address}"
			+ "#if(:1.studentId !=null){,:1.studentId}"
			+ ")")
	public int addStudent(MemberBase bean);
	
	/**
	 * 会员(学生)分页查询返回列表
	 */
	@SQL("select "+SQL_FIELDS + " from "+ SQL_TABLE +" where 1=1 "
			+ "#if(:1.studyNo !=null){ and study_no like concat('%',:1.studyNo,'%')} "
			+ " #if(:1.email!=null){ and email like concat('%',:1.email,'%')}"
			+"#if(:1.nickname != null){ and nickname like concat('%',:1.nickname,'%')}"
			+"#if(:1.sex !=null){ and sex=:1.sex}"
			+"#if(:1.homeland !=null){ and homeland like concat('%',:1.homeland,'%')}"
			+"#if(:1.birthday !=null){ and birthday=:1.birthday}"
			+"#if(:1.regTimeBegin !=null){ and reg_time >= :1.regTimeBegin}"
			+ "#if(:1.regTimeEnd !=null){ and reg_time <= :1.regTimeEnd}"
			+" order by id desc limit :1.start,:1.rows")
	public List<MemberBase> query4Pagination(MemberBase bean);
	
	/**
	 * 统计计录总数
	 */
	@SQL("select count(1) from "+ SQL_TABLE +" where 1=1 "
			+ "#if(:1.studyNo !=null){ and study_no like concat('%',:1.studyNo,'%')} "
			+ "#if(:1.email!=null){ and email like concat('%',:1.email,'%')}"
			+"#if(:1.nickname != null){ and nickname like concat('%',:1.nickname,'%')}"
			+"#if(:1.sex !=null){ and sex=:1.sex}"
			+"#if(:1.homeland !=null){ and homeland like concat('%',:1.homeland,'%')}"
			+"#if(:1.birthday !=null){ and birthday=:1.birthday}"
			+"#if(:1.regTimeBegin !=null){ and reg_time >= :1.regTimeBegin}"
			+ "#if(:1.regTimeEnd !=null){ and reg_time <= :1.regTimeEnd}")
	public int count4Pagination(MemberBase bean);
	
	/**
	 * 查询登录用户信息
	 */
	@SQL("select "+SQL_FIELDS +" from "+SQL_TABLE+" where nickname=:1 and pwd=:2")
	public MemberBase getLoginMember(String nickname,String pwd);
	
	/**
	 * 通过email读取用户信息
	 */
	@SQL("SELECT "+SQL_FIELDS+" FROM "+SQL_TABLE+" a WHERE a.email=:1")
	public List<MemberBase> findByEmail(String email);
	
	/**
	 * 通过用户名查询用户信息
	 */
	@SQL("SELECT "+SQL_FIELDS+" FROM "+SQL_TABLE+" a WHERE a.nickname=:1")
	public List<MemberBase> findByNickname(String nickname);
	
	/**
	 * 用户通过该方法进行资料完善
	 * @param bean
	 */
	@SQL("update "+SQL_TABLE+" set sex=:1.sex"
			+ " #if(:1.homeland != null){ ,homeland=:1.homeland}"
			+ " #if(:1.birthday !=null){ ,birthday=:1.birthday}"
			+ " #if(:1.personalDesc!=null){ ,personal_desc=:1.personalDesc}"
			+ "#if(:1.balance !=null){,balance=:1.balance}"
			+ "#if(:1.mobilephone !=null){,mobilephone=:1.mobilephone}"
			+ "#if(:1.address !=null){,address=:1.address}"
			+ "#if(:1.studentId !=null){,student_id=:1.studentId}"
			+ " where id=:1.id")
	public void updateMemberBaseInfo(MemberBase bean);
	
	/**
	 * 修改余额
	 * @param bean
	 */
	@SQL("update "+SQL_TABLE +" set balance=:1.balance where id=:1.id")
	public void updateBalance(MemberBase bean);
	
	/**
	 * 获取学号
	 */
	@SQL("SELECT MAX(IFNULL(study_no,99))+1 AS study_no FROM member_base where study_no is not null")
	public int getStudyNo();
}
