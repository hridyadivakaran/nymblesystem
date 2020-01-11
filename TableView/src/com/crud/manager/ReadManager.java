/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Staff
 */
public class ReadManager {
    ArrayList<UserBean> list =null;
    UserBean userBean = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;
    public ArrayList<UserBean> tableLoader() {
        try {
            connection = new DBConnection().getConnection();
            list = new ArrayList<UserBean>();
            String sql = "SELECT * FROM user_info";
            ps = connection.prepareStatement(sql);
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
            Logger.getLogger(ReadManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
