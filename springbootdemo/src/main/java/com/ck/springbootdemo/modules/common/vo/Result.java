package com.ck.springbootdemo.modules.common.vo;

public class Result<T> {
	private int status;
	private String message;
	private T object;
	
	public Result() {
	}

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public Result(int status, String message, T object) {
		this.status = status;
		this.message = message;
		this.object = object;
	}
	
	public enum ResultStatus{
		SUCCESS(200), FAILED(500);
		
		public int status;
		
		//采用单例模式生成实例，为确保值的唯一性，构造方法私有
		private ResultStatus(int status){
			this.status = status;
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
}
