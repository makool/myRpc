package com.zjd.idl.domain.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/22 14:32
 * @Version 1.0
 **/
public class Message implements Serializable {

    private String uuid;
    private String title;
    private String content;
    private Date gmtCreate;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}
