package com.youthlin.javaee.ejb;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

@Remote
public interface AllBookRemote {
	boolean addBook(Map<String, Object> book);

	boolean addBook(List<Map<String, Object>> list);

	void clear();

	List<Map<String, Object>> getAllBook();

	String sayHello(String name);
}
