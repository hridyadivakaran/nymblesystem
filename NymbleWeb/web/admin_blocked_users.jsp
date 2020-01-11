<%@page import="com.nymble.beans.BlockedBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nymble.bean.mgr.BlockedUserManager"%>
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










                <div id="holder"> 
                    <div class="pagetitle">
                        <h2>Admin Home - All Users</h2>

                    </div> 

                    <div id="content" align="center" style="height:400px;">


                        <table border="5">
                            <thead>
                                <tr>
                                    <th width="20">SL</th>
                                    <th width="150">Username</th>
                                    <th width="200">Name</th>
                                    <th width="200">Email</th>
                                    <th width="200">Option</th>


                                </tr>
                            </thead>

                            <tbody>

                                <tr>
                                    <td> </td>
                                    <td> </td>
                                    <td> </td>
                                    <td> </td>
                                    <td> <a href="">Remove</a></td>


                                </tr>

                            </tbody>
                        </table>




                    </div>		<!-- #content ends -->




                </div>		<!-- #holder ends -->








                <!-- #logos ends -->










                <div id="footer">
                    <p class="left"><a href="#"><span>Blur Studio</span></a></p>
                    <p class="right">Copyright &copy; 2013. Some rights reserved.</p>
                </div>		<!-- #footer ends -->



            </div>		<!-- wrapper ends -->


        </div>








    </body>
</html>