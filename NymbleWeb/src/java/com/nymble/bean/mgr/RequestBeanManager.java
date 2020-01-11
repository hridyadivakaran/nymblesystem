/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.bean.mgr;

import com.nymble.beans.RequestBean;
import com.nymble.db.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class RequestBeanManager {

    private Connection connection = null;

    public RequestBeanManager() throws ClassNotFoundException, SQLException {
        connection = new DBConnectionManager().getConnection();
    }

    public void insertRequest(RequestBean requestBean) throws SQLException {
        String query = "INSERT into requests (blocked_domain_id, subject, content) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, requestBean.getBlockID());
        ps.setString(2, requestBean.getSubject());
        ps.setString(3, requestBean.getContent());
        ps.executeUpdate();
    }

    public ArrayList<RequestBean> getALLRequestBeansFor(String blockId) throws SQLException {
        ArrayList<RequestBean> arrayList = new ArrayList<RequestBean>();
        String query = "SELECT id, subject, content FROM requests WHERE blocked_domain_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, blockId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            RequestBean requestBean = new RequestBean();
            requestBean.setId(rs.getString("id"));
            requestBean.setSubject(rs.getString("subject"));
            requestBean.setContent(rs.getString("content"));
            arrayList.add(requestBean);
        }
        return arrayList;
    }

    public void removeReq(String reqid) throws SQLException {
        String query = "DELETE FROM requests WHERE id = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, reqid);
        ps.executeUpdate();
    }
}
