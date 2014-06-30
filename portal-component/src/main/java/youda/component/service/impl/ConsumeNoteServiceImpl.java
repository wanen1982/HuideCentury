package youda.component.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.constants.ConsumeVariable;
import youda.component.dao.ConsumeNoteDAO;
import youda.component.model.ConsumeNote;
import youda.component.model.ConsumeNoteView;
import youda.component.service.ConsumeNoteService;
import youda.component.util.ExcelUtil;
import youda.component.util.NumberComputeUtil;

@Component
public class ConsumeNoteServiceImpl implements ConsumeNoteService {

	/**
	 * 消费记录dao
	 */
	@Autowired
	ConsumeNoteDAO dao;
	
	/**
	 * 对view查询结果集中的数据进行整理
	 * @param list
	 */
	private void _processListOfConsumeNoteView(List<ConsumeNoteView> list){
		SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(ConsumeNoteView bean : list){
			bean.setShowCreateTime(dateFor.format(new Date(bean.getCreateTime())));
			bean.setPreviousCost((float)NumberComputeUtil.div(bean.getCost(), ConsumeVariable.CONSUME_RATE, 2));
			if(ConsumeVariable.CHARGE.equals(bean.getType()))
				bean.setTypeName("充值");
			else if(ConsumeVariable.CONSUME.equals(bean.getType()))
				bean.setTypeName("消费");
			else
				bean.setTypeName("退款");
		}
	}
	
	/**
	 * 生成excel文件
	 * @param model
	 * @return 生成的文件路径
	 */
	public String createExcelFile(ConsumeNoteView model){
		//充值
		model.setStart(0);
		model.setType(ConsumeVariable.CHARGE);
		model.setRows(dao.count4PaginationOfView(model));
		List<ConsumeNoteView> chargeNoteList = dao.query4PaginationOfView(model);
		_processListOfConsumeNoteView(chargeNoteList);
		//消费
		model.setType(ConsumeVariable.CONSUME);
		model.setRows(dao.count4PaginationOfView(model));
		List<ConsumeNoteView> consumeNoteList = dao.query4PaginationOfView(model);
		_processListOfConsumeNoteView(consumeNoteList);
		//退款
		model.setType(ConsumeVariable.MONEY_BACK);
		model.setRows(dao.count4PaginationOfView(model));
		List<ConsumeNoteView> moneyBackNoteList = dao.query4PaginationOfView(model);
		_processListOfConsumeNoteView(moneyBackNoteList);
		//构建文件路径
		File xlsFile = new File(System.currentTimeMillis()+".xls");
		Map<String,List<ConsumeNoteView>> datamap = new HashMap<String, List<ConsumeNoteView>>();
		//组织excel数据
		datamap.put("充值", chargeNoteList);
		datamap.put("消费", consumeNoteList);
		datamap.put("退款", moneyBackNoteList);
		ExcelUtil.exportXls(xlsFile.getAbsolutePath(), ConsumeNoteView.getExcelTitle(), datamap);
		return xlsFile.getAbsolutePath();
	}
	
	/**
	 * 分页查询消费明细
	 */
	public List<ConsumeNoteView> query4PaginationOfView(ConsumeNoteView model){
		if(null == model.getStart()) model.setStart(0);
		if(null == model.getRows()) model.setRows(20);
		List<ConsumeNoteView> list = dao.query4PaginationOfView(model);
		_processListOfConsumeNoteView(list);
		return list;
	}
	
	/**
	 * 统计消费明细多表分页查询的记录总数
	 */
	public int count4PaginationOfView(ConsumeNoteView model){
		return dao.count4PaginationOfView(model);
	}
	
	/**
	 * 统计分页查询结果集的记录总数
	 * @param model
	 * @return
	 */
	@Override
	public int count4Pagination(ConsumeNote model){
		return dao.count4Pagination(model);
	}
	
	/**
	 * 分页查询结果集
	 * @param model
	 * @return
	 */
	@Override
	public List<ConsumeNote> query4Pagination(ConsumeNote model){
		if(null == model.getStart()) model.setStart(0);
		if(null == model.getRows()) model.setRows(20);
		SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
		List<ConsumeNote> list = dao.query4Pagination(model);
		for(ConsumeNote bean : list){
			bean.setShowCreateTime(dateFor.format(new Date(bean.getCreateTime())));
			bean.setPreviousCost((float)NumberComputeUtil.div(bean.getCost(), ConsumeVariable.CONSUME_RATE, 2));
		}
		return list;
	}
	
	/**
	 * 帐户充值
	 */
	public ConsumeNote charge(ConsumeNote param){
		param.setCreateTime(System.currentTimeMillis());
		float srcCost = param.getCost();
		param.setCost((float)NumberComputeUtil.round( NumberComputeUtil.mul(srcCost, ConsumeVariable.CONSUME_RATE),2));
		param.setRemark(String.format("充值原金额[%s],充值优惠[%s],实际充值金额[%s]", 
				srcCost,ConsumeVariable.CONSUME_RATE,param.getCost() ));
		param.setType(ConsumeVariable.CHARGE);
		//执行充值记帐并保存主键信息
		param.setId(this.dao.addConsumeNote(param));
		return param;
	}
	
	/**
	 * 帐户消费
	 */
	public ConsumeNote consume(ConsumeNote param){
		param.setCreateTime(System.currentTimeMillis());
		param.setType(ConsumeVariable.CONSUME);
		param.setId(this.dao.addConsumeNote(param));
		return param;
	}
	
	/**
	 * 帐户退款
	 */
	public ConsumeNote moneyBack(ConsumeNote param){
		param.setCreateTime(System.currentTimeMillis());
		param.setType(ConsumeVariable.MONEY_BACK);
		param.setId(this.dao.addConsumeNote(param));
		return param;
	}
	
}
