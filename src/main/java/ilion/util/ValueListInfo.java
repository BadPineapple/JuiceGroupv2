package ilion.util; 





public class ValueListInfo extends net.mlw.vlh.ValueListInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int FIRST_PAGE = 1;

	public static final int DEFAULT_PAGE_SIZE = 20;

	public final static Integer ASC = net.mlw.vlh.ValueListInfo.ASCENDING;

	public final static Integer DESC = net.mlw.vlh.ValueListInfo.DESCENDING;

	private boolean allPages;

	public static final ValueListInfo ALL = new ValueListInfo();

	public ValueListInfo() {
		this(true, null);
	}

	public ValueListInfo(String primarySortColumn, Integer primarySortDirection) {
		this(true, primarySortColumn);
		setPrimarySortDirection(primarySortDirection);
	}

	public ValueListInfo(String primarySortColumn) {
		this(true, primarySortColumn);
	}

	public ValueListInfo(boolean allPages) {
		setAllPages(allPages);
		setPagingNumberPer(ValueListInfo.DEFAULT_PAGE_SIZE);
		setPagingPage(ValueListInfo.FIRST_PAGE);
		setPrimarySortDirection(ValueListInfo.ASC);
	}
	
	public ValueListInfo(boolean allPages, String primarySortColumn) {
		setPrimarySortColumn(primarySortColumn);

		setAllPages(allPages);
		setPagingNumberPer(ValueListInfo.DEFAULT_PAGE_SIZE);
		setPagingPage(ValueListInfo.FIRST_PAGE);
		setPrimarySortDirection(ValueListInfo.ASC);
	}

	public ValueListInfo(VLHForm vlhForm) {
		setAllPages(allPages);

		setPrimarySortColumn(vlhForm.getSortColumn());		
		if(vlhForm.getSortDirection() != null && !vlhForm.getSortDirection().equals(""))
			setPrimarySortDirection(vlhForm.getSortDirection());
		else
			setPrimarySortDirection(new Integer(1));

		if(vlhForm.getPagingPage() != null && !vlhForm.getPagingPage().equals(""))
			setPagingPage(vlhForm.getPagingPage().intValue());
		else
			setPagingPage(1);

		setPagingNumberPer(vlhForm.getPagingNumberPer());
	}
	
	public ValueListInfo(VLHForm vlhForm, Integer qtd) {
		this(vlhForm);
		setPagingNumberPer(qtd);
	}

	public String getSortingDirectionName() {
		Integer sortingDirection = getSortingDirection();
		if (sortingDirection.equals(ValueListInfo.ASC)) {
			return "asc";
		} else if (sortingDirection.equals(ValueListInfo.DESC)) {
			return "desc";
		} else {
			return null;
		}
	}

	public boolean isAllPages() {
		return allPages;
	}

	public void setAllPages(boolean allPages) {
		this.allPages = allPages;
	}
}