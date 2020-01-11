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

public class QueryManager {

    Connection connection = null;
    ArrayList<UserBean> list = null;
    String sql = null;
    PreparedStatement ps = null;
    boolean flag = false;
    UserBean userBean = null;
    ResultSet rs = null;

    public QueryManager() {
        connection = new DBConnection().getConnection();
    }

    public ArrayList<UserBean> loadTable() {
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
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public UserBean getUserDetails(String phone) {
        try {
            sql = "SELECT * FROM user_info WHERE phone = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                userBean = new UserBean();
                userBean.setName(rs.getString("name"));
                userBean.setUsername(rs.getString("username"));
                userBean.setMail(rs.getString("email"));
                userBean.setMobile(rs.getString("phone"));
                userBean.setAddress(rs.getString("address"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userBean;
    }

    public boolean updateUser(UserBean userBean) {
        try {
            sql = "UPDATE user_info SET username = ?, name = ?,email = ?,"
                    + "address = ? WHERE phone = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, userBean.getUsername());
            ps.setString(2, userBean.getName());
            ps.setString(3, userBean.getMail());
            ps.setString(4, userBean.getAddress());
            ps.setString(5, userBean.getMobile());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public ArrayList<UserBean> readTable() {
        try {
            list = new ArrayList<UserBean>();
            sql = "SELECT * FROM user_info";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                userBean = new UserBean();
                userBean.setName(rs.getString("name"));
                System.out.println("name " + rs.getString("name"));
                userBean.setMail(rs.getString("email"));
                System.out.println("email is " + rs.getString("email"));
                userBean.setMobile(rs.getString("phone"));
                System.out.println("The phone is " + rs.getString("phone"));
                userBean.setAddress(rs.getString("address"));
                System.out.println("THe address is " + rs.getString("address"));
                list.add(userBean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
