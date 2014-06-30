package youda.component.model;


/**
 * 消费明细表
 * @author we
 *
 */
public class ConsumeNote extends AbstractPagination{

	private static final long serialVersionUID = 7263482117283203768L;
	private Integer id;
	private String type;//'消费类型，取值范围（充值，消费，退款）',
	private Integer  catId;//'上的课程',
	private Integer  stuId;//'刷卡学生的编码',
	private Float   cost;//'发生额',
	private Long  createTime;//'记录创建的时间',
	private String  newsId;//'课程编号，用于关联内容课程的信息'
	private String remark;//备注
	
	/**
	 * 用于显示的属性
	 */
	private Float previousCost;//充值额度(不含费率的)
	private String showCreateTime;//创建时间
	/**
	 * 查询条件
	 */
	//创建时间起,创建时间止
	private Long createTimeBegin,createTimeEnd;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the catId
	 */
	public Integer getCatId() {
		return catId;
	}
	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	/**
	 * @return the stuId
	 */
	public Integer getStuId() {
		return stuId;
	}
	/**
	 * @param stuId the stuId to set
	 */
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	/**
	 * @return the cost
	 */
	public Float getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(Float cost) {
		this.cost = cost;
	}
	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the newsId
	 */
	public String getNewsId() {
		return newsId;
	}
	/**
	 * @param newsId the newsId to set
	 */
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the createTimeBegin
	 */
	public Long getCreateTimeBegin() {
		return createTimeBegin;
	}
	/**
	 * @param createTimeBegin the createTimeBegin to set
	 */
	public void setCreateTimeBegin(Long createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	/**
	 * @return the createTimeEnd
	 */
	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}
	/**
	 * @param createTimeEnd the createTimeEnd to set
	 */
	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	/**
	 * @return the previousCost
	 */
	public Float getPreviousCost() {
		return previousCost;
	}
	/**
	 * @param previousCost the previousCost to set
	 */
	public void setPreviousCost(Float previousCost) {
		this.previousCost = previousCost;
	}
	/**
	 * @return the showCreateTime
	 */
	public String getShowCreateTime() {
		return showCreateTime;
	}
	/**
	 * @param showCreateTime the showCreateTime to set
	 */
	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}
}
