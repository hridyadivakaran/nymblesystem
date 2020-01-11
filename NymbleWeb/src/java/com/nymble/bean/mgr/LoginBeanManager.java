/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.bean.mgr;

import com.nymble.beans.LoginBean;
import com.nymble.db.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Staff
 */
public class LoginBeanManager {

    private Connection connection = null;

    public LoginBeanManager() throws ClassNotFoundException, SQLException {
        connection = new DBConnectionManager().getConnection();
    }

    public String checkLogin(LoginBean loginBean) throws SQLException {
        String userType = "";
        String query = "SELECT user_type "
                + "FROM user_login WHERE username = ? AND user_password = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, loginBean.getUsername());
        ps.setString(2, loginBean.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            userType = rs.getString("user_type");
        }
        rs.close();
        return userType;
    }

    public boolean setPseudonym(LoginBean loginBean, String pseudonym) throws SQLException {
        String query = "UPDATE user_login SET pseudonym  = ? "
                + "WHERE  username = ? AND user_password = ?; ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, pseudonym);
        ps.setString(2, loginBean.getUsername());
        ps.setString(3, loginBean.getPassword());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getPseudonym(LoginBean loginBean) throws SQLException {
        String query = "SELECT pseudonym "
                + "FROM user_login WHERE username = ? AND user_password = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, loginBean.getUsername());
        ps.setString(2, loginBean.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("pseudonym");
        } else {
            return "";
        }
    }

    public boolean canInsert(String username) throws SQLException {
        String query = "SELECT * "
                + "FROM user_login WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return false;
        } else {
            return true;
        }
    }

    /*
     *  To Update password user has to replace all pseudonym with new pseudonym in all tables
     *  and then update  user_login
     *  
     */
    public boolean updateAccount(LoginBean loginBean, String newPassword) throws SQLException {

        boolean update = false;
        // obtain the pseudonym query
        String pseudoQuery = "SELECT pseudonym FROM user_login WHERE username = ? AND user_password = ? ";
        PreparedStatement psForPseudo = connection.prepareStatement(pseudoQuery);
        psForPseudo.setString(1, loginBean.getUsername());
        psForPseudo.setString(2, loginBean.getPassword());
        ResultSet rsForPseudo = psForPseudo.executeQuery();
        if (rsForPseudo.next()) {
            String pseudonym = rsForPseudo.getString("pseudonym");
            // generate new pseudonym

            String query = "UPDATE user_login SET user_password  = ? "
                    + "WHERE  username = ? AND user_password = ?; ";
            PreparedStatement psForUpdateAcc = connection.prepareStatement(query);
            psForUpdateAcc.setString(1, newPassword);
            psForUpdateAcc.setString(2, loginBean.getUsername());
            psForUpdateAcc.setString(3, loginBean.getPassword());
            if (psForUpdateAcc.executeUpdate() > 0) {
                update = true;
            }
        }

        return update;
    }
}
