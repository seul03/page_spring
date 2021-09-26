package board;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PagingVO {
	private int page;
	private int pageNum;
	private int rowStart;
	private int rowEnd;
	private int total;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int disPageNum = 10;
	private String keyword;

	public PagingVO() {
		this.page = 1;
		this.pageNum = 10;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPageNum(int pageNum) {
		if(pageNum <= 0 || pageNum > 100) {
			this.pageNum = 10;
			return;
		}
		this.pageNum = pageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageStart() {
		return (this.page - 1) * pageNum;
	}
	
	public int getPageNum() {
		return this.pageNum;
	}
	
	public int getRowStart() {
		rowStart = ((page - 1) * pageNum) + 1;
		return rowStart;
	}
	
	public int getRowEnd() {
		rowEnd = rowStart + pageNum - 1;
		return rowEnd;
	}
	
	public void setTotal(int total) {
		this.total = total;
		calcData();
	}
	
	public int getTotal() {
		return total;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisPageNum() {
		return disPageNum;
	}
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
}
	
	private void calcData() {
		endPage = (int)(Math.ceil(getPage() / (double)disPageNum) * disPageNum);
		startPage = (endPage - disPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(total / (double)getPageNum()));
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * getPageNum() >= total ? false : true;
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents =
		UriComponentsBuilder.newInstance()
							.queryParam("page", page)
							.queryParam("perPageNum", getPageNum())
							.build();
		
		return uriComponents.toUriString();
	}
}


	
