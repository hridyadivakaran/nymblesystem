/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topk.bean;

import java.io.Serializable;

/**
 *
 * @author Harris Bastin
 */
public class LoginBean implements Serializable {
    
   public static final long serialVersionUID = 14236542L;
   
   private String username = "";
   private String password = "";
   private String userType = "";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
