package youda.component.dao;

import java.util.List;

import youda.component.model.ConsumeNote;
import youda.component.model.ConsumeNoteView;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

/**
 * 充值、消费和退款记录
 * @author we
 *
 */
@DAO
public interface ConsumeNoteDAO {
	/**查询的字段名*/
	static final String FIELDS_NAME = "id,`type`, cat_id, stu_id, cost, create_time, news_id, remark";
	/**查询表名*/
	static final String TABLE_NAME = "consume_note";
	
	/**
	 * 分页查询消费记录
	 * @param model
	 * @return
	 */
	@SQL("select "+FIELDS_NAME +" from "+TABLE_NAME +" where 1=1 "
			+ "#if(:1.id !=null){ and id=:1.id}"
			+ "#if(:1.type !=null){ and `type`=:1.type}"
			+ "#if(:1.catId !=null){ and cat_id=:1.catId}"
			+ "#if(:1.stuId !=null){ and stu_id=:1.stuId}"
			+ "#if(:1.cost !=null){ and cost=:1.cost}"
			+ "#if(:1.createTimeBegin !=null){ and create_time >=:1.createTimeBegin}"
			+ "#if(:1.createTimeEnd !=null){ and create_time <=:1.createTimeEnd}"
			+ "#if(:1.newsId !=null){ and news_id = :1.newsId}"
			+ " order by id desc limit :1.start,:1.rows")
	public List<ConsumeNote> query4Pagination(ConsumeNote model);
	
	/**
	 * 统计分页查询消费记录
	 * @param model
	 * @return
	 */
	@SQL("select count(1) from "+TABLE_NAME +" where 1=1 "
			+ "#if(:1.id !=null){ and id=:1.id}"
			+ "#if(:1.type !=null){ and `type`=:1.type}"
			+ "#if(:1.catId !=null){ and cat_id=:1.catId}"
			+ "#if(:1.stuId !=null){ and stu_id=:1.stuId}"
			+ "#if(:1.cost !=null){ and cost=:1.cost}"
			+ "#if(:1.createTimeBegin !=null){ and create_time >=:1.createTimeBegin}"
			+ "#if(:1.createTimeEnd !=null){ and create_time <=:1.createTimeEnd}"
			+ "#if(:1.newsId !=null){ and news_id = :1.newsId}")
	public int count4Pagination(ConsumeNote model);
	
	/**
	 * 多表查询消费明细带分页功能
	 */
	@SQL("SELECT a.id , a.`TYPE`,a.cat_id,a.stu_id,a.cost,a.create_time,a.news_id,a.remark,b.cat_name,"
			+ "c.nickname AS student_name,b.title AS course_Name "
			+ " FROM consume_note a LEFT JOIN news b ON a.news_id = b.news_id LEFT JOIN member_base c ON a.stu_id = c.id "
			+ " where 1=1 "
			+ "#if(:1.id !=null){ and a.id=:1.id}"
			+ "#if(:1.type !=null){ and a.`type`=:1.type}"
			+ "#if(:1.catId !=null){ and a.cat_id=:1.catId}"
			+ "#if(:1.stuId !=null){ and a.stu_id=:1.stuId}"
			+ "#if(:1.cost !=null){ and a.cost=:1.cost}"
			+ "#if(:1.createTimeBegin !=null){ and a.create_time >=:1.createTimeBegin}"
			+ "#if(:1.createTimeEnd !=null){ and a.create_time <=:1.createTimeEnd}"
			+ "#if(:1.newsId !=null){ and a.news_id = :1.newsId}"
			+ " order by id desc limit :1.start,:1.rows")
	public List<ConsumeNoteView> query4PaginationOfView(ConsumeNoteView model);
	
	/**
	 * 统计消费明细多表分页查询的记录总数
	 * @param model
	 * @return
	 */
	@SQL("SELECT count(1) "
			+ " FROM consume_note a LEFT JOIN news b ON a.news_id = b.news_id LEFT JOIN member_base c ON a.stu_id = c.id "
			+ " where 1=1 "
			+ "#if(:1.id !=null){ and a.id=:1.id}"
			+ "#if(:1.type !=null){ and a.`type`=:1.type}"
			+ "#if(:1.catId !=null){ and a.cat_id=:1.catId}"
			+ "#if(:1.stuId !=null){ and a.stu_id=:1.stuId}"
			+ "#if(:1.cost !=null){ and a.cost=:1.cost}"
			+ "#if(:1.createTimeBegin !=null){ and a.create_time >=:1.createTimeBegin}"
			+ "#if(:1.createTimeEnd !=null){ and a.create_time <=:1.createTimeEnd}"
			+ "#if(:1.newsId !=null){ and a.news_id = :1.newsId}")
	public int count4PaginationOfView(ConsumeNoteView model);
	
	/**
	 * 添加消费充值记录，返回刚添加的主键信息
	 * @param model
	 * @return
	 */
	@ReturnGeneratedKeys
	@SQL("insert into "+TABLE_NAME +"(type,stu_id,cost,create_time"
			+ "#if(:1.catId !=null){,cat_id}"
			+ "#if(:1.newsId !=null){,news_id}"
			+ "#if(:1.remark !=null){,remark}"
			+ ") values(:1.type,:1.stuId,:1.cost,:1.createTime"
			+ "#if(:1.catId !=null){,:1.catId}"
			+ "#if(:1.newsId !=null){,:1.newsId}"
			+ "#if(:1.remark !=null){,:1.remark}"
			+ ")")
	public int addConsumeNote(ConsumeNote model);
}
