package io.github.mocanjie.base.mycommon.data;


import io.github.mocanjie.base.mycommon.pager.Pager;

public class DataPagerResult extends ResponseResult {
	protected Object data;
	protected long count;
	
	public DataPagerResult(Pager pager) {
		super();
		this.data = pager.getPageData();
		this.count = pager.getTotalRows();
	}
	public DataPagerResult(Object data) {
		super();
		this.data = data;
	}
	public DataPagerResult(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public DataPagerResult() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
