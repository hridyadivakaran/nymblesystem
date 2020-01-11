/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.beans;

/**
 *
 * @author user
 */
public class BlockedBean {

    private String id = "";
    private String username = "";
    private String domain = "";
    private String macID = "";

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMacID() {
        return macID;
    }

    public void setMac(String macID) {
        this.macID = macID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
