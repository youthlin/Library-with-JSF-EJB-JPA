package com.youthlin.javaee.jsf;

import java.util.*;
import javax.naming.*;

import com.youthlin.javaee.ejb.*;
import org.apache.logging.log4j.Logger;

public class BookUtil {
    private static AllBookRemote allBookRemote;
    private static ViewBookRemote viewBookRemote;
    private static BookListRemote bookListRemote;
    private static LoginCountRemote loginCountRemote;
    private static Logger LOG = MyLog.getLogger(BookUtil.class);

    public static Object getRemote(Class<?> clazz) {
        LOG.trace("获取远程对象");
        if (clazz.getName().equals(AllBookRemote.class.getName())
                && allBookRemote != null) {
            LOG.trace("AllBookRemote 不为空，直接返回");
            return allBookRemote;
        }
        if (clazz.getName().equals(ViewBookRemote.class.getName())
                && viewBookRemote != null) {
            LOG.trace("ViewBookRemote 不为空，直接返回");
            return viewBookRemote;
        }
        if (clazz.getName().equals(LoginCountRemote.class.getName())
                && loginCountRemote != null) {
            LOG.trace("LookCountRemote 不为空，直接返回");
            return loginCountRemote;
        }
        if (clazz.getName().equals(BookListRemote.class.getName())
                && bookListRemote != null) {
            LOG.trace("BookListRemote 不为空，直接返回");
            return bookListRemote;
        }
        Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            Context context = new InitialContext(jndiProperties);
            final String appName = "";
            final String moduleName = "BookEJB";
            final String distinctName = "";
            final String fullName = "ejb:" + appName + "/" + moduleName
                    + "/" + distinctName + "/"
                    + clazz.getSimpleName().substring(0,
                    clazz.getSimpleName().length() - 6) + "!"
                    + clazz.getName();
            LOG.debug("EJB全名=" + fullName);
            return context.lookup(fullName);
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            LOG.trace("远程对象获取完毕");
        }
        LOG.warn("获取远程对象失败");
        return null;
    }

    /**
     * 返回所有的图书(要求一，查阅所有图书)
     */
    public static List<Map<String, Object>> getAllBook() {
        allBookRemote = (AllBookRemote) getRemote(AllBookRemote.class);
        if (allBookRemote != null) {
            return allBookRemote.getAllBook();
        }
        return null;
    }

    /**
     * 添加到会话列表里(要求二：用有状态会话Bean保存临时列表)
     */
    public static boolean addBook(Map<String, Object> map) {
        bookListRemote = (BookListRemote) getRemote(BookListRemote.class);
        if (bookListRemote != null) {
            LOG.debug("调用添加到会话列表的方法,当前列表=" + bookListRemote.getList());
            boolean result = bookListRemote.add(map);
            LOG.debug("添加成功=" + result + ";当前列表=" + bookListRemote.getList());
            return result;
        }
        return false;
    }

    public static List<Map<String, Object>> getList() {
        bookListRemote = (BookListRemote) getRemote(BookListRemote.class);
        if (bookListRemote != null) {
            return bookListRemote.getList();
        }
        return null;
    }

    /**
     * 添加会话中的图书列表(要求二，有状态会话Bean，一次添加一个列表)
     */
    public static boolean addAll(List<Map<String, Object>> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        allBookRemote = (AllBookRemote) getRemote(AllBookRemote.class);
        if (allBookRemote != null) {
            LOG.debug("调用添加整个列表的方法,全局列表=" + allBookRemote.getAllBook());
            boolean result = allBookRemote.addBook(list);
            LOG.debug("添加成功=" + result + "全局列表=" + allBookRemote.getAllBook());
            clearList();
            return result;
        }
        return false;
    }

    /**
     * 查询登录次数(要求三，单例Bean记录登录次数)
     */
    public static int getLoginCount() {
        loginCountRemote = (LoginCountRemote) getRemote(LoginCountRemote.class);
        if (loginCountRemote != null) {
            return loginCountRemote.getCount();
        }
        return 0;
    }

    public static void addCount() {
        loginCountRemote = (LoginCountRemote) getRemote(LoginCountRemote.class);
        if (loginCountRemote != null) {
            loginCountRemote.add();
        }
    }

    public static void clearList() {
        bookListRemote = (BookListRemote) getRemote(BookListRemote.class);
        if (bookListRemote != null) {
            LOG.debug("清空会话列表");
            bookListRemote.clear();
        }
    }

    public static void clearAll() {
        allBookRemote = (AllBookRemote) getRemote(AllBookRemote.class);
        if (allBookRemote != null) {
            LOG.debug("清空所有！");
            allBookRemote.clear();
        }
    }
}
