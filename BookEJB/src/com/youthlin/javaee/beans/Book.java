package com.youthlin.javaee.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity
public class Book implements Serializable {
    private static final long serialVersionUID = -5403609862802354991L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** 这个主键不能是AUTO，否则出错：Table 'book.hibernate_sequence' doesn't exist
     *  @Link http://stackoverflow.com/a/36086793
     * */
    private int id;
    private String name;
    private String isbn;
    private Date publishDate;
    private Float price;
    private String type;
    private String subType;
    private String author;

    public Book() {
    }

    public Book(String name, String isbn, Date publishDate, Float price,
                String type, String subType, String authors) {
        this.name = name;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.price = price;
        this.type = type;
        this.subType = subType;
        this.author = authors;
    }

    public Book(int id, String name, String isbn, Date publishDate,
                Float price, String type, String subType, String authors) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.price = price;
        this.type = type;
        this.subType = subType;
        this.author = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAuthors() {
        return author;
    }

    public void setAuthors(String authors) {
        this.author = authors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("isbn", isbn);
        map.put("author", author);
        map.put("publishDate", publishDate);
        map.put("price", price);
        map.put("type", type);
        map.put("subType", subType);
        return map;
    }
}
