package youda.component.model;

import java.io.Serializable;

/**
 *  分页抽象模型
 * @author we
 *
 */
public abstract class AbstractPagination implements Serializable{
	private static final long serialVersionUID = 8283324044049683840L;
	private Integer start=0,rows=20,totalRows=0;
	
	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	/**
	 * @return the totalRows
	 */
	public Integer getTotalRows() {
		return totalRows;
	}
	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	
	/**
	 * 计算总页数
	 * @return
	 */
	public int getTotalPage(){
		return this.totalRows % rows > 0? this.totalRows/rows +1 : this.totalRows/rows; 
	}
	
	/**
	 * 计算起始值
	 */
	public int countStart(int indexPage,int totalPage){
		if(indexPage < 1 || totalPage < 1) return 0;
		if(indexPage > totalPage){
			return (totalPage-1) * rows;
		}
		return (indexPage-1) * rows;
	}
}
