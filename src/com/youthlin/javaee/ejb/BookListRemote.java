package com.youthlin.javaee.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface BookListRemote {
    List<Map<String, Object>> getList();

    boolean add(Map<String, Object> map);

    void clear();
}
