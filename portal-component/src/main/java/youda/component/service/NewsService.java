package youda.component.service;

import java.util.List;

import youda.component.model.CourseView;
import youda.component.model.News;

public interface NewsService {

	public String generateNewsId();
	
	public News getNewsById(String newsId);
	
	public List<News> getNewsListByCategoryId(int catId, int status, String orderBy, int page, int limit);

	public List<News> getPicNewsListByCategoryId(int catId, int status, String orderBy, int page, int limit);
	
	public String newsSave(News news);
	
	public void updateNews(News news);
	
	public int getNewsListCountByUser(int editorId);
	
	/********* Admin 管理后台专用方法 ***********/
	public void delNews(String newsId);
	
	public List<News> getAllNewsListByCategoryId(int catId, String orderBy);
	
	public List<News> getAllNewsListByPage(int editorId, int page, int size);
	
	public List<News> getNeed2ApproveNewsList(int catId, int page, int limit);
	
	public int countNeed2ApproveNewsList(int catId);
	
	public List<News> getPublishNewsList(int catId, int page, int limit);
	
	public int countPublishNewsList(int catId);
	
	public List<News> getNeed2ApproveNewsSearchList(String newsId, String newsTitle, int catId, int editor, int page, int limit);
	
	public int countNewsSearchListByStatus(String newsId, String newsTitle, int catId, int editor, int status);
	
	public List<News> getNewsSearchListByStatus(String newsId, String newsTitle, int catId, int editor, int status, int page, int limit,String orderBy, int desc);
	
	/**
	 * 通过category id读取课程表列表
	 */
	public List<CourseView> getCourseList(int catId);
}
