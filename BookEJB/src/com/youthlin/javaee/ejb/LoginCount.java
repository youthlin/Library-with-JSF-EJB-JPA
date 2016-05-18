package com.youthlin.javaee.ejb;

import javax.ejb.Singleton;

/**
 * Session Bean implementation class LoginCount
 */
@Singleton
public class LoginCount implements LoginCountRemote {
    private int count = 0;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void add() {
        count++;
    }

    @Override
    public void add(int i) {
        count += i;
    }
}
