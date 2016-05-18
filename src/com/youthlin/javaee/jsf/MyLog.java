package com.youthlin.javaee.jsf;

/**
 * Created by lin on 2016-05-12-012.
 * 获取Logger, 方便以后更改日志组件
 */
public class MyLog {
    public static org.apache.logging.log4j.Logger getLogger() {
        return org.apache.logging.log4j.LogManager.getLogger();
    }

    public static org.apache.logging.log4j.Logger getLogger(String str) {
        return org.apache.logging.log4j.LogManager.getLogger(str);
    }

    public static org.apache.logging.log4j.Logger getLogger(Class<?> clz) {
        return org.apache.logging.log4j.LogManager.getLogger(clz);
    }
}
