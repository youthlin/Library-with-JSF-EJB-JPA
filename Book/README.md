## Book Web 模块
目录结构：
<pre>
/
├─src
│  │  jboss-ejb-client.properties                          *用于连接远程EJB接口*
│  │  log4j2.xml                                           *日志配置文件*
│  │
│  ├─com
│  │  └─youthlin
│  │      └─javaee
│  │          ├─ejb
│  │          │      AllBookRemote.java                    *EJB接口*
│  │          │      BookListRemote.java
│  │          │      LoginCountRemote.java
│  │          │      TestClient.java
│  │          │      ViewBookRemote.java
│  │          │
│  │          └─jsf
│  │              │  AdminBean.java                        *登录*
│  │              │  AuthorConverter.java                  *JSF 的自定义转换器，字符串->列表*
│  │              │  BookBean.java                         *接收 JSF 页面的表单信息*
│  │              │  BookUtil.java                         *工具类，用于实现各种操作*
│  │              │  MyLog.java                            *用于获取Log*
│  │              │  SUBTYPE.java                          *图书二级分类，枚举*
│  │              │  TYPE.java                             *图书分类，枚举*
│  │              │
│  │              └─filter
│  │                      LoginCheckFilter.java            *登录过滤器*
│  │                      SetCharacterEncodingFilter.java  *字符编码转换过滤器*
│  │
│  └─META-INF
└─WebContent
    │  added.xhtml                                         *显示会话列表*
    │  all.xhtml                                           *显示所有图书*
    │  confirm.xhtml                                       *确认表单信息*
    │  index.xhtml                                         *填写图书表单，实现了 Ajax 级联菜单*
    │  login.xhtml                                         *管理员登录*
    │
    ├─dist                                                 *Bootstrap 框架*
    │  ├─css
    │  │      bootstrap-theme.css
    │  │      bootstrap-theme.css.map
    │  │      bootstrap-theme.min.css
    │  │      bootstrap.css                                *增加了.well, .form-control透明*
    │  │      bootstrap.css.map
    │  │      bootstrap.min.css
    │  │
    │  ├─fonts
    │  │      glyphicons-halflings-regular.eot
    │  │      glyphicons-halflings-regular.svg
    │  │      glyphicons-halflings-regular.ttf
    │  │      glyphicons-halflings-regular.woff
    │  │      glyphicons-halflings-regular.woff2
    │  │
    │  └─js
    │          bootstrap.js
    │          bootstrap.min.js
    │          jquery-2.1.4.min.js                        *增加了使居中的效果*
    │
    ├─META-INF
    │      MANIFEST.MF
    │
    └─WEB-INF
        │  faces-config.xml                               *定义跳转规则*
        │  web.xml                                        *web.xml*
        │
        └─lib
                jboss-client.jar                          *调用 EJB 要用*
                log4j-api-2.5.jar
                log4j-core-2.5.jar
                log4j-web-2.5.jar

</pre>
