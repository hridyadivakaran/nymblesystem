/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.db;


import com.nymble.util.AppConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Staff
 */
public class DBConnectionManager {

    Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            connect();
            return connection;
        } else {
            return connection;
        }
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName(AppConstants.DB_DRIVER);
        connection = DriverManager.getConnection(AppConstants.DB_URL,
                AppConstants.DB_USERNAME, AppConstants.DB_PASSWORD);
    }
}
