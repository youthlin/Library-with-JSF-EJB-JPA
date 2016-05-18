package com.youthlin.javaee.ejb;

import javax.ejb.Remote;

@Remote
public interface LoginCountRemote {
    int getCount();

    void add();

    void add(int i);
}
