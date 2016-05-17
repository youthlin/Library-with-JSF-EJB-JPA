package com.youthlin.javaee.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface ViewBookRemote {
	List<Map<String, Object>> getAllBook();
}
