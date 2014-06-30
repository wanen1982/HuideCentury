package youda.component.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.dao.MemberDAO;
import youda.component.model.MemberBase;
import youda.component.service.MemberService;

@Component
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;
	
	/**
	 * 添加学生信息
	 */
	public int addStudent(MemberBase bean){
		bean.setPwd(DigestUtils.md5Hex(bean.getPwd()));
		return this.dao.addStudent(bean);
	}
	
	/**
	 * 根据id删除一条学生信息
	 * @param id
	 * @return
	 */
	public int deleteById(Long id){
		return this.dao.deleteById(id);
	}
	
	/**
	 * 统计记录总数
	 */
	public int count4Pagination(MemberBase bean){
		return this.dao.count4Pagination(bean);
	}
	/**
	 * 会员(学生)分页查询返回列表
	 */
	public List<MemberBase> query4Pagination(MemberBase bean){
		if(null == bean.getStart()) bean.setStart(0);
		if(null == bean.getRows()) bean.setRows(20);
		List<MemberBase> queryList = this.dao.query4Pagination(bean);
		SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
		for(MemberBase item : queryList){
			Date tmpDate = new Date(item.getRegTime().longValue());
			item.setRegTimeShow(dateFor.format(tmpDate));
		}
		return queryList;
	}
	
	/* (non-Javadoc)
	 * @see youda.component.service.impl.MemberService#findById(java.lang.Long)
	 */
	@Override
	public MemberBase findById(Long id){
		if(null==id){
			throw new RuntimeException("查询条件id为null");
		}
		return dao.findById(id);
	}
	
	/* (non-Javadoc)
	 * @see youda.component.service.impl.MemberService#save(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void save(String email,String nickname,String pwd){
		if(null==email || "".equals(email)){
			throw new RuntimeException("email参数为null");
		}
		if(null==nickname || "".equals(nickname)){
			throw new RuntimeException("nickname参数为null");
		}
		if(null==pwd || "".equals(pwd)){
			throw new RuntimeException("pwd参数为null");
		}
		MemberBase bean = new MemberBase();
		bean.setEmail(email);
		bean.setNickname(nickname);
		bean.setPwd(DigestUtils.md5Hex(pwd));
		dao.addMemberBase(bean);
	}
	
	/**
	 * 读取登录用户的信息
	 * @param nickname
	 * @param pwd
	 * @return MemberBase
	 */
	@Override
	public MemberBase getLoginMember(String nickname,String pwd){
		//需要md5加密后再查询
		return dao.getLoginMember(nickname, DigestUtils.md5Hex(pwd));
	}
	
	/**
	 * 是否存在已经注册的电子邮箱地址
	 * @param email
	 * @return
	 */
	public boolean existsRegisterEmail(String email){
		List<MemberBase> memBaseList = dao.findByEmail(email);
		if(null==memBaseList || memBaseList.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * 是否存在已经注册的用户名
	 * @param nickname
	 * @return
	 */
	public boolean existsRegisterNickname(String nickname){
		List<MemberBase> memBaseList = dao.findByNickname(nickname);
		if(null==memBaseList || memBaseList.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * 用户完善资料
	 * @param bean
	 */
	public void updateCompleteMemberInfo(MemberBase bean){
		this.dao.updateMemberBaseInfo(bean);
	}
	
	/**
	 * 修改学生余额
	 * @param bean
	 */
	public void updateBalance(MemberBase bean){
		this.dao.updateBalance(bean);
	}
	
	/**
	 * 产生学号
	 * @return studyNo
	 */
	public String generatorStudyNo() {
		int studyNo = this.dao.getStudyNo();
		String strStudyNo = "00000"+studyNo;
		return strStudyNo.substring(strStudyNo.length()-5);
	}
}
