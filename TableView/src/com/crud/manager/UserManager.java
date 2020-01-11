package com.crud.manager;

import com.crud.bean.UserBean;
import com.crud.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManager {

    Connection connection = null;
    String sql = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ArrayList<UserBean> list = null;
    UserBean userBean = null;
    boolean flag = false;

    public UserManager() {
        connection = new DBConnection().getConnection();
    }

    public ArrayList<UserBean> tableView() {
        list = new ArrayList<UserBean>();
        try {
            sql = "SELECT * FROM user_info";
            ps = connection.prepareStatement(sql);
            //ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                userBean = new UserBean();
                userBean.setName(rs.getString("name"));
                userBean.setMail(rs.getString("email"));
                userBean.setMobile(rs.getString("phone"));
                userBean.setAddress(rs.getString("address"));
                list.add(userBean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<UserBean> loadSearchTable(String text) {
        list = new ArrayList<UserBean>();
        try {
            sql = "SELECT * FROM user_info WHERE name LIKE ? OR phone"
                    + " LIKE ? OR email LIKE ? OR address LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, text);
            ps.setString(2, text);
            ps.setString(3, text);
            ps.setString(4, text);
            rs = ps.executeQuery();
            while (rs.next()) {
                userBean = new UserBean();
                userBean.setName(rs.getString("name"));
                userBean.setMobile(rs.getString("phone"));
                userBean.setMail(rs.getString("email"));
                userBean.setAddress(rs.getString("address"));
                list.add(userBean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public UserBean getUserDetails(String phone) {
        try {
            sql = "SELECT * FROM user_info WHERE phone = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                userBean = new UserBean();
                userBean.setName(rs.getString("name"));
                userBean.setMail(rs.getString("email"));
                userBean.setAddress(rs.getString("address"));
                userBean.setMobile(rs.getString("phone"));
                userBean.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userBean;
    }

    public boolean updateUser(UserBean userBean) {
        try {
            sql = "UPDATE user_info SET username = ?, name = ?, email = ?"
                    + " WHERE phone = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, userBean.getUsername());
            ps.setString(2, userBean.getName());
            ps.setString(3, userBean.getMail());
            ps.setString(4, userBean.getMobile());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean deleteUser(UserBean userBean) {
        try {
            sql = "DELETE FROM user_info WHERE phone = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, userBean.getMobile());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;

    }

    public ArrayList<UserBean> getTable() {
        list = new ArrayList<UserBean>();
        try {
            sql = "SELECT * FROM user_info";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                userBean = new UserBean();
                userBean.setName(rs.getString("name"));
                userBean.setUsername(rs.getString("username"));
                userBean.setMail(rs.getString("email"));
                userBean.setMobile(rs.getString("phone"));
                userBean.setAddress(rs.getString("address"));
                list.add(userBean);
                           }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public UserBean getDetails(String phone) {
        try {
            sql = "SELECT * FROM user_info WHERE phone = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                userBean = new UserBean();
                userBean.setName(rs.getString("name"));
                userBean.setAddress(rs.getString("address"));
                userBean.setMail(rs.getString("email"));
                userBean.setMobile(rs.getString("phone"));
                userBean.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userBean;
    }
                                                                                                                                                    
    public boolean insertUser(UserBean userBean) {
        try {
            sql = "INSERT INTO user_info (username,name,email,phone,address) "
                    + "VALUES (?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, userBean.getUsername());
            ps.setString(2, userBean.getName());
            ps.setString(4, userBean.getMobile());
            ps.setString(3, userBean.getMail());
            ps.setString(5, userBean.getAddress());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}
