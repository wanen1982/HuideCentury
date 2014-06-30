package youda.component.util;

import java.math.BigDecimal;

/**
 * 浮点数计算，精度不丢失
 * @author we
 *
 */
public class NumberComputeUtil {
	//默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
	/**
	 * 两浮点数相加,参数v1加上参数v2
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1,double v2){
		BigDecimal bigV1 = new BigDecimal(v1);
		BigDecimal bigV2 = new BigDecimal(v2);
		return bigV1.add(bigV2).doubleValue();
	}
	/**
	 * 减法运算，参数v1减参数v2
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1,double v2){
		BigDecimal bigV1 = new BigDecimal(v1);
		BigDecimal bigV2 = new BigDecimal(v2);
		return bigV1.subtract(bigV2).doubleValue();
	}
	
	/**
	 * 乘法运算，参数v1乘以参数v2
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1,double v2){
		BigDecimal bigV1 = new BigDecimal(v1);
		BigDecimal bigV2 = new BigDecimal(v2);
	    return bigV1.multiply(bigV2).doubleValue();
	}
	
	/**
	 * 除法运算，参数v1除以参数v2，精度10
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double div(double v1,double v2){
		return div(v1, v2, DEF_DIV_SCALE);
	}
	
	/**
	 * 除法运算，参数v1除以参数v2，参数3给定精度
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double div(double v1,double v2,int scale){
		if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 四舍五入，保存小数点后位数，参数1：需要操作的数；参数2：精度’
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v,int scale){
		if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
