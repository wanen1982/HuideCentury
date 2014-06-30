package youda.component.model;

/**
 * 充值，消费，退款明细查询视图
 * @author we
 *
 */
public class ConsumeNoteView extends ConsumeNote{

	private static final long serialVersionUID = -6619544507573855977L;
	private String typeName;//类型名称
	private String catName;//课程类型
	private String studentName;//学生名称
	private String courseName;//课程名称
	
	/**
	 * 获取导出excel的表头
	 * @return
	 */
	public static final String[] getExcelTitle(){
		return new String[]{"ID","消费类型","课程类型","课程","学生姓名","发生额","发生时间","备注"};
	}
	
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
