package com.youthlin.javaee.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class BookList
 */
@Stateless
public class BookList implements BookListRemote {
	private List<Map<String, Object>> list;

	public BookList() {
		list = new ArrayList<Map<String, Object>>();
	}

	@Override
	public List<Map<String, Object>> getList() {
		return list;
	}

	@Override
	public boolean add(Map<String, Object> map) {
		return list.add(map);
	}

	@Override
	public void clear() {
		list.clear();
	}
}
