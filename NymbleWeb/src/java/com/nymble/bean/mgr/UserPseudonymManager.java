/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.bean.mgr;

import java.sql.PreparedStatement;
import com.nymble.beans.UserPseudoBean;
import com.nymble.db.DBConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class UserPseudonymManager {

    private Connection connection = null;

    public UserPseudonymManager() throws ClassNotFoundException, SQLException {
        connection = new DBConnectionManager().getConnection();

    }

    public void insertNewPseudoNym(UserPseudoBean userPseudoBean) throws SQLException {
        String chkQUery = "SELECT id FROM user_pseudo WHERE username = ? AND pseudonym = ? ";
        PreparedStatement chkPs = connection.prepareStatement(chkQUery);
        chkPs.setString(1, userPseudoBean.getUsername());
        chkPs.setString(2, userPseudoBean.getPseudonym());
        ResultSet rs = chkPs.executeQuery();
        if (!rs.next()) {
            String insertQuery = "INSERT into user_pseudo (username,  pseudonym)  VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setString(1, userPseudoBean.getUsername());
            ps.setString(1, userPseudoBean.getPseudonym());
            ps.executeUpdate();
        } else {
            // do nothing
        }
    }
}
