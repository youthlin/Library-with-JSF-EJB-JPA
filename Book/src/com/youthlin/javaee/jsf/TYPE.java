package com.youthlin.javaee.jsf;

/**
 * Created by lin on 2016-05-09-009. 分类及子分类
 */
public enum TYPE {
	INVALID("请选择分类"), COMPUTER("计算机"), LITERATURE("文学"), MANAGEMENT("管理"), OTHER(
			"其他");

	public String displayName;

	TYPE(String name) {
		this.displayName = name;
	}

	public String getDisplayName() {
		return displayName;
	}

}
