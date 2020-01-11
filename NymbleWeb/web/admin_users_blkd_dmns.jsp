<%@page import="com.nymble.bean.mgr.BlockedUserManager"%>
<%@page import="com.nymble.beans.BlockedBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nymble.beans.LoginBean"%>
<%@page import="java.io.File"%>
<%@page import="com.nymble.util.AppConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Nymble&#9679; Home</title>

        <meta name="description" content="..." />
        <meta name="keywords" content="..." />

        <style type="text/css" media="all">
            @import url("css/style.css");
            @import url("css/tables.css");
        </style>

        <!--[if lt IE 8]><style type="text/css" media="all">@import url("css/ie.css");</style><![endif]-->

        <!-- Shared on MafiaShare.net  --><!-- Shared on MafiaShare.net  --></head>
        <%
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Expires", "0");
            response.setDateHeader("Expires", -1);
        %> 

    <%
        LoginBean loginBean = (LoginBean) session.getAttribute(AppConstants.USER_SESSION);
        if (loginBean != null && loginBean.getUserType().equals(AppConstants.TYPE_ADMIN)) {
        } else if (loginBean != null && loginBean.getUserType().equals(AppConstants.TYPE_CLIENT)) {
            response.sendRedirect("client_home.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    %>



    <%
      
        if (loginBean == null) {
            response.sendRedirect("index.jsp");
        }

    %>



    <body>

        <div id="bgc">

            <div class="wrapper">		<!-- wrapper begins -->






                <div id="header">
                    <h1><a href="#"><span>Blur Studio</span></a></h1>

                    <ul>
                        <li><a href="admin_home.jsp" class="active">Home</a></li> 
                        <li><a href="uploadapp.jsp">Application</a></li>
                        <li><a href="account.jsp" >Account</a></li>
                        <li><a href="LogoutServlet?page=index.jsp">Logout</a></li>
                    </ul>
                </div>		<!-- #header ends -->



                <div id="holder" > 

                    <div class="pagetitle">
                        <h2>Welcome admin</h2>

                    </div> 


                    <div id="content" style="height: 450px; overflow: auto;">
                        <%
                            String username = request.getParameter("username");
                            if (username != null) {
                        %>
                        <h3>Blocked sites of <%=username%></h3>


                        <table border="1" summary="Summary Here" cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th style="width: 30px;">No </th>

                                    <th style="width: 350px;">Domain</th>

                                    <th  style="width: 100px" >Option</th>
                                </tr>
                            </thead>


                            <tbody>

                                <%

                                    BlockedUserManager blockedUserManager = new BlockedUserManager();
                                    ArrayList<BlockedBean> blockedDmns = blockedUserManager.getBlocledDomainBeans(username);

                                    for (int i = 0; i < blockedDmns.size(); i++) {
                                        BlockedBean blockedBean = blockedDmns.get(i);

                                %>
                                <tr>


                                    <td > <%= i + 1%></td>
                                    <td style="text-align: center;"><%= blockedBean.getDomain()%></td>
                                    <td style="text-align: center;">  
                                        <a href="view_reqs.jsp?block_id=<%= blockedBean.getId()%>">View Requests</a>
                                        <a href="UnblockUserServlet?block_id=<%= blockedBean.getId()%>&username=<%=username%>">Unblock</a>
                                    </td>

                                </tr>
                                <%}
                                %>

                            </tbody>
                        </table>



                        <%}%>

                    </div>		<!-- #content ends -->




                </div>		<!-- #holder ends -->


                <div id="footer">
                    <p class="left"><a href="#"><span>Blur Studio</span></a></p>
                    <p class="right">Copyright &copy; 2013. Some rights reserved.</p>
                </div>		<!-- #footer ends -->



            </div>		<!-- wrapper ends -->


        </div>







    </body>
</html>