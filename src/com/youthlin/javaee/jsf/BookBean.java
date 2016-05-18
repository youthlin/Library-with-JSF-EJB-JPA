package com.youthlin.javaee.jsf;

import org.apache.logging.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by lin on 2016-05-09-009. 图书托管bean
 */
@ManagedBean
@SessionScoped
public class BookBean implements Serializable {
    private static final long serialVersionUID = -1349219058675355775L;
    private String name;
    private String isbn;
    private List<String> author;
    private Date publishDate;
    private Float price;
    private TYPE type;
    private SUBTYPE subType;
    private String typeName;
    private String subTypeName;
    private List<SelectItem> types = new ArrayList<>();
    private List<SelectItem> subTypes;
    private List<Map<String, Object>> sessionBookList;
    private List<Map<String, Object>> allBookList;

    private static Logger LOG = MyLog.getLogger(BookBean.class);

    public BookBean() {
        LOG.debug("进入BookBean构造函数");
        types.add(new SelectItem(TYPE.INVALID, TYPE.INVALID.getDisplayName()));
        types.add(new SelectItem(TYPE.COMPUTER, TYPE.COMPUTER.getDisplayName()));
        types.add(new SelectItem(TYPE.LITERATURE, TYPE.LITERATURE
                .getDisplayName()));
        types.add(new SelectItem(TYPE.MANAGEMENT, TYPE.MANAGEMENT
                .getDisplayName()));
        types.add(new SelectItem(TYPE.OTHER, TYPE.OTHER.getDisplayName()));
        updateMyself();
    }

    public void onTypesChange(ValueChangeEvent event) {
        setType((TYPE) event.getNewValue());
    }

    // JSF: 双联菜单（双级联动菜单）范例，源代码
    // http://www.myexception.cn/javascript/673420.html
    public List<SelectItem> getSubTypes() {
        subTypes = new ArrayList<>();
        if (type == null || type.equals(TYPE.INVALID)) {
            subTypes.add(new SelectItem(SUBTYPE.INVALID, "请先选择分类"));
            return subTypes;
        }
        subTypes.add(new SelectItem(SUBTYPE.INVALID, SUBTYPE.INVALID
                .getDisplayName()));
        switch (type) {
            case COMPUTER:
                subTypes.add(new SelectItem(SUBTYPE.COMPUTER_SOFTWARE_ENGINEERING,
                        SUBTYPE.COMPUTER_SOFTWARE_ENGINEERING.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.COMPUTER_NETWORK,
                        SUBTYPE.COMPUTER_NETWORK.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.COMPUTER_PROGRAM_LANGUAGE,
                        SUBTYPE.COMPUTER_PROGRAM_LANGUAGE.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.COMPUTER_OTHER,
                        SUBTYPE.COMPUTER_OTHER.getDisplayName()));
                break;
            case LITERATURE:
                subTypes.add(new SelectItem(SUBTYPE.LITERATURE_STORY,
                        SUBTYPE.LITERATURE_STORY.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.LITERATURE_ESSAY,
                        SUBTYPE.LITERATURE_ESSAY.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.LITERATURE_POETRY,
                        SUBTYPE.LITERATURE_POETRY.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.LITERATURE_OTHER,
                        SUBTYPE.LITERATURE_OTHER.getDisplayName()));
                break;
            case MANAGEMENT:
                subTypes.add(new SelectItem(SUBTYPE.MANAGEMENT_ADMINISTRATION,
                        SUBTYPE.MANAGEMENT_ADMINISTRATION.getDisplayName()));
                subTypes.add(new SelectItem(
                        SUBTYPE.MANAGEMENT_BUSINESS_ADMINISTRATION,
                        SUBTYPE.MANAGEMENT_BUSINESS_ADMINISTRATION.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.MANAGEMENT_FINANCIAL,
                        SUBTYPE.MANAGEMENT_FINANCIAL.getDisplayName()));
                subTypes.add(new SelectItem(SUBTYPE.MANAGEMENT_OTHER,
                        SUBTYPE.MANAGEMENT_OTHER.getDisplayName()));
                break;
            case OTHER:
                subTypes.add(new SelectItem(SUBTYPE.OTHER_NONE, SUBTYPE.OTHER_NONE
                        .getDisplayName()));
            default:
                break;
        }
        return subTypes;
    }

    public void requiredValidator(FacesContext context, UIComponent component,
                                  Object o) throws ValidatorException {
        String str = "" + o;
        if (o == null || str.length() == 0) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "必填项", "此项为必填项");
            throw new ValidatorException(message);
        }

    }

    public void isbnValidator(FacesContext context, UIComponent component,
                              Object o) throws ValidatorException {
        requiredValidator(context, component, o);
        String s = (String) o;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                sb.append(s.charAt(i));
            }
        }
        s = sb.toString();
        if (s.length() != 13) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "ISBN错误", "请检查输入的编号是否为13位");
            throw new ValidatorException(message);
        }
        int sum = 0;
        int tmp;
        for (int i = 0; i < s.length() - 1; i++) {// 前12为参与校验
            tmp = Integer.parseInt(String.valueOf(s.charAt(i)));
            if (i % 2 == 0) {// 第0项是第一位，1为奇数，乘以1
                sum += tmp;
            } else {// 第1项是第二位，2为偶数，乘以3
                sum += tmp * 3;
            }
        }
        sum %= 10;
        sum = 10 - sum;
        if (sum == 10) {
            sum = 0;
        }
        tmp = Integer.parseInt(s.substring(s.length() - 1));
        if (sum != tmp) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "ISBN校验失败",
                    "校验失败,请输入正确的ISBN编码");
            throw new ValidatorException(message);
        }
    }

    public void typeValidator(FacesContext context, UIComponent component,
                              Object o) throws ValidatorException {
        requiredValidator(context, component, o);
        com.youthlin.javaee.jsf.TYPE t = (TYPE) o;
        if (t.equals(TYPE.INVALID)) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "分类是必选的", "请选择分类");
            throw new ValidatorException(message);
        }
    }

    /**
     * 添加一本书，到确认添加界面
     */
    public String addBook() {
        LOG.debug("点击添加按钮");
        return "success";
    }

    /**
     * 确认添加到会话列表
     */
    public String confirm() {
        if (getName() == null || getIsbn() == null || getAuthor() == null
                || getPublishDate() == null || getPrice() == null
                || getType() == null) {
            LOG.debug("没有提交表单，不需添加，返回表单页面");
            return "error";
        }
        boolean result = BookUtil.addBook(this.toMap());
        if (result)
            LOG.debug("添加成功");
        else
            LOG.debug("添加失败");
        LOG.debug("添加图书...");
        LOG.debug("图书名称=" + getName());
        LOG.debug("图书书号=" + getIsbn());
        LOG.debug("图书作者=" + getAuthor());
        LOG.debug("出版时间=" + getPublishDate());
        LOG.debug("图书价格=" + getPrice());
        LOG.debug("图书分类=" + getType() + "(" + getTypeName() + ")");
        LOG.debug("二级分类=" + getSubType() + "(" + getSubTypeName() + ")");
        LOG.debug("...添加完成");
        updateMyself();
        resetForm();
        return "list";
    }

    public String addAll() {
        boolean result = BookUtil.addAll(BookUtil.getList());
        if (result) {
            LOG.debug("添加列表成功");
        } else {
            LOG.debug("添加列表失败");
        }
        updateMyself();
        return "all";
    }

    public String viewList() {
        updateMyself();
        return "list";
    }

    /**
     * 查看所有图书
     */
    public String viewAllBook() {
        updateMyself();
        return "all";
    }

    public String clearList() {
        BookUtil.clearList();
        updateMyself();
        return "list";
    }

    public String clearAll() {
        BookUtil.clearAll();
        updateMyself();
        return "success";
    }

    // 更新列表
    private void updateMyself() {
        sessionBookList = BookUtil.getList();
        allBookList = BookUtil.getAllBook();
    }

    // 重置表单
    private void resetForm() {
        setName(null);
        setIsbn(null);
        setAuthor(null);
        setPublishDate(null);
        setPrice(null);
        setType(TYPE.INVALID);
        setSubType(SUBTYPE.INVALID);
    }

    public String getName() {
        if (name == null || name.length() == 0)
            return null;
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        if (price != null)
            price = (float) (Math.floor(price * 10) / 10);
        this.price = price;
    }

    public String getIsbn() {
        if (isbn == null)
            return null;
        if (isbn.length() == 22 && isbn.startsWith("ISBN ")
                && isbn.charAt(8) == '-' && isbn.charAt(10) == '-'
                && isbn.charAt(15) == '-' && isbn.charAt(20) == '-') {
            return isbn;
        }
        StringBuilder sb = new StringBuilder("ISBN ");
        int len;
        for (int i = 0; i < isbn.length(); i++) {
            if (isbn.charAt(i) >= '0' && isbn.charAt(i) <= '9') {
                sb.append(isbn.charAt(i));
            }
            len = sb.length();
            if (len == 8 || len == 10 || len == 15 || len == 20)
                sb.append('-');
        }
        isbn = sb.toString();
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setSubTypes(List<SelectItem> subTypes) {
        this.subTypes = subTypes;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        if (type == null)
            return;
        this.type = type;
        this.typeName = type.getDisplayName();
    }

    public SUBTYPE getSubType() {
        return subType;
    }

    public void setSubType(SUBTYPE subType) {
        if (subType == null)
            return;
        this.subType = subType;
        this.subTypeName = subType.getDisplayName();
    }

    public List<SelectItem> getTypes() {
        return types;
    }

    public void setTypes(List<SelectItem> types) {
        this.types = types;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public List<Map<String, Object>> getSessionBookList() {
        return sessionBookList;
    }

    public void setSessionBookList(List<Map<String, Object>> sessionBookList) {
        this.sessionBookList = sessionBookList;
    }

    public List<Map<String, Object>> getAllBookList() {
        return allBookList;
    }

    public void setAllBookList(List<Map<String, Object>> allBookList) {
        this.allBookList = allBookList;
    }

    Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("isbn", isbn);
        map.put("author", author);
        map.put("publishDate", publishDate);
        map.put("price", price);
        map.put("type", type.getDisplayName());
        map.put("subType", subType.displayName);
        return map;
    }
}
