package youda.component.service;

import java.util.List;

import youda.component.model.ConsumeNote;
import youda.component.model.ConsumeNoteView;

/**
 * 消费服务接口
 * @author we
 *
 */
public interface ConsumeNoteService {
	/**
	 * 生成excel文件
	 * @param model
	 * @return 生成的文件路径
	 */
	public String createExcelFile(ConsumeNoteView model);
	
	/**
	 * 分页查询消费明细
	 */
	public List<ConsumeNoteView> query4PaginationOfView(ConsumeNoteView model);
	
	/**
	 * 统计消费明细多表分页查询的记录总数
	 */
	public int count4PaginationOfView(ConsumeNoteView model);

	/**
	 * 统计分页查询结果集的记录总数
	 * @param model
	 * @return
	 */
	public abstract int count4Pagination(ConsumeNote model);

	/**
	 * 分页查询结果集
	 * @param model
	 * @return
	 */
	public abstract List<ConsumeNote> query4Pagination(ConsumeNote model);
	
	/**
	 * 帐户充值
	 * @param param
	 * @return
	 */
	public ConsumeNote charge(ConsumeNote param);
	
	/**
	 * 帐户消费
	 */
	public ConsumeNote consume(ConsumeNote param);
	
	/**
	 * 帐户退款
	 */
	public ConsumeNote moneyBack(ConsumeNote param);

}