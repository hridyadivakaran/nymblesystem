
package com.crud.manager;

import com.crud.bean.LoginBean;
import com.crud.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager {
    Connection connection = null;
    boolean flag = false;
    public boolean checkLogin(LoginBean loginBean) {
        try {
            connection = new DBConnection().getConnection();
            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, loginBean.getUsername());
            ps.setString(2, loginBean.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
        
    }
    
}
