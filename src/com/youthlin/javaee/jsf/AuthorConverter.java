package com.youthlin.javaee.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lin on 2016-05-09-009. 自定义转换器
 */
@FacesConverter("com.youthlin.javaee.jsf.AuthorConverter")
public class AuthorConverter implements Converter {
//	private static Logger LOG = MyLog.getLogger(AuthorConverter.class);

    @Override
    public Object getAsObject(FacesContext facesContext,
                              UIComponent uiComponent, String s) {
//		LOG.debug("转换器：字符是" + s);
        if (s == null || s.length() == 0) {
            return null;
        }
        String[] authors = s.split(",");
        List<String> converterAuthor = new ArrayList<>();
        Collections.addAll(converterAuthor, authors);
        return converterAuthor;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public String getAsString(FacesContext facesContext,
                              UIComponent uiComponent, Object o) {
        if (o == null)
            return null;
        List<String> converterAuthor;
        if (o instanceof List) {
            converterAuthor = (List<String>) o;
            if (converterAuthor.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (String s : converterAuthor) {
                    sb.append(s);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
//				LOG.debug("sb=" + sb);
                return sb.toString();
            }
        }
        return null;
    }
}
