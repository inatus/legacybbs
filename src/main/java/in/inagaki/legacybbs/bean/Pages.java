package in.inagaki.legacybbs.bean;

public class Pages {
	private int totalPage;
	private int currentPage;

	public Pages(int totalPage, int currentPage) {
		super();
		this.totalPage = totalPage;
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
