package com.netctoss.entity;

import java.io.Serializable;

/**
 * 返回结果类型封装
 * @author Madrid
 *
 */
public class NetctossResult implements Serializable{
	
	private static final long serialVersionUID = 6361404221203005033L;
	//状态
	private int state;//0 表示成功   1表示失败
	//休息
	private String msg;
	//数据
	private Object data;
	//数据2
	private Object data2;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData2() {
		return data2;
	}
	public void setData2(Object data2) {
		this.data2 = data2;
	}
	
}
