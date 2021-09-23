package board;

public class PagingVO {
	private int now;
	private int startpage;
	private int endpage;
	private int total;
	private int cntpage = 5;
	private int lastpage;
	private int sta;
	private int end;

	public PagingVO() {}

	public PagingVO(int total, int now, int cntpage) {
		setNow(now);
		setCntpage(cntpage);
		setTotal(total);
		calcLastPage(getTotal(), getCntpage());
		calcStartEndPage(getNow(), cntpage);
		calcStartEnd(getNow(), getCntpage());
	}
	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPage) {
		setLastpage((int) Math.ceil((double)total / (double)cntpage));
	}
	// 시작, 끝 페이지 계산
	public void calcStartEndPage(int now, int cntPage) {
		setEndpage(((int)Math.ceil((double)now / (double)cntPage)) * cntPage);
		if (getLastpage() < getEndpage()) {
			setEndpage(getLastpage());
		}
		setStartPage(getEndpage() - cntPage + 1);
		if (getStartPage() < 1) {
			setStartPage(1);
		}
	}
	// DB 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int now, int cntPage) {
		setEnd(now * cntPage);
		setSta(getEnd() - cntPage + 1);
	}
	
	public int getnow() {
		return now;
	}
	public void setnow(int now) {
		this.now = now;
	}
	public int getStartPage() {
		return startpage;
	}
	public void setStartPage(int startPage) {
		this.startpage = startPage;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntpage() {
		return cntpage;
	}

	public void setCntpage(int cntpage) {
		this.cntpage = cntpage;
	}

	public int getLastpage() {
		return lastpage;
	}

	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}

	public int getSta() {
		return sta;
	}

	public void setSta(int sta) {
		this.sta = sta;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
	
}
