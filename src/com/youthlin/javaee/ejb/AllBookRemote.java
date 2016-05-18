package com.youthlin.javaee.ejb;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

@Remote
public interface AllBookRemote {
    boolean addBook(Map<String, Object> book);

    boolean addBook(List<Map<String, Object>> list);

    List<Map<String, Object>> getAllBook();

    void clear();

    String sayHello(String name);
}
