/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.beans;

/**
 *
 * @author Administrator
 */
public class RequestBean {

    private String subject = "";
    private String url = "";
    private String content = "";
    private String id = "";
    private String blockID = "";

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
