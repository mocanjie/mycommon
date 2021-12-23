package io.github.mocanjie.base.mycommon.pager;


import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * 分页bean
 * 
 * @author canjie.mo 
 * @since 2016年7月19日
 */
@Data
public class Pager<T>{
	
	private long currentPage = 1L;
	private int pageSize = 10;
	private long totalRows;
	private long totalPages;
	private long startRow;
	private List<T> pageData;
	
	//清汇新增参数

	private String order = "";//排序方式
	private String sort = "";//排序字段
	
	private Boolean ignoreCount = false;//忽略分页
	
	public Pager(int pageSize) {
		currentPage = 1L;
		startRow = 0L;
		this.pageSize = pageSize;
	}


	public Pager() {
		currentPage = 1L;
		startRow = 0L;
	}

	public Pager(Map<String, Object> request, int pageSize) {
		currentPage = (request.get("currentPage") == null ? 1L : Integer.parseInt(String.valueOf(request.get("currentPage"))));
		startRow = 0L;
		this.pageSize = pageSize;
	}
	
	

	
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
		totalPages = (totalRows / pageSize);
		long mod = totalRows % pageSize;
		if (mod > 0L) {
		  totalPages += 1L;
		}
		if (currentPage > totalPages)
		  currentPage = totalPages;
		startRow = ((currentPage - 1L) * pageSize);
		if (startRow < 0L)
		  startRow = 0L;
		if (currentPage <= 0L)
		  currentPage = 1L;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getStartRow() {
		return startRow != 0L ? startRow : (currentPage - 1L) * pageSize;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public List<T> get() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Boolean getIgnoreCount() {
		return ignoreCount;
	}

	public Pager setIgnoreCount(Boolean ignoreCount) {
		this.ignoreCount = ignoreCount;
		return this;
	}
	

}
