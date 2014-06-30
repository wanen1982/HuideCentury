package youda.component.dao;

import java.util.List;

import youda.component.model.Teacher;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

@DAO
public interface TeacherDAO {
	static final String FIELDS_NAME = "id , name, age, educated, course_type, personal_desc, photo_url, sex , homeland , mobilephone , email , hobby,is_delete";
	static final String TABLE_NAME = "teacher";
	
	/**
	 * 分页查询,只查询有效的老师(未逻辑删除的)
	 */
	@SQL("select "+FIELDS_NAME + " from "+ TABLE_NAME +" where is_delete = 0 "
			+ "#if(:1.id !=null){ and id=:1.id} #if(:1.name !=null){ and name like concat('%',:1.name,'%')}"
			+ "#if(:1.age !=null){ and age=:1.age} #if(:1.educated !=null){ and educated like concat('%',:1.educated,'%')}"
			+ "#if(:1.courseType !=null){ and course_type=:1.courseType}"
			+ "#if(:1.sex !=null){ and sex=:1.sex}"
			+ "#if(:1.homeland !=null){ and homeland=:1.homeland}"
			+ "#if(:1.mobilephone !=null){ and mobilephone like concat('%',:1.mobilephone,'%')}"
			+ "#if(:1.email !=null){ and email like concat('%',:1.email,'%')}"
			+ " order by id desc limit :1.start,:1.rows")
	public List<Teacher> query4Pagination(Teacher teacher);
	
	/**
	 * 统计记录总数,只统计有效的老师(未逻辑删除的)
	 */
	@SQL("select count(1) from "+ TABLE_NAME +" where is_delete = 0 "
			+ "#if(:1.id !=null){ and id=:1.id} #if(:1.name !=null){ and name like concat('%',:1.name,'%')}"
			+ "#if(:1.age !=null){ and age=:1.age} #if(:1.educated !=null){ and educated like concat('%',:1.educated,'%')}"
			+ "#if(:1.courseType !=null){ and course_type=:1.courseType}"
			+ "#if(:1.sex !=null){ and sex=:1.sex}"
			+ "#if(:1.homeland !=null){ and homeland=:1.homeland}"
			+ "#if(:1.mobilephone !=null){ and mobilephone like concat('%',:1.mobilephone,'%')}"
			+ "#if(:1.email !=null){ and email like concat('%',:1.email,'%')}")
	public int count4Pagination(Teacher teacher);

	/**
	 * 根据id读一条老师信息
	 * @param id
	 * @return
	 */
	@SQL("select "+FIELDS_NAME+" from "+TABLE_NAME+" where id=:1")
	public Teacher findById(Integer id);
	
	/**
	 * 通过email读取用户信息
	 */
	@SQL("SELECT "+FIELDS_NAME+" FROM "+TABLE_NAME+" a WHERE a.email=:1")
	public Teacher findByEmail(String email);
	
	/**
	 *根据id删除一条记录 
	 *逻辑删除老师is_delete=1
	 */
	@SQL("update "+TABLE_NAME+" set is_delete=1 where id=:1")
	public int deleteById(Integer id);
	
	/**
	 * 添加老师记录,返回生成的主键信息
	 */
	@ReturnGeneratedKeys
	@SQL("insert into "+TABLE_NAME+"(name, age, educated, course_type,sex , homeland , mobilephone , email"
			+ "#if(:1.personalDesc !=null){,personal_desc}"
			+ "#if(:1.photoUrl !=null){,photo_url}"
			+ "#if(:1.hobby !=null){,hobby}"
			+ ") values(:1.name,:1.age,:1.educated,:1.courseType,:1.sex,:1.homeland,:1.mobilephone,:1.email"
			+ "#if(:1.personalDesc !=null){,:1.personalDesc}"
			+ "#if(:1.photoUrl !=null){,:1.photoUrl}"
			+ "#if(:1.hobby !=null){,:1.hobby}"
			+ ")")
	public int addTeacher(Teacher bean);
	
	/**
	 * 修改老师信息
	 */
	@SQL("update "+TABLE_NAME+" set name=:1.name,age=:1.age,educated=:1.educated,course_type=:1.courseType"
			+ ",sex=:1.sex, homeland=:1.homeland, mobilephone=:1.mobilephone,email=:1.email"
			+ "#if(:1.personalDesc !=null){ ,personal_desc=:1.personalDesc}"
			+ "#if(:1.photoUrl !=null){ ,photo_url=:1.photoUrl}"
			+ "#if(:1.hobby !=null){ ,hobby=:1.hobby}"
			+ " where id=:1.id")
	public int updateTeacherInfo(Teacher bean);
	
	/**
	 * 通过课程类型查老师
	 * @param courseType
	 * @return   List<Teacher>
	 */
	@SQL("select id,name,sex from "+TABLE_NAME+" where is_delete=0 and course_type =:1")
	public List<Teacher> findByCourseType(Integer courseType); 
}
