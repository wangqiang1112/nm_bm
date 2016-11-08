package ns.common;


public class BaseQuery {
		private int pageSize = 20;
		private int pageIndex = 1;
		private int opratId=0;
		private String startDate;
		private String endDate;
		
		
		
		
		public int getOpratId() {
			return opratId;
		}
		public void setOpratId(int opratId) {
			this.opratId = opratId;
		}
		
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getPageIndex() {
			return pageIndex;
		}
		public void setPageIndex(int pageIndex) {
			this.pageIndex = pageIndex;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
}
