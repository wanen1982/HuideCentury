package youda.component.constants;

/**
 * 消费类型
 * @author we
 *
 */
public interface ConsumeVariable {
	public static final String CHARGE = "CZ";//充值
	public static final String CONSUME = "XF";//消费
	public static final String MONEY_BACK = "TK";//退款
	//消费类型列表
	public static final String[] TYPES = new String[]{CHARGE,CONSUME,MONEY_BACK};
	
	/**
	 * 充值费率
	 */
	public static final double CONSUME_RATE= 1.2D;
}
