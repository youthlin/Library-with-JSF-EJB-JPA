package com.youthlin.javaee.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ManagedBean
@SessionScoped
public class AdminBean {
    private String username;
    private String password;
    private String msg;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private static Logger LOG = MyLog.getLogger(AdminBean.class);

    public String login() {
        String adminProperties = "/admin.properties";
        InputStream in = getClass().getResourceAsStream(adminProperties);
        Properties properties = new Properties();
        String username;
        String password;
        try {
            LOG.debug("获取管理员配置文件" + in);
            properties.load(in);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            LOG.debug("读取管理员配置文件完成\tusername=" + username + ",password=" + password);
        } catch (IOException e) {
            username = "lin";
            password = "1234";
            LOG.debug("读取管理员配置文件失败，使用默认用户名与密码\tusername=" + username + ",password=" + password);
            e.printStackTrace();
        }
        msg = "";
        if (username.equals(getUsername()) && password.equals(getPassword())) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSession(true);
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("login", this);
            // 登录次数加一
            BookUtil.addCount();
            int count = BookUtil.getLoginCount();
            LOG.debug("登录成功,共登录" + count + "次");
            setCount(count);
            return "success";
        }
        msg = "用户名或密码错误";
        LOG.debug("登录失败");
        return "login";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put("login", null);
        return "login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
