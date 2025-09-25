package io.github.mocanjie.base.mycommon.pager;

/**
 * 分页参数基类
 *
 * @author canjie.mo
 * @since 2016年7月19日
 */
public class PagerParam {

	protected int pageNum = 1;
	protected int pageSize = 10;

	public int getPageNum() {
		return pageNum;
	}

	public PagerParam setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public PagerParam setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

}