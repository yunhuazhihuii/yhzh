package com.yhzh.zhyq.test;

public class HelloBean {
	private final long id;
    private final String content;

    public HelloBean(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
