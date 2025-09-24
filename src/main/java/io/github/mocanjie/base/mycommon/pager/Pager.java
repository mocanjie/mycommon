package io.github.mocanjie.base.mycommon.pager;



import java.util.List;
import java.util.Map;


/**
 * 分页bean
 * 
 * @author canjie.mo 
 * @since 2016年7月19日
 */
public class Pager<T> extends PagerParam {

	private long totalRows;
	private long totalPages;
	private long startRow = 0L;
	private List<T> pageData;
	
	//清汇新增参数

	private String order = "";//排序方式
	private String sort = "";//排序字段
	
	private Boolean ignoreCount = false;//忽略分页

	public Pager<T> Pager(){
		return this;
	}

	public Pager(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	public Pager(int pageSize) {
		this.pageSize = pageSize;
	}


	public Pager() {
	}

	public Pager(Map<String, Object> request, int pageSize) {
		pageNum = (request.get("pageNum") == null ? 1 : Integer.parseInt(String.valueOf(request.get("pageNum"))));
		this.pageSize = pageSize;
	}
	
	

	
	@Override
	public Pager<T> setPageNum(int pageNum) {
		super.setPageNum(pageNum);
		return this;
	}

	@Override
	public Pager<T> setPageSize(int pageSize) {
		super.setPageSize(pageSize);
		return this;
	}

	public long getTotalRows() {
		return totalRows;
	}
	public Pager<T> setTotalRows(long totalRows) {
		this.totalRows = totalRows;
		totalPages = (totalRows / pageSize);
		long mod = totalRows % pageSize;
		if (mod > 0L) {
		  totalPages += 1L;
		}
		if (pageNum > totalPages)
			pageNum = (int) totalPages;
		startRow = ((pageNum - 1) * pageSize);
		if (startRow < 0L)
		  startRow = 0L;
		if (pageNum <= 0)
			pageNum = 1;
		return this;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public Pager<T> setTotalPages(long totalPages) {
		this.totalPages = totalPages;
		return this;
	}

	public long getStartRow() {
		return startRow != 0L ? startRow : (pageNum - 1) * pageSize;
	}

	public Pager<T> setStartRow(long startRow) {
		this.startRow = startRow;
		return this;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public List<T> get() {
		return pageData;
	}

	public Pager<T> setPageData(List<T> pageData) {
		this.pageData = pageData;
		return this;
	}

	public String getOrder() {
		return order;
	}

	public Pager<T> setOrder(String order) {
		this.order = order;
		return this;
	}

	public String getSort() {
		return sort;
	}

	public Pager<T> setSort(String sort) {
		this.sort = sort;
		return this;
	}

	public Boolean getIgnoreCount() {
		return ignoreCount;
	}

	public Pager setIgnoreCount(Boolean ignoreCount) {
		this.ignoreCount = ignoreCount;
		return this;
	}
	

}
