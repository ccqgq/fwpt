package com.qgq.fwpt.common.dto;

/**
 * 全局的返回格式
 * @author qiguoqiang  
 *
 */
public class ResultDto {
	//标志码
	private int code;
	//信息
	private String message;
	//数据
	private Object data;
	
	public ResultDto() {
	}
	
	public ResultDto(int code) {
		this.code = code;
	}

	public ResultDto(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
