package ns.common;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 分页 
 * @author 尚雄  
 * @Date 2016-10-14
 */
public class Page {
	
	private PageList<?> result;
	private int pageIndex;
	private int pageSize;
	private int pageCount;
	private int totalCount;
	
	public PageList<?> getResult() {
		return result;
	}
	public void setResult(PageList<?> result) {
		this.result = result;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
