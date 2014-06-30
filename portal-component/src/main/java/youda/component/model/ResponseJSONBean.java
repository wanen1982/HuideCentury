package youda.component.model;

import java.io.Serializable;
import java.util.List;

public class ResponseJSONBean<T> implements Serializable{
	private static final long serialVersionUID = 4087522928838124160L;
	private T bean;
	private List<T> reultBeans;
	private String message;
	private Boolean success;
	
	/**
	 * @return the bean
	 */
	public T getBean() {
		return bean;
	}
	/**
	 * @param bean the bean to set
	 */
	public void setBean(T bean) {
		this.bean = bean;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	/**
	 * @return the reultBeans
	 */
	public List<T> getReultBeans() {
		return reultBeans;
	}
	/**
	 * @param reultBeans the reultBeans to set
	 */
	public void setReultBeans(List<T> reultBeans) {
		this.reultBeans = reultBeans;
	}

}
