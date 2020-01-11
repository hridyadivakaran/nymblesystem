/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.util;

import java.io.File;

/**
 *
 * @author Staff
 */
public interface AppConstants {

    String DB_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/nymble_db";
    String DB_USERNAME = "root";
    String DB_PASSWORD = "mysql";
    /************* mail settings ********************/
    String LOCAL_FOLDER = "www" + File.separator + "resources";
    /**************** Session variables *************/
    String TYPE_ADMIN = "admin";
    String TYPE_CLIENT = "client";
    String USER_SESSION = "user_session";
    /**************** Request Attributes *************/
    String  ALL_USERS = "ALL_USERS";
    String  USR_BLKD_DOM = "USER_BLK_DOM";
    /*
     *  To Mension working type
     *  
     *  The Working type parameter decides whether user has to be blocked or  
     *  Sysem to be blocked
     */

    boolean BLOCK_SYSTEM = false;
}
