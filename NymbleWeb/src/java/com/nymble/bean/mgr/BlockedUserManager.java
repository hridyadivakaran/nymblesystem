/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.bean.mgr;

import com.nymble.beans.BlockedBean;
import com.nymble.beans.LoginBean;
import com.nymble.beans.UserBean;
import com.nymble.db.DBConnectionManager;
import com.nymble.util.AppConstants;
import com.nymble.util.DecryptEncryptString;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author user
 */
public class BlockedUserManager {

    private Connection connection = null;

    public BlockedUserManager() {
        try {
            connection = new DBConnectionManager().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlockedUserManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlockedUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertBlockedDomain(String pseudonym, String domain) {
        try {
            Connection con = connection;

            String pseudonymQuery = "Select username From user_login where pseudonym = ?";
            PreparedStatement psForUsername = connection.prepareStatement(pseudonymQuery);
            psForUsername.setString(1, pseudonym);
            ResultSet rsForUsername = psForUsername.executeQuery();
            if (rsForUsername.next()) {
                String username = rsForUsername.getString("username");
                String queryForCount = "SELECT count FROM blocked_domain WHERE"
                        + " username = ? AND domain = ? AND pseudonym = ?; ";
                PreparedStatement psForCount = connection.prepareStatement(queryForCount);
                psForCount.setString(1, username);
                psForCount.setString(2, domain);
                psForCount.setString(3, pseudonym);
                ResultSet rsForCount = psForCount.executeQuery();
                if (rsForCount.next()) {
                    int count = rsForCount.getInt("count");
                    String query = "UPDATE blocked_domain SET count = ? WHERE username = ? "
                            + "AND pseudonym = '' AND domain = ?";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setInt(1, count + 1);
                    ps.setString(2, username);
                    ps.setString(3, pseudonym);
                    ps.setString(4, domain);
                    if (ps.executeUpdate() > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {

                    String insertQuery = "INSERT into blocked_domain (pseudonym, domain, username, count) VALUES (?, ?, ?, ?)";
                    PreparedStatement forInsert = con.prepareStatement(insertQuery);
                    forInsert.setString(1, pseudonym);
                    forInsert.setString(2, domain);
                    forInsert.setString(3, username);
                    forInsert.setInt(4, 1);
                    if (forInsert.executeUpdate() > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlockedUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<String> getDomains(LoginBean loginBean) throws SQLException {
        Connection con = connection;
        String query = "Select domain from blocked_domain where pseudonym = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, loginBean.getPseudonym());
        ResultSet rs = ps.executeQuery();

        ArrayList<String> domains = new ArrayList<String>();
        while (rs.next()) {
            String domain = rs.getString("domain");
            domains.add(domain);
        }
        return domains;
    }

    public boolean removeBlocking(String blockId) {
        boolean delete = false;
        try {
            String query = "DELETE FROM blocked_domain WHERE id = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, blockId);
            int result = ps.executeUpdate();
            if (result > 0) {
                delete = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlockedUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return delete;
    }

    public ArrayList<String> getAllBlockedDomainsFor(UserBean userBean) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        ArrayList<String> domainNames = new ArrayList<String>();
        String passwordQuery = "SELECT user_password FROM user_login WHERE username = ? ";
        PreparedStatement psForPassword = connection.prepareStatement(passwordQuery);
        psForPassword.setString(1, userBean.getUsername());
        ResultSet rsForPassword = psForPassword.executeQuery();
        if (rsForPassword.next()) {
            if (AppConstants.BLOCK_SYSTEM) {
                // for blocking user and system
                String password = rsForPassword.getString("user_password");
                DecryptEncryptString des = new DecryptEncryptString();
                String pseudoNym = des.encrypt(userBean.getMacAddress(), password);
                String query = "SELECT domain, count FROM blocked_domain WHERE username = ? OR  pseudonym = ? ";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, userBean.getUsername());
                ps.setString(2, pseudoNym);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int count = rs.getInt("count");
                    if (count == 3) {
                        domainNames.add(rs.getString("domain"));
                    }
                }
            } else {
                // for blocking user
                String password = rsForPassword.getString("user_password");
                DecryptEncryptString des = new DecryptEncryptString();
                String pseudoNym = des.encrypt(userBean.getMacAddress(), password);
                String query = "SELECT domain FROM blocked_domain WHERE username = ? ";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, userBean.getUsername());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    domainNames.add(rs.getString("domain"));
                }
            }
        }
        return domainNames;
    }

    public ArrayList<BlockedBean> getBlocledDomainBeans(String username) throws SQLException {

        ArrayList<BlockedBean> blockedDoms = new ArrayList<BlockedBean>();
        String passwordQuery = "SELECT user_password FROM user_login WHERE username = ? ";
        PreparedStatement psForPassword = connection.prepareStatement(passwordQuery);
        psForPassword.setString(1, username);
        ResultSet rsForPassword = psForPassword.executeQuery();
        if (rsForPassword.next()) {
            String query = "SELECT id, domain, count,  pseudonym FROM blocked_domain WHERE username = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count == 3) {
                    BlockedBean blockedBean = new BlockedBean();
                    blockedBean.setUsername(username);
                    blockedBean.setId(rs.getString(1));
                    blockedBean.setDomain(rs.getString(2));
                    blockedDoms.add(blockedBean);
                }
            }
        }
        return blockedDoms;

    }
}
