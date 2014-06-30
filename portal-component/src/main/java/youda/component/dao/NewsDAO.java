package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.CourseView;
import youda.component.model.News;

@DAO
public interface NewsDAO {

	final static String news = "news_id,cat_id,cat_name,title,source,content,editor_id,editor_name,status,template," +
			"is_top,list_order,attachment,create_time,update_time,update_by,publish_time,publish_by,begin_time,end_time,week_index,default_cost,course_type,tea_id";
	
	@SQL("select " + news + " from news where news_id = :1")
	public News getNewsById(String newsId);
	
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 and is_top = 1 order by :3 desc")
	public List<News> getNewsOrderListByCategoryId(int catId, int status, String orderBy);
	
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 order by list_order desc,publish_time desc")
	public List<News> getNewsListByCategoryId_PT(int catId, int status);
	
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 order by list_order desc,publish_time desc limit :3,:4")
	public List<News> getNewsListByCategoryId_PT(int catId, int status, int index, int limit);
	
	@SQL("select " + news + " from news where cat_id = :1 order by :2 desc")
	public List<News> getAllNewsListByCategoryId(int catId, String orderBy);
	
	@SQL("insert into news(news_id,"+
			"cat_id,cat_name,title,source,content," +
			"editor_id,editor_name,`status`,template," +
			"is_top,list_order,attachment," +
			"create_time,update_time,update_by,publish_time,publish_by"
			+ "#if(:1.beginTime !=null){,begin_time}"
			+ "#if(:1.endTime !=null){,end_time}"
			+ "#if(:1.weekIndex !=null){,week_index}"
			+ "#if(:1.defaultCost !=null){,default_cost}"
			+ "#if(:1.courseType!=null){,course_type}"
			+ "#if(:1.teaId !=null){,tea_id})" +
			"values (:1.newsId,:1.catId,:1.catName,:1.title,:1.source,:1.content," +
			":1.editorId,:1.editorName,:1.status,:1.template,"+
			":1.isTop,:1.listOrder,:1.attachment,"+
			":1.createTime,:1.updateTime,:1.updateBy ,:1.publishTime,:1.publishBy"
			+ "#if(:1.beginTime !=null){,:1.beginTime}"
			+ "#if(:1.endTime !=null){,:1.endTime}"
			+ "#if(:1.weekIndex !=null){,:1.weekIndex}"
			+ "#if(:1.defaultCost !=null){,:1.defaultCost}"
			+ "#if(:1.courseType!=null){,:1.courseType}"
			+ "#if(:1.teaId !=null){,:1.teaId})")
	public void newsSave(News news);
	
	@SQL("update news set cat_id=:1.catId,cat_name=:1.catName,title=:1.title,source=:1.source,content=:1.content," +
			"editor_id=:1.editorId,editor_name=:1.editorName,status=:1.status,template=:1.template,is_top=:1.isTop,list_order=:1.listOrder," +
			"attachment=:1.attachment,create_time=:1.createTime,update_time=now(),update_by=:1.updateBy,publish_time=:1.publishTime," +
			"publish_by=:1.publishBy "
			+ "#if(:1.beginTime !=null){,begin_time=:1.beginTime}"
			+ "#if(:1.endTime !=null){,end_time=:1.endTime}"
			+ "#if(:1.weekIndex !=null){,week_index=:1.weekIndex}"
			+ "#if(:1.defaultCost !=null){,default_cost=:1.defaultCost}"
			+ "#if(:1.courseType!=null){,course_type=:1.courseType}"
			+ "#if(:1.teaId !=null){,tea_id=:1.teaId} "
			+ "where news_id=:1.newsId")
	public void updateNews(News news);
	
	@SQL("select " + news + " from news where editor_id = :1 order by update_time desc limit :2,:3 ")
	public List<News> getAllNewsListByPage(int editorId, int index, int size);
	
	@SQL("select count(*) from news where editor_id = :1")
	public int countNewsListByUser(int editorId);
	
	@SQL("select count(*) from news where news_id = :1")
	public int checkNewsId(String newsId);
	
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 and template=:3 order by list_order desc,publish_time desc")
	public List<News> getTemplateNewsListByCatId_PT(int catId, int status, String template);
	
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 and template=:3 order by list_order desc,publish_time desc limit :4,:5")
	public List<News> getTemplateNewsListByCatId_PT(int catId, int status, String template, int index, int limit);
	
	@SQL("select " + news + " from news where status = :1 order by update_time limit :2,:3")
	public List<News> getNewsListByStatus_ASC(int status, int index, int limit);
	
	//Order by update_time
	@SQL("select " + news + " from news where status = :1 order by update_time desc limit :2,:3")
	public List<News> getNewsListByStatus_DESC(int status, int index, int limit);
	
	//Update by publish_time
	@SQL("select " + news + " from news where status = :1 order by list_order desc, publish_time desc limit :2,:3")
	public List<News> getPublishNewsList_DESC(int status, int index, int limit);
	
	@SQL("select count(*) from news where status = :1")
	public int countNewsListByStatus(int status);
	
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 order by update_time limit :3,:4")
	public List<News> getNewsListByStatusAndCategory_ASC(int catId, int status, int index, int limit);
	
	//Order by update_time
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 order by update_time desc limit :3,:4")
	public List<News> getNewsListByStatusAndCategory_DESC(int catId, int status, int index, int limit);
	
	//Update by publish_time
	@SQL("select " + news + " from news where cat_id = :1 and status = :2 order by list_order desc,publish_time desc limit :3,:4")
	public List<News> getPublishNewsListByCategory_DESC(int catId, int status, int index, int limit);
	
	@SQL("select count(*) from news where cat_id = :1 and status = :2")
	public int countNewsListByStatusAndCategory(int catId, int status);
	
	@SQL("delete from news where news_id = :1")
	public void delNews(String newsId);
	
	@SQL("select count(*) from news where #if(:1>0){news_id = :2 and } #if(:3>0){title like :4 and } #if(:5>0){cat_id=:5 and } #if(:6>0){editor_id=:6 and } #if(:7>0){status=:7 and} cat_id > 0")
	public int countNewsSearchListByStatus(int searchNewsId, String newsId, int searchNewsTitle, String newsTitle, int catId, int editor, int status);
	
	@SQL("select " + news + " from news where #if(:1>0){news_id = :2 and } #if(:3>0){title like :4 and } #if(:5>0){cat_id=:5 and } #if(:6>0){editor_id=:6 and } #if(:7>0){status=:7 and } cat_id > 0 order by #if(:11 > 0){:10 desc} #if(:11 == 0){:10} limit :8,:9")
	public List<News> getNewsSearchListByStatus(int searchNewsId, String newsId, int searchNewsTitle, String newsTitle, int catId, int editor, int status, int index, int limit, String orderBy, int desc);
	
	/**
	 * 读取课程表信息
	 */
	@SQL("SELECT a.news_id,a.cat_id,a.cat_name,a.title,a.source,a.begin_time,a.end_time,"
			+ "a.week_index,a.default_cost,a.course_type,a.tea_id,b.name tea_name"
			+ " FROM news a LEFT JOIN teacher b ON a.tea_id=b.id "
			+ "where a.cat_id=:1")
//			"select "+news+" from news where cat_id=:1"
	public List<CourseView> getCourseListByCatId(int catId);
}
