## Book EJB 模块
<pre>
/
└─ejbModule
    ├─com
    │  └─youthlin
    │      └─javaee
    │          ├─beans
    │          │      Book.java               *保存在数据库中的实体*
    │          │
    │          └─ejb
    │                  AllBook.java           *添加图书(实例化 Book 类并持久化到数据库中)，获取所有图书*
    │                  AllBookRemote.java
    │                  BookList.java          *实验二要求二 用于保存每个会话的列表*
    │                  BookListRemote.java
    │                  LoginCount.java        *实验二要求三 用于记录管理员登录次数*
    │                  LoginCountRemote.java
    │                  ViewBook.java          *实验二要求一 用于获取所有图书*
    │                  ViewBookRemote.java
    │
    └─META-INF
            ejb-jar.xml
            MANIFEST.MF
            persistence.xml                   *配置文件，使用的是JTA方式，JNDI数据源*

</pre>