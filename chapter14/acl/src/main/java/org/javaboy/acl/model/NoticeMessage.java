package org.javaboy.acl.model;


public class NoticeMessage {
    private Integer id;
    private String content;

    @Override
    public String toString() {
        return "NoticeMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}