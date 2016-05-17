package com.youthlin.javaee.ejb;

import com.youthlin.javaee.jsf.BookUtil;

public class TestClient {
	public static void main(String[] args) {
		AllBookRemote remote = (AllBookRemote) BookUtil
				.getRemote(AllBookRemote.class);
		if(remote!=null){
			System.out.println(remote.sayHello("Lin"));
		}
	}
}
