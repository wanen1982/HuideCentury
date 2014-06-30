package youda.component.service;

import java.util.List;

import youda.component.model.MemberBase;

public interface MemberService {
	/**
	 * 添加学生信息
	 */
	public int addStudent(MemberBase bean);
	
	/**
	 * 根据id删除一条学生信息
	 * @param id
	 * @return
	 */
	public int deleteById(Long id);
	/**
	 * 统计记录总数
	 */
	public int count4Pagination(MemberBase bean);
	
	/**
	 * 会员(学生)分页查询返回列表
	 */
	public List<MemberBase> query4Pagination(MemberBase bean);

	public abstract MemberBase findById(Long id);

	public abstract void save(String email, String nickname, String pwd);
	
	/**
	 * 读取登录用户的信息
	 * @param nickname
	 * @param pwd
	 * @return MemberBase
	 */
	public MemberBase getLoginMember(String nickname,String pwd);
	
	/**
	 * 是否存在已经注册的电子邮箱地址
	 * @param email
	 * @return
	 */
	public boolean existsRegisterEmail(String email);
	
	/**
	 * 是否存在已经注册的用户名
	 * @param nickname
	 * @return
	 */
	public boolean existsRegisterNickname(String nickname);
	
	/**
	 * 用户完善资料
	 * @param bean
	 */
	public void updateCompleteMemberInfo(MemberBase bean);
	
	/**
	 * 修改学生余额
	 * @param bean
	 */
	public void updateBalance(MemberBase bean);
	
	/**
	 * 产生学号
	 * @return studyNo
	 */
	public String generatorStudyNo();

}