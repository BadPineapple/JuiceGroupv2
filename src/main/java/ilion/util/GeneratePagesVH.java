package ilion.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneratePagesVH {

	private Integer pagingPage;
	
	private Integer totalNumberOfPages;
	
	private GeneratePagesVH() {
		super();
	}
	
	public static GeneratePagesVH create() {
		return new GeneratePagesVH();
	}
	
	public GeneratePagesVH withPagingPage(Integer pagingPage) {
		
		this.pagingPage = pagingPage;
		
		return this;
	}
	
	public GeneratePagesVH withTotalNumberOfPages(Integer totalNumberOfPages) {
		
		this.totalNumberOfPages = totalNumberOfPages;
		
		return this;
	}
	
	public List<Integer> build() {
		
		if( pagingPage == null ) {
			throw new RuntimeException("pagingPage must not be null");
		}
		
		if( totalNumberOfPages == null ) {
			throw new RuntimeException("totalNumberOfPages must not be null");
		}
		
		List<Integer> allPages = new ArrayList<Integer>();
		
		for (int i = 1; i <= totalNumberOfPages; i++) {
			
			allPages.add(i);
			
		}
		
		if( allPages.size() <= 5 ) {
			return allPages;
		}
		
		for (Iterator<Integer> iterator = allPages.iterator(); iterator.hasNext();) {
			Integer page = (Integer) iterator.next();
			
			if( allPages.size() == 5 ) {
				break;
			}
			
			if( pagingPage <= 3 && page <= 5 ) {
				continue;
			}
			
			int diff = 0;
			
			if( page > pagingPage ) {
				diff = page - pagingPage;
			} else if( page < pagingPage ) {
				diff = pagingPage - page;
			}
			
			if( diff >= 3 ) {
				iterator.remove();
			}
		}
		
		return allPages;
	}


	
}