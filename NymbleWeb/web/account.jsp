<%@page import="com.nymble.beans.LoginBean"%>
<%@page import="com.nymble.util.AppConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Nymble &#9679;Account</title>

        <meta name="description" content="..." />
        <meta name="keywords" content="..." />

        <style type="text/css" media="all">
            @import url("css/style.css");

        </style>

        <script src="jquery/jquery.js" type="text/javascript"></script>
        <script src="jquery/jquery.validate.js" type="text/javascript"></script>

        <script type="text/javascript">

            $().ready(function() {
                // validate the comment form when it is submitted
                

                // validate signup form on keyup and submit
                $("#update_acc").validate({
                    rules: {
                        password:{
                            required: true
                        },
                        new_password: {
                            required: true,
                            minlength: 5
                        },
                        confirm: {
                            required: true,
                            minlength: 5,
                            equalTo: "#new_password"
                        }  
                    },
                    messages: {
                         
                        new_password: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 5 characters long"
                        },
                        confirm: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 5 characters long",
                            equalTo: "Please enter the same password as above"
                        }
                    }
                });             
            });
        </script>

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
        } else {
            response.sendRedirect("index.jsp");
        }
    %>

    <body>

        <div id="bgc">

            <div class="wrapper">		<!-- wrapper begins -->






                <div id="header">
                    <h1><a href="#"><span>Blur Studio</span></a></h1>
                    <%   if (loginBean != null) {%>
                    <ul> <% if (loginBean.getUserType().equals(AppConstants.TYPE_ADMIN)) {%>
                        <li><a href="admin_home.jsp">Home</a></li>

                        <li><a href="uploadapp.jsp">Application</a></li>
                        <%} else {%>
                        <li><a href="client_home.jsp">Home</a></li>
                        <li><a href="blocked_domains.jsp">Blocked Domains </a></li>
                        <% }%>
                        <li><a href="account.jsp" class="active">Account</a></li>

                        <li><a href="LogoutServlet?page=index.jsp">Logout</a></li>

                    </ul>
                    <%}%>
                </div>		<!-- #header ends --> 

                <div id="holder" style="margin-left: 150px;margin-right: 150px; height: 450px;">





                    <div class="pagetitle" style="text-align: right; font-size: 25px;">
                        Update Account
                    </div> 


                    <div id="content">




                        <div id="main">
                            <div id="respond">
                                <%if (loginBean != null) {%>
                                <form id="update_acc" name="update_acc" action="UpdateAccountServlet" method="post">
                                    <p>
                                        <label for="name"> Username : </label><br/>
                                        <input type="text" disabled="disabled" name="username" id="username" 
                                               size="22" value="<%=loginBean.getUsername()%>"/>
                                        <input type="hidden" name="username" id="username"   size="22" value="<%=loginBean.getUsername()%>"/>

                                    </p>


                                    <p>
                                        <label for="name"> Password* : </label><br/>
                                        <input type="password" name="password" id="password"  size="22"
                                               value=""/>

                                    </p>
                                    <p>
                                        <label for="name"> New Password* : </label><br/>
                                        <input type="password" name="new_password" id="new_password"  size="22" 
                                               value=""/>

                                    </p>
                                    <p>
                                        <label for="name"> Confirm* : </label><br/>
                                        <input type="password" name="confirm" id="confirm"  size="22"  value=""/>
                                        <input type="hidden" name="src" id="confirm"  size="22"  value="account.jsp"/>

                                    </p>
                                    <p>
                                        <input name="submit" type="submit" id="submit" value="Submit Form" class="myButton" />
                                        <input name="reset" type="reset" id="reset" tabindex="5" value="Reset Form" class="myButton"/>
<br/>

                                        <%
                                            String regResp = request.getParameter("insert");
                                            if (regResp != null && regResp.equals("false;")) {
                                        %>
                                        Failed to Update
                                        <%                                                } else if (regResp != null) {
                                        %> successfully Updated<%                                            }
                                        %>
                                    </p>

                                </form>
                            </div>
                            <%}%>
                        </div>		<!-- #main ends --><!-- #side ends -->








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