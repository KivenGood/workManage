package com.xawl.Exception;
/**
 * 自定义异常类
 * @author doter
 *
 */
public class MyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	int state;

	public MyException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public MyException(String message, int state) {
		super(message);
		this.message=message;
		this.state =state;
	}

}
