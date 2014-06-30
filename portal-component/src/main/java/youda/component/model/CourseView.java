package youda.component.model;

import java.util.Date;
import java.util.List;

/**
 *  课程查询视图
 * @author we
 *
 */
public class CourseView {
	private News news;
	private String teaName;//老师名称
	
	public CourseView(){
		super();
		news = new News();
	}
	
	/**
	 * @return
	 * @see youda.component.model.AbstractPagination#getStart()
	 */
	public Integer getStart() {
		return news.getStart();
	}

	/**
	 * @param start
	 * @see youda.component.model.AbstractPagination#setStart(java.lang.Integer)
	 */
	public void setStart(Integer start) {
		news.setStart(start);
	}

	/**
	 * @return
	 * @see youda.component.model.AbstractPagination#getRows()
	 */
	public Integer getRows() {
		return news.getRows();
	}

	/**
	 * @param rows
	 * @see youda.component.model.AbstractPagination#setRows(java.lang.Integer)
	 */
	public void setRows(Integer rows) {
		news.setRows(rows);
	}

	/**
	 * @return
	 * @see youda.component.model.AbstractPagination#getTotalRows()
	 */
	public Integer getTotalRows() {
		return news.getTotalRows();
	}

	/**
	 * @param totalRows
	 * @see youda.component.model.AbstractPagination#setTotalRows(java.lang.Integer)
	 */
	public void setTotalRows(Integer totalRows) {
		news.setTotalRows(totalRows);
	}

	/**
	 * @return
	 * @see youda.component.model.AbstractPagination#getTotalPage()
	 */
	public int getTotalPage() {
		return news.getTotalPage();
	}

	/**
	 * @param indexPage
	 * @param totalPage
	 * @return
	 * @see youda.component.model.AbstractPagination#countStart(int, int)
	 */
	public int countStart(int indexPage, int totalPage) {
		return news.countStart(indexPage, totalPage);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getNewsId()
	 */
	public String getNewsId() {
		return news.getNewsId();
	}

	/**
	 * @param newsId
	 * @see youda.component.model.News#setNewsId(java.lang.String)
	 */
	public void setNewsId(String newsId) {
		news.setNewsId(newsId);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getCatId()
	 */
	public int getCatId() {
		return news.getCatId();
	}

	/**
	 * @param catId
	 * @see youda.component.model.News#setCatId(int)
	 */
	public void setCatId(int catId) {
		news.setCatId(catId);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getCatName()
	 */
	public String getCatName() {
		return news.getCatName();
	}

	/**
	 * @param catName
	 * @see youda.component.model.News#setCatName(java.lang.String)
	 */
	public void setCatName(String catName) {
		news.setCatName(catName);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getTitle()
	 */
	public String getTitle() {
		return news.getTitle();
	}

	/**
	 * @param title
	 * @see youda.component.model.News#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		news.setTitle(title);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getSource()
	 */
	public String getSource() {
		return news.getSource();
	}

	/**
	 * @param source
	 * @see youda.component.model.News#setSource(java.lang.String)
	 */
	public void setSource(String source) {
		news.setSource(source);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getContent()
	 */
	public String getContent() {
		return news.getContent();
	}

	/**
	 * @param content
	 * @see youda.component.model.News#setContent(java.lang.String)
	 */
	public void setContent(String content) {
		news.setContent(content);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getEditorId()
	 */
	public int getEditorId() {
		return news.getEditorId();
	}

	/**
	 * @param editorId
	 * @see youda.component.model.News#setEditorId(int)
	 */
	public void setEditorId(int editorId) {
		news.setEditorId(editorId);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getEditorName()
	 */
	public String getEditorName() {
		return news.getEditorName();
	}

	/**
	 * @param editorName
	 * @see youda.component.model.News#setEditorName(java.lang.String)
	 */
	public void setEditorName(String editorName) {
		news.setEditorName(editorName);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getStatus()
	 */
	public int getStatus() {
		return news.getStatus();
	}

	/**
	 * @param status
	 * @see youda.component.model.News#setStatus(int)
	 */
	public void setStatus(int status) {
		news.setStatus(status);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getTemplate()
	 */
	public String getTemplate() {
		return news.getTemplate();
	}

	/**
	 * @param template
	 * @see youda.component.model.News#setTemplate(java.lang.String)
	 */
	public void setTemplate(String template) {
		news.setTemplate(template);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getIsTop()
	 */
	public int getIsTop() {
		return news.getIsTop();
	}

	/**
	 * @param isTop
	 * @see youda.component.model.News#setIsTop(int)
	 */
	public void setIsTop(int isTop) {
		news.setIsTop(isTop);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getListOrder()
	 */
	public int getListOrder() {
		return news.getListOrder();
	}

	/**
	 * @param listOrder
	 * @see youda.component.model.News#setListOrder(int)
	 */
	public void setListOrder(int listOrder) {
		news.setListOrder(listOrder);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getAttachment()
	 */
	public int getAttachment() {
		return news.getAttachment();
	}

	/**
	 * @param attachment
	 * @see youda.component.model.News#setAttachment(int)
	 */
	public void setAttachment(int attachment) {
		news.setAttachment(attachment);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getCreateTime()
	 */
	public Date getCreateTime() {
		return news.getCreateTime();
	}

	/**
	 * @param createTime
	 * @see youda.component.model.News#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date createTime) {
		news.setCreateTime(createTime);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getUpdateTime()
	 */
	public Date getUpdateTime() {
		return news.getUpdateTime();
	}

	/**
	 * @param updateTime
	 * @see youda.component.model.News#setUpdateTime(java.util.Date)
	 */
	public void setUpdateTime(Date updateTime) {
		news.setUpdateTime(updateTime);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getUpdateBy()
	 */
	public int getUpdateBy() {
		return news.getUpdateBy();
	}

	/**
	 * @param updateBy
	 * @see youda.component.model.News#setUpdateBy(int)
	 */
	public void setUpdateBy(int updateBy) {
		news.setUpdateBy(updateBy);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getPublishTime()
	 */
	public Date getPublishTime() {
		return news.getPublishTime();
	}

	/**
	 * @param publishTime
	 * @see youda.component.model.News#setPublishTime(java.util.Date)
	 */
	public void setPublishTime(Date publishTime) {
		news.setPublishTime(publishTime);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getPublishBy()
	 */
	public int getPublishBy() {
		return news.getPublishBy();
	}

	/**
	 * @param publishBy
	 * @see youda.component.model.News#setPublishBy(int)
	 */
	public void setPublishBy(int publishBy) {
		news.setPublishBy(publishBy);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getBeginTime()
	 */
	public String getBeginTime() {
		return news.getBeginTime();
	}

	/**
	 * @param beginTime
	 * @see youda.component.model.News#setBeginTime(java.lang.String)
	 */
	public void setBeginTime(String beginTime) {
		news.setBeginTime(beginTime);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getEndTime()
	 */
	public String getEndTime() {
		return news.getEndTime();
	}

	/**
	 * @param endTime
	 * @see youda.component.model.News#setEndTime(java.lang.String)
	 */
	public void setEndTime(String endTime) {
		news.setEndTime(endTime);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getWeekIndex()
	 */
	public Integer getWeekIndex() {
		return news.getWeekIndex();
	}

	/**
	 * @param weekIndex
	 * @see youda.component.model.News#setWeekIndex(java.lang.Integer)
	 */
	public void setWeekIndex(Integer weekIndex) {
		news.setWeekIndex(weekIndex);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getDefaultCost()
	 */
	public Float getDefaultCost() {
		return news.getDefaultCost();
	}

	/**
	 * @param defaultCost
	 * @see youda.component.model.News#setDefaultCost(java.lang.Float)
	 */
	public void setDefaultCost(Float defaultCost) {
		news.setDefaultCost(defaultCost);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getCourseType()
	 */
	public Integer getCourseType() {
		return news.getCourseType();
	}

	/**
	 * @param courseType
	 * @see youda.component.model.News#setCourseType(java.lang.Integer)
	 */
	public void setCourseType(Integer courseType) {
		news.setCourseType(courseType);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getTeaId()
	 */
	public Integer getTeaId() {
		return news.getTeaId();
	}

	/**
	 * @param teaId
	 * @see youda.component.model.News#setTeaId(java.lang.Integer)
	 */
	public void setTeaId(Integer teaId) {
		news.setTeaId(teaId);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getThumbnail()
	 */
	public Attachment getThumbnail() {
		return news.getThumbnail();
	}

	/**
	 * @param thumbnail
	 * @see youda.component.model.News#setThumbnail(youda.component.model.Attachment)
	 */
	public void setThumbnail(Attachment thumbnail) {
		news.setThumbnail(thumbnail);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getPicAttachmentList()
	 */
	public List<Attachment> getPicAttachmentList() {
		return news.getPicAttachmentList();
	}

	/**
	 * @param picAttachmentList
	 * @see youda.component.model.News#setPicAttachmentList(java.util.List)
	 */
	public void setPicAttachmentList(List<Attachment> picAttachmentList) {
		news.setPicAttachmentList(picAttachmentList);
	}

	/**
	 * @return
	 * @see youda.component.model.News#getAttachmentList()
	 */
	public List<Attachment> getAttachmentList() {
		return news.getAttachmentList();
	}

	/**
	 * @param attachmentList
	 * @see youda.component.model.News#setAttachmentList(java.util.List)
	 */
	public void setAttachmentList(List<Attachment> attachmentList) {
		news.setAttachmentList(attachmentList);
	}

	/**
	 * @return the teaName
	 */
	public String getTeaName() {
		return teaName;
	}

	/**
	 * @param teaName the teaName to set
	 */
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

}
