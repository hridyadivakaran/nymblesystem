/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.beans;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class UserBean implements Serializable {

    private String macAddress = "";
    private String domain = "";
    private String username = ""; 

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String ip) {
        this.macAddress = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
