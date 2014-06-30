package youda.admin.web.controllers;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import youda.component.constants.ConsumeVariable;
import youda.component.model.ConsumeNoteView;
import youda.component.service.ConsumeNoteService;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

@Path("")
public class ConsumeNoteController {
	private static Logger logger = Logger.getLogger(ConsumeNoteController.class);
	@Autowired
	Invocation inv;
	
	/**
	 * 消费记录服务
	 */
	@Autowired
	ConsumeNoteService consumeNoteService;
	
	/**
	 * 通过课程id查询该节课程的消费记录
	 */
	@Get("consume/note/queryByNewsid/{newsId}/{pageIndex:[0-9]+}")
	@Post("consume/note/queryByNewsid/{newsId}/{pageIndex:[0-9]+}")
	public String queryByNewsid(@Param("newsId")String newsId,@Param("pageIndex")int pageIndex){
		ConsumeNoteView param = new ConsumeNoteView();
		if(null !=newsId && !"".equals(newsId)){
			param.setNewsId(newsId);
		}else{
			return "@newsId参数缺失，无法查询";
		}
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strCreateTimeBegin = inv.getParameter("createTimeBegin");
		if(null !=strCreateTimeBegin && !"".equals(strCreateTimeBegin)){
			try {
				param.setCreateTimeBegin(dataFormat.parse(strCreateTimeBegin).getTime());
				inv.addModel("createTimeBegin", strCreateTimeBegin);
			} catch (ParseException e) {
				logger.error(e,e.getCause());
			}
		}
		String strCreateTimeEnd = inv.getParameter("createTimeEnd");
		if(null !=strCreateTimeEnd && !"".equals(strCreateTimeEnd)){
			try {
				param.setCreateTimeEnd(dataFormat.parse(strCreateTimeEnd).getTime());
				inv.addModel("createTimeEnd", strCreateTimeEnd);
			} catch (ParseException e) {
				logger.error(e,e.getCause());
			}
		}
		//改造
		param.setTotalRows(this.consumeNoteService.count4PaginationOfView(param));
		param.setStart(param.countStart(pageIndex, param.getTotalPage()));
		int pi = pageIndex < 1 ? 1 : (pageIndex > param.getTotalPage() ? param
				.getTotalPage() : pageIndex);
		try{
			List<ConsumeNoteView> consumeNoteList = this.consumeNoteService.query4PaginationOfView(param);
			inv.addModel("consumeNoteList", consumeNoteList);
			inv.addModel("pageInfo", param);
			inv.addModel("pageIndex", pi);
		}catch(Exception e){
			logger.error(e,e.getCause());
		}
		return "user/consume/note/query_newsId";
	}
	
	/**
	 * 导出全部学生消费明细
	 */
	@Get("consume/note/export/excel/all")
	@Post("consume/note/export/excel/all")
	public String exportExcelForAll(ConsumeNoteView param){
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strCreateTimeBegin = inv.getParameter("createTimeBegin");
		String strCreateTimeEnd = inv.getParameter("createTimeEnd");
		File excelFile = null;
		try{
			if(null == strCreateTimeBegin || "".equals(strCreateTimeBegin) || 
					null == strCreateTimeEnd || "".equals(strCreateTimeEnd) ){
				inv.addModel("errorMsg","导出的时间参数为空");
				return "";
			}
			param.setCreateTimeBegin(dataFormat.parse(strCreateTimeBegin).getTime());
			param.setCreateTimeEnd(dataFormat.parse(strCreateTimeEnd).getTime());
			excelFile =new File(consumeNoteService.createExcelFile(param));
			if(!excelFile.exists()){
				//文件不存在
				inv.addModel("errorMsg","由于数据量太大，文件无法生成，请缩段导出时间");
				return "";
			}
			inv.getResponse().setHeader("Content-type","application/ms-excel");
			inv.getResponse().setHeader("Content-Disposition", "attachment;filename="+ new String("全部学生消费明细.xls".getBytes("utf-8"), "ISO8859-1" ));
			inv.getResponse().getOutputStream().write(FileUtils.readFileToByteArray(excelFile));
			inv.getResponse().setStatus(HttpServletResponse.SC_OK);
			inv.getResponse().flushBuffer();
		}catch(Exception e){
			logger.error(e,e.getCause());
		}finally{
			if(null !=excelFile && excelFile.exists()){
				excelFile.delete();
			}
		}
		return null;
	}
	
	/**
	 * 导出excel文件
	 */
	@Get("consume/note/export/excel/{studentId:[0-9]+}")
	@Post("consume/note/export/excel/{studentId:[0-9]+}")
	public String exportExcelForStudent(@Param("studentId")Long studentId,ConsumeNoteView param){
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strCreateTimeBegin = inv.getParameter("createTimeBegin");
		String strCreateTimeEnd = inv.getParameter("createTimeEnd");
		File excelFile = null;
		try {
			if(null == strCreateTimeBegin || "".equals(strCreateTimeBegin) || 
					null == strCreateTimeEnd || "".equals(strCreateTimeEnd) ){
				inv.addModel("errorMsg","导出的时间参数为空");
				inv.addModel("studentId",studentId);
				return "";
			}
			param.setCreateTimeBegin(dataFormat.parse(strCreateTimeBegin).getTime());
			param.setCreateTimeEnd(dataFormat.parse(strCreateTimeEnd).getTime());
			param.setStuId(studentId.intValue());
			excelFile =new File(consumeNoteService.createExcelFile(param));
			if(!excelFile.exists()){
				//文件不存在
				inv.addModel("errorMsg","由于数据量太大，文件无法生成，请缩段导出时间");
				inv.addModel("studentId",studentId);
				return "";
			}
			inv.getResponse().setHeader("Content-type","application/ms-excel");
			inv.getResponse().setHeader("Content-Disposition", "attachment;filename="+ new String("学生消费明细.xls".getBytes("utf-8"), "ISO8859-1" ));
			inv.getResponse().getOutputStream().write(FileUtils.readFileToByteArray(excelFile));
			inv.getResponse().setStatus(HttpServletResponse.SC_OK);
			inv.getResponse().flushBuffer();
		} catch (Exception e) {
			logger.error(e,e.getCause());
		}finally{
			if(null !=excelFile && excelFile.exists()){
				excelFile.delete();
			}
		}
		return null;
	}
	
	/**
	 * 分页查询消费记录
	 */
	@Get("consume/note/list/{studentId:[0-9]+}/{pageIndex:[0-9]+}")
	@Post("consume/note/list/{studentId:[0-9]+}/{pageIndex:[0-9]+}")
	public String query(@Param("studentId")Long studentId,@Param("pageIndex")int pageIndex,ConsumeNoteView param){
		/**
		 * 参数设置
		 */
		if("".equals(param.getType())){
			param.setType(null);
		}
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strCreateTimeBegin = inv.getParameter("createTimeBegin");
		if(null !=strCreateTimeBegin && !"".equals(strCreateTimeBegin)){
			try {
				param.setCreateTimeBegin(dataFormat.parse(strCreateTimeBegin).getTime());
				inv.addModel("createTimeBegin", strCreateTimeBegin);
			} catch (ParseException e) {
				logger.error(e,e.getCause());
			}
		}
		String strCreateTimeEnd = inv.getParameter("createTimeEnd");
		if(null !=strCreateTimeEnd && !"".equals(strCreateTimeEnd)){
			try {
				param.setCreateTimeEnd(dataFormat.parse(strCreateTimeEnd).getTime());
				inv.addModel("createTimeEnd", strCreateTimeEnd);
			} catch (ParseException e) {
				logger.error(e,e.getCause());
			}
		}
		
		param.setStuId(studentId.intValue());
		param.setTotalRows(this.consumeNoteService.count4PaginationOfView(param));
		param.setStart(param.countStart(pageIndex, param.getTotalPage()));
		int pi = pageIndex < 1 ? 1 : (pageIndex > param.getTotalPage() ? param
				.getTotalPage() : pageIndex);
		try{
			List<ConsumeNoteView> consumeNoteList = this.consumeNoteService.query4PaginationOfView(param);
			inv.addModel("consumeNoteList", consumeNoteList);
			inv.addModel("pageInfo", param);
			inv.addModel("pageIndex", pi);
			inv.addModel("consumeTypes",ConsumeVariable.TYPES);
		}catch(Exception e){
			logger.error(e,e.getCause());
		}
		return "user/consume/note/list";
	}
}
