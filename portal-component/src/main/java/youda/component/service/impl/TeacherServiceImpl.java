package youda.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.dao.TeacherDAO;
import youda.component.model.Teacher;
import youda.component.service.TeacherService;

/**
 * 老师服务类
 * @author we
 */
@Component
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherDAO dao;
	
	/**
	 * 统计查询的记录总数 
	 */
	@Override
	public int count4Pagination(Teacher bean){
		return this.dao.count4Pagination(bean);
	}
	
	/**
	 * 查询分页的老师信息
	 */
	@Override
	public List<Teacher> query4Pagination(Teacher bean){
		return this.dao.query4Pagination(bean);
	}
	
	/**
	 *  根据主键查询一条老师信息
	 */
	@Override
	public Teacher findById(Integer id){
		return this.dao.findById(id);
	}
	
	/**
	 * 通过email查询老师信息
	 */
	@Override
	public Teacher findByEmail(String email){
		return this.dao.findByEmail(email);
	}
	
	/**
	 * 通过主键删除一条老师的信息
	 */
	@Override
	public int deleteById(Integer id){
		return this.dao.deleteById(id);
	}
	
	/**
	 * 添加一条老师信息
	 */
	@Override
	public int addTeacher(Teacher bean){
		return this.dao.addTeacher(bean);
	}
	
	/**
	 * 修改一条老师信息
	 */
	@Override
	public int updateTeacherInfo(Teacher bean){
		return this.dao.updateTeacherInfo(bean);
	}
	
	/**
	 * 通过课程类型查老师
	 * @param courseType
	 * @return   List<Teacher>
	 */
	public List<Teacher> findByCourseType(Integer courseType){
		return this.dao.findByCourseType(courseType);
	}
}
