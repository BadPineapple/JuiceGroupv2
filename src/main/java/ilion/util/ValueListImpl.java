package ilion.util;


import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import net.mlw.vlh.ValueList;


public class ValueListImpl implements ValueList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List list;
	private ValueListInfo valueListInfo;
	private Iterator iterator;
	
	public ValueListImpl(List list, ValueListInfo valueListInfo) {
	    this.list = list;
	    this.valueListInfo = valueListInfo;
	}

	public List getList() {
		return list;
	}

	public net.mlw.vlh.ValueListInfo getValueListInfo() {
		return valueListInfo;
	}

	public boolean hasNext() {
		if (iterator == null) {
			iterator = list.iterator();
		}
		return iterator.hasNext();
	}

	public Object next() throws NoSuchElementException {
		if (iterator == null) {
			iterator = list.iterator();
		}
		return iterator.next();
	}

	public void remove() {
		if (iterator == null) {
			iterator = list.iterator();
		}
		iterator.remove();
	}
}