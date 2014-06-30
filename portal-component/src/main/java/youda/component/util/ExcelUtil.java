package youda.component.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import youda.component.model.ConsumeNoteView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {
	private static final Logger log = Logger.getLogger(ExcelUtil.class);

	/**
	 * 导出excel
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static void exportXls(String fileName,String[] titleList,Map<String, List<ConsumeNoteView>>  dataMap) {
		WritableWorkbook wriBook = null;
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(new File(fileName));
			wriBook = Workbook.createWorkbook(fileOut);
			Set<String> sheetList = dataMap.keySet();
			int sheetIndex=0;
			for(String sheetName : sheetList){
				WritableSheet wriSheet = wriBook.createSheet(sheetName, sheetIndex);
				sheetIndex++;
				WritableCellFormat wriCellFormat = getContentCellFormat();
				WritableCellFormat titleCellFormat = getTitleCellFormat();
				for(int colIndex=0,size=titleList.length; colIndex <size; colIndex++){
					wriSheet.addCell(new Label(colIndex, 0,titleList[colIndex], titleCellFormat));
				}
				List<ConsumeNoteView> consumeNoteList = dataMap.get(sheetName);
				for (int row = 0,rowCount=consumeNoteList.size(); row < rowCount; row++) {
					ConsumeNoteView consumeBean = consumeNoteList.get(row);
					int column = 0;
					wriSheet.addCell(new Label(column, row+1, consumeBean.getId().toString(), wriCellFormat));
					wriSheet.addCell(new Label(++column, row+1, consumeBean.getTypeName(), wriCellFormat));
					String catName = null==consumeBean.getCatName()?"":consumeBean.getCatName();
					wriSheet.addCell(new Label(++column, row+1, catName, wriCellFormat));
					String courseName = null==consumeBean.getCourseName()?"":consumeBean.getCourseName();
					wriSheet.addCell(new Label(++column, row+1, courseName, wriCellFormat));
					wriSheet.addCell(new Label(++column, row+1, consumeBean.getStudentName(), wriCellFormat));
					wriSheet.addCell(new Label(++column, row+1, consumeBean.getCost().toString(), wriCellFormat));
					wriSheet.addCell(new Label(++column, row+1, consumeBean.getShowCreateTime(), wriCellFormat));
					String remark = null==consumeBean.getRemark()?"":consumeBean.getRemark();
					wriSheet.addCell(new Label(++column, row+1, remark, wriCellFormat));
				}
			}
			wriBook.write();
		} catch (Exception e) {
			log.error(e, e.getCause());
		} finally {
			try {
				if (null != wriBook)
					wriBook.close();
			} catch (Exception e) {
				log.error(e, e.getCause());
			}
		}
	}
	
	/**
	 * 主内内容格式化
	 * @return
	 * @throws WriteException
	 */
	private static WritableCellFormat getContentCellFormat() throws WriteException{
		WritableCellFormat wriCellFormat = new WritableCellFormat();
		wriCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		wriCellFormat.setAlignment(Alignment.CENTRE);
		return wriCellFormat;
	}
	
	/**
	 * 表头的格式
	 * @return
	 * @throws WriteException
	 */
	private static WritableCellFormat getTitleCellFormat() throws WriteException {
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat wriCellFormat = new WritableCellFormat();
		wriCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		wriCellFormat.setAlignment(Alignment.CENTRE);
		wriCellFormat.setFont(font);
		return wriCellFormat;
	}
	
	public static void main(String[] args) {
		Map<String, List<ConsumeNoteView>> datamap = new HashMap<String, List<ConsumeNoteView>>();
		List<ConsumeNoteView> list = new ArrayList<ConsumeNoteView>();
		ConsumeNoteView bean = new ConsumeNoteView();
		bean.setId(1);
		bean.setTypeName("消费");
		bean.setCourseName("周日上午-双语早教课");
		bean.setCatName("双语早教");
		bean.setStudentName("李中国");
		bean.setCost(128F);
		bean.setShowCreateTime("2014-05-26 10:01:58");
		bean.setRemark("无");
		list.add(bean);
		datamap.put("消费",list);
		datamap.put("充值",new ArrayList<ConsumeNoteView>());
		datamap.put("退款",new ArrayList<ConsumeNoteView>());
		exportXls("C:/Users/wanen.GEOSTAR/Desktop/1.xls",ConsumeNoteView.getExcelTitle(),datamap);
	}
}
