package youda.component.service;

import java.util.List;

import youda.component.model.Teacher;

public interface TeacherService {

	/**
	 * 统计查询的记录总数 
	 */
	public abstract int count4Pagination(Teacher bean);

	/**
	 * 查询分页的老师信息
	 */
	public abstract List<Teacher> query4Pagination(Teacher bean);

	/**
	 *  根据主键查询一条老师信息
	 */
	public abstract Teacher findById(Integer id);

	/**
	 * 通过email查询老师信息
	 */
	public abstract Teacher findByEmail(String email);

	/**
	 * 通过主键删除一条老师的信息
	 */
	public abstract int deleteById(Integer id);

	/**
	 * 添加一条老师信息
	 */
	public abstract int addTeacher(Teacher bean);

	/**
	 * 修改一条老师信息
	 */
	public abstract int updateTeacherInfo(Teacher bean);

	/**
	 * 通过课程类型查老师
	 * @param courseType
	 * @return   List<Teacher>
	 */
	public List<Teacher> findByCourseType(Integer courseType);
}