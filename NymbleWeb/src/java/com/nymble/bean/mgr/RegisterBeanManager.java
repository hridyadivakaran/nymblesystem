/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.bean.mgr;

import com.nymble.beans.RegisterBean;
import com.nymble.db.DBConnectionManager;
import com.nymble.util.AppConstants;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Staff
 */
public class RegisterBeanManager {

    private Connection connection = null;

    public RegisterBeanManager() throws ClassNotFoundException, SQLException {
        connection = new DBConnectionManager().getConnection();
    }

    public boolean registerClient(RegisterBean registerBean) throws SQLException {
        Connection con = connection;
        con.setAutoCommit(false);
        String query = "INSERT into user_login (username, user_password, user_type)"
                + " VALUES (?, ?, ?);";
        PreparedStatement psForLogin = con.prepareStatement(query);
        psForLogin.setString(1, registerBean.getUsername());
        psForLogin.setString(2, registerBean.getPassword());
        psForLogin.setString(3, AppConstants.TYPE_CLIENT);
        //psForLogin.setString(4, registerBean.getPseudonym());
        if (psForLogin.executeUpdate() > 0) {
            String regQuery = "INSERT into client_detais "
                    + "(username, first_name, last_name, email_id, phone, address,"
                    + " district,state, nationality)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement psForReg = con.prepareStatement(regQuery);
            psForReg.setString(1, registerBean.getUsername());
            psForReg.setString(2, registerBean.getFirstName());
            psForReg.setString(3, registerBean.getLastName());
            psForReg.setString(4, registerBean.getEmail());
            psForReg.setString(5, registerBean.getPhone());
            psForReg.setString(6, registerBean.getAddress());
            psForReg.setString(7, registerBean.getDistrict());
            psForReg.setString(8, registerBean.getState());
            psForReg.setString(9, registerBean.getNationality());
            if (psForReg.executeUpdate() > 0) {
                con.commit();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<RegisterBean> getAllClientInfos() throws SQLException {
        String query = "SELECT username, first_name, last_name, email_id, phone "
                + "FROM client_detais;";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ArrayList<RegisterBean> allUsers = new ArrayList<RegisterBean>();
        while (rs.next()) {
            RegisterBean registerBean = new RegisterBean();
            registerBean.setUsername(rs.getString("username"));
            registerBean.setFirstName(rs.getString("first_name"));
            registerBean.setLastName(rs.getString("last_name"));
            registerBean.setEmail(rs.getString("email_id"));
            registerBean.setPhone(rs.getString("phone"));
            allUsers.add(registerBean);
        }

        return allUsers;
    }

    public RegisterBean getAllinfo(String username) throws SQLException {
        String query = "SELECT username, first_name, last_name, email_id, "
                + "phone, address, district, state, nationality "
                + "FROM client_detais WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            RegisterBean registerBean = new RegisterBean();
            registerBean.setUsername(resultSet.getString("username"));
            registerBean.setFirstName(resultSet.getString("first_name"));
            registerBean.setLastName(resultSet.getString("last_name"));
            registerBean.setEmail(resultSet.getString("email_id"));
            registerBean.setPhone(resultSet.getString("phone"));
            registerBean.setAddress(resultSet.getString("address"));
            registerBean.setDistrict(resultSet.getString("district"));
            registerBean.setState(resultSet.getString("state"));
            registerBean.setNationality(resultSet.getString("nationality"));
            return registerBean;
        } else {
            return null;
        }
    }

    public boolean updateUserDetails(RegisterBean registerBean) throws SQLException {
        String query = "UPDATE client_detais SET first_name = ?, last_name =  ?,"
                + " email_id = ?, phone = ?, address = ?, district = ?,"
                + " state = ?, nationality = ? WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, registerBean.getFirstName());
        ps.setString(2, registerBean.getLastName());
        ps.setString(3, registerBean.getEmail());
        ps.setString(4, registerBean.getPhone());
        ps.setString(5, registerBean.getAddress());
        ps.setString(6, registerBean.getDistrict());
        ps.setString(7, registerBean.getState());
        ps.setString(8, registerBean.getNationality());
        ps.setString(9, registerBean.getUsername());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
/*
 *
 * INSERT into user_login (username, user_password, user_type) VALUES ('a', 'a',
 * 'a');
 *
 *
 * INSERT into client_detais (username, first_name, last_name, email_id, phone,
 * address, district, nationality) VALUES('a', 'a', 'a', 'a', 'a', 'a', 'a',
 * 'a')
 *
 *
 * UPDATE client_detais SET first_name = '', last_name = '', email_id = '',
 * phone = '', address = '', district = '', state = '', nationality = '' WHERE
 * username = 'a'
 *
 *
 *
 */
