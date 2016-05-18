package com.youthlin.javaee.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class ViewBook
 */
@Stateless
public class ViewBook implements ViewBookRemote {

    @Override
    public List<Map<String, Object>> getAllBook() {
        return new AllBook().getAllBook();
    }

}
