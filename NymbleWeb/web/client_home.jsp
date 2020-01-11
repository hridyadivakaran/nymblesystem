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
            response.sendRedirect("index.jsp");
        } else if (loginBean != null && loginBean.getUserType().equals(AppConstants.TYPE_CLIENT)) {
        } else {
            response.sendRedirect("index.jsp");
        }
    %>

    <body> 
        <div id="bgc">

            <div class="wrapper"> 

                <div id="header">
                    <h1>Pseudonym Manager</h1> 

                    <ul>
                        <li><a href="client_home.jsp" class="active">Home</a></li>
                        <li><a href="UserBlockedDomServ">Blocked Domains </a></li>
                        <li><a href="account.jsp" >Account</a></li>

                        <li><a href="LogoutServlet?page=index.jsp">Logout</a></li>
                    </ul>
                </div>		<!-- #header ends -->










                <div id="holder">





                    <div class="pagetitle">
                        <h2>Home</h2>

                    </div>









                    <div id="content" style="height: 400px;">




                        <div id="main">


                            <h3>Nymble</h3>

                            <blockquote>We present a secure system called Nymble, which provides all the following properties: anonymous authentication, backward unlinkability, subjective blacklisting, fast authentication speeds, rate-limited anonymous connections, revocation auditability (where users can verify whether they have been blacklisted), and also addresses</blockquote>






                        </div>		<!-- #main ends -->








                        <div id="side">

                            <br/>
                            <br/>



                            <ul id="twitter_update_list">
                                <li>Download Nymble Application</li></ul>
                            <div id="twitter">


                                <table border="1" summary="Summary Here" cellpadding="0" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th style="width: 30px;">No </th>

                                            <th style="width: 200px;">File</th>
                                            <th  style="width: 200px" >Option</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%
                                            String path = getServletContext().getRealPath(AppConstants.LOCAL_FOLDER);
                                            File f = new File(path);
                                            File[] datas = f.listFiles();
                                            if (datas != null) {
                                                for (int i = 0; i < datas.length; i++) {
                                        %>

                                        <tr  <% if (i % 2 == 0) {%>  <%} else {%> class="dark" <%}%> >
                                            <td ><%=(i + 1)%></td>

                                            <td style="width: 200px;text-align: center;"><%=datas[i].getName()%></td>
                                            <td style="width: 150px;text-align: center;"><a href="DownloadFileServlet?fileName=<%=datas[i].getName()%>">Download File </a></td>
                                        </tr>
                                        <%}
                                            }%>


                                    </tbody>
                                </table>

                            </div>

                        </div>		<!-- #side ends -->








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