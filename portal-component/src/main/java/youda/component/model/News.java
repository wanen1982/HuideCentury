package youda.component.model;

import java.util.Date;
import java.util.List;

public class News extends AbstractPagination {

	private static final long serialVersionUID = -8894592131328985288L;
	
	private String newsId;
	
	private int catId;
	
	private String catName;
	
	private String title;
	
	private String source;
	
	private String content;
	
	private int editorId;
	
	private String editorName;
	
	private int status;
	
	private String template;
	
	private int isTop;
	
	private int listOrder;
	
	private int attachment;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int updateBy;
	
	private Date publishTime;
	
	private int publishBy;
	
	private String beginTime;//'上课时间,格式hh:mi;用于表示该课程的开始时间',
	private String endTime;//'下课时间,格式hh:mi;用于表示该课程的结束时间',
	private Integer  weekIndex;//'用整数表示星期几(0~6)',
	private Float  defaultCost;//'课程默认费用,每节不同类型课程的标准价格',
	private Integer  courseType;//'课程的类型，对应老师的课程类型',
	private Integer  teaId;//'本节课程的授课老师ID',
	
	private Attachment thumbnail;
	
	private List<Attachment> picAttachmentList;
	
	private List<Attachment> attachmentList;
	
	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getEditorId() {
		return editorId;
	}

	public void setEditorId(int editorId) {
		this.editorId = editorId;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public int getAttachment() {
		return attachment;
	}

	public void setAttachment(int attachment) {
		this.attachment = attachment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public int getPublishBy() {
		return publishBy;
	}

	public void setPublishBy(int publishBy) {
		this.publishBy = publishBy;
	}

	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the weekIndex
	 */
	public Integer getWeekIndex() {
		return weekIndex;
	}

	/**
	 * @param weekIndex the weekIndex to set
	 */
	public void setWeekIndex(Integer weekIndex) {
		this.weekIndex = weekIndex;
	}

	/**
	 * @return the defaultCost
	 */
	public Float getDefaultCost() {
		return defaultCost;
	}

	/**
	 * @param defaultCost the defaultCost to set
	 */
	public void setDefaultCost(Float defaultCost) {
		this.defaultCost = defaultCost;
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
	 * @return the teaId
	 */
	public Integer getTeaId() {
		return teaId;
	}

	/**
	 * @param teaId the teaId to set
	 */
	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}

	public Attachment getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Attachment thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<Attachment> getPicAttachmentList() {
		return picAttachmentList;
	}

	public void setPicAttachmentList(List<Attachment> picAttachmentList) {
		this.picAttachmentList = picAttachmentList;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
}
