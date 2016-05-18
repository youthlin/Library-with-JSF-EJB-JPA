package com.youthlin.javaee.ejb;

import java.util.*;
import javax.ejb.Singleton;
import javax.persistence.*;

import com.youthlin.javaee.beans.Book;

/**
 * Session Bean implementation class AllBook
 */
@Singleton
public class AllBook implements AllBookRemote {
    private List<Map<String, Object>> allBook;

    @PersistenceContext(unitName = "BookEJB")
    private EntityManager manager;

    public AllBook() {
        allBook = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addBook(Map<String, Object> book) {
        String name = null;
        String isbn = null;
        Date publishDate = null;
        Float price = null;
        String type = null;
        String subType = null;
        String author = null;
        if (book.containsKey("name"))
            name = (String) book.get("name");
        if (book.containsKey("isbn"))
            isbn = (String) book.get("isbn");
        if (book.containsKey("publishDate"))
            publishDate = (Date) book.get("publishDate");
        if (book.containsKey("price"))
            price = (Float) book.get("price");
        if (book.containsKey("type"))
            type = (String) book.get("type");
        if (book.containsKey("subType"))
            subType = (String) book.get("subType");

        if (book.containsKey("author"))
            author = book.get("author").toString();

        Book b = new Book(name, isbn, publishDate, price, type, subType, author);
        manager.persist(b);
        System.out.println("远程接口实现类正在持久化：" + b.toMap());
        return allBook.add(book);
    }

    @Override
    public List<Map<String, Object>> getAllBook() {
        Query q = manager.createQuery("from Book b");
        List<?> books = q.getResultList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object o : books) {
            result.add(((Book) o).toMap());
        }
        System.out.println(result);
        // return allBook;
        return result;
    }

    public String sayHello(String name) {
        return "hello," + name;
    }

    @Override
    public boolean addBook(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            addBook(map);
        }
        return true;
        // return allBook.addAll(list);
    }

    @Override
    public void clear() {
        Query q = manager.createNativeQuery("delete from Book");
        int i = q.executeUpdate();
        System.out.println("清空表：" + i + "行已清空");
        allBook.clear();
    }

}
