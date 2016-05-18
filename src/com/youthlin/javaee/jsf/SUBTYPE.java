package com.youthlin.javaee.jsf;

public enum SUBTYPE {
    INVALID("请选择二级分类"),

    COMPUTER_SOFTWARE_ENGINEERING("软件工程"), COMPUTER_NETWORK("计算机网络"), COMPUTER_PROGRAM_LANGUAGE(
            "编程语言"), COMPUTER_OTHER("计算机-其他"),

    LITERATURE_STORY("小说"), LITERATURE_ESSAY("散文"), LITERATURE_POETRY("诗词"), LITERATURE_OTHER(
            "文学-其他"),

    MANAGEMENT_ADMINISTRATION("行政管理"), MANAGEMENT_BUSINESS_ADMINISTRATION(
            "工商管理"), MANAGEMENT_FINANCIAL("金融管理"), MANAGEMENT_OTHER("管理-其他"),

    OTHER_NONE("无");

    String displayName;

    SUBTYPE(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
