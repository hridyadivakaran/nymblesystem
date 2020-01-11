/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.beans;

/**
 *
 * @author Administrator
 */
public class UserPseudoBean {

    private String id = "";
    private String username = "";
    private String pseudonym = "";

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
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
