package youda.component.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.dao.NewsDAO;
import youda.component.model.CourseView;
import youda.component.model.News;
import youda.component.service.AttachmentService;
import youda.component.service.NewsService;
import youda.component.type.NewsStatus;

@Component
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsDAO newsDao;
	@Autowired
	AttachmentService attachmentService;
	
	@Override
	public News getNewsById(String newsId) {
		return newsDao.getNewsById(newsId);
	}

	@Override
	public List<News> getNewsListByCategoryId(int catId, int status, String orderBy, int page, int limit) {
		List<News> rtn = new ArrayList<News>();
		if(limit <= 0){
			rtn = newsDao.getNewsListByCategoryId_PT(catId, status);
		}
		else{
			page = (page <= 0 ? 1 : page);
			int index = (int)Math.ceil((page - 1) * 1.0 / limit);
			rtn = newsDao.getNewsListByCategoryId_PT(catId, status, index, limit);
		}
		for(News news : rtn){
			news.setThumbnail(attachmentService.getNewsThumbnail(news.getNewsId()));
			news.setPicAttachmentList(attachmentService.getPicAttachmentListByNewsId(news.getNewsId()));
			news.setAttachmentList(attachmentService.getOtherAttachmentListByNewsId(news.getNewsId()));
		}
		return rtn;
	}

	@Override
	public List<News> getAllNewsListByCategoryId(int catId, String orderBy) {
		return newsDao.getAllNewsListByCategoryId(catId, orderBy);
	}

	@Override
	public String newsSave(News news) {
		String newsId = this.generateNewsId();
		news.setNewsId(newsId);
		newsDao.newsSave(news);
		return newsId;
	}

	@Override
	public void updateNews(News news) {
		newsDao.updateNews(news);
	}

	@Override
	public List<News> getAllNewsListByPage(int editorId, int page, int size) {
		List<News> rtn = new ArrayList<News>();
		page = (page <= 0 ? 1 : page);
		int index = (page - 1) * size;
		rtn = newsDao.getAllNewsListByPage(editorId, index, size);
		return rtn;
	}

	@Override
	public int getNewsListCountByUser(int editorId) {
		return newsDao.countNewsListByUser(editorId);
	}
	
	
	
	@Override
	public List<News> getPicNewsListByCategoryId(int catId, int status,	String orderBy, int page, int limit) {
		List<News> rtn = new ArrayList<News>();
		if(limit <= 0){
			rtn = newsDao.getTemplateNewsListByCatId_PT(catId, status, "IMAGE");
		}
		else{
			page = (page <= 0 ? 1 : page);
			int index = (int)Math.ceil((page - 1) * 1.0 / limit);
			rtn = newsDao.getTemplateNewsListByCatId_PT(catId, status, "IMAGE", index, limit);
		}
		
		for(News news : rtn){
			news.setThumbnail(attachmentService.getNewsThumbnail(news.getNewsId()));
			news.setPicAttachmentList(attachmentService.getPicAttachmentListByNewsId(news.getNewsId()));
			news.setAttachmentList(attachmentService.getOtherAttachmentListByNewsId(news.getNewsId()));
		}
		return rtn;
	}

	@Override
	public String generateNewsId(){
		Date currentTime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String newsId = format.format(currentTime) + RandomStringUtils.randomAlphanumeric(3);
		if(newsDao.checkNewsId(newsId) > 0){
			return generateNewsId();
		}
		return newsId;
	}

	@Override
	public List<News> getNeed2ApproveNewsList(int catId, int page, int limit) {
		page = (page <= 0 ? 1 : page);
		int index = (page - 1) * limit;
		if(catId <= 0){
			return newsDao.getNewsListByStatus_ASC(NewsStatus.WAIT_TO_APPROVE, index, limit);
		}
		
		return newsDao.getNewsListByStatusAndCategory_ASC(catId, NewsStatus.WAIT_TO_APPROVE, index, limit);
	}

	@Override
	public int countNeed2ApproveNewsList(int catId) {
		if(catId <= 0){
			return newsDao.countNewsListByStatus(NewsStatus.WAIT_TO_APPROVE);
		}
		return newsDao.countNewsListByStatusAndCategory(catId, NewsStatus.WAIT_TO_APPROVE);
	}

	@Override
	public List<News> getPublishNewsList(int catId, int page, int limit) {
		page = (page <= 0 ? 1 : page);
		int index = (page - 1) * limit;
		if(catId <= 0){
			return newsDao.getPublishNewsList_DESC(NewsStatus.PUBLISHED, index, limit);
		}
		
		return newsDao.getPublishNewsListByCategory_DESC(catId, NewsStatus.PUBLISHED, index, limit);
	}

	@Override
	public int countPublishNewsList(int catId) {
		if(catId <= 0){
			return newsDao.countNewsListByStatus(NewsStatus.PUBLISHED);
		}
		return newsDao.countNewsListByStatusAndCategory(catId, NewsStatus.PUBLISHED);
	}

	@Override
	public void delNews(String newsId) {
		newsDao.delNews(newsId);
		attachmentService.removeAttachmentByNewsId(newsId);
	}

	@Override
	public List<News> getNeed2ApproveNewsSearchList(String newsId,
			String newsTitle, int catId, int editor, int page, int limit) {
		int searchNewsId = (StringUtils.isEmpty(newsId)?0:1);
		int searchNewsTitle = (StringUtils.isEmpty(newsTitle)?0:1);
		int index = (page - 1) * limit;
		newsTitle = "%" + newsTitle + "%";
		return newsDao.getNewsSearchListByStatus(searchNewsId, newsId, searchNewsTitle, newsTitle, catId, editor, NewsStatus.WAIT_TO_APPROVE, index, limit,"update_time", 0);
	}

	@Override
	public int countNewsSearchListByStatus(String newsId, String newsTitle,
			int catId, int editor, int status) {
		int searchNewsId = (StringUtils.isEmpty(newsId)?0:1);
		int searchNewsTitle = (StringUtils.isEmpty(newsTitle)?0:1);
		newsTitle = "%" + newsTitle + "%";
		return newsDao.countNewsSearchListByStatus(searchNewsId, newsId, searchNewsTitle, newsTitle, catId, editor, status);
	}

	@Override
	public List<News> getNewsSearchListByStatus(String newsId,
			String newsTitle, int catId, int editor, int status, int page,
			int limit, String orderBy, int desc) {
		int searchNewsId = (StringUtils.isEmpty(newsId)?0:1);
		int searchNewsTitle = (StringUtils.isEmpty(newsTitle)?0:1);
		int index = (page - 1) * limit;
		newsTitle = "%" + newsTitle + "%";
		return newsDao.getNewsSearchListByStatus(searchNewsId, newsId, searchNewsTitle, newsTitle, catId, editor, status, index, limit, orderBy, desc);
	}
	
	/**
	 * 读取课程表列表
	 */
	public List<CourseView> getCourseList(int catId){
		return this.newsDao.getCourseListByCatId(catId);
	}
}
