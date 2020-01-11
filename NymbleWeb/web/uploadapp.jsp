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


    <body>

        <div id="bgc">

            <div class="wrapper">		<!-- wrapper begins -->






                <div id="header">
                    <h1><a href="#"><span>Blur Studio</span></a></h1>

                    <ul>
                        <li><a href="admin_home.jsp" >Home</a></li>
                        <li><a href="uploadapp.jsp" class="active" >Application</a></li>
                        <li><a href="account.jsp" >Account</a></li>
                        <li><a href="LogoutServlet?page=index.jsp">Logout</a></li>
                    </ul>
                </div>		<!-- #header ends -->










                <div id="holder">





                    <div class="pagetitle">
                        <h2>Application Upload</h2>

                    </div>









                    <div id="content">




                        <div id="main">


                            <h3>Nymble</h3>

                            <blockquote> Upload Nymble Application</blockquote>


                            <div id="respond" style="width:220px;float:left">
                                <form id="update_acc" name="update_acc" action="UploadFileServlet" method="post" enctype="multipart/form-data">
                                    <p>&nbsp;</p>


                                    <p>
                                        <input type="file" name="confirm" id="confirm"  size="22"  value=""/>
                                    </p>
                                    <p>
                                        <input  style=" text-align:center" name="submit" type="submit" id="submit" value="Upload" />

                                    </p>

                                </form>
                            </div>
                            <div id="respond" style="width:320px;float:right">
                                <table summary="Summary Here" cellpadding="0" cellspacing="0">
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
                                            <td style="width: 150px;text-align: center;"><a href="DeleteFileServlet?fileName=<%=datas[i].getName()%>">Delete File </a></td>
                                        </tr>
                                        <%}
                                            }%>


                                    </tbody>
                                </table>

                            </div>






                        </div>		<!-- #main ends -->








                        <div id="side">

                            <p><img src="images/office.jpg" alt="" /></p>
                        </div>		<!-- #side ends -->








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