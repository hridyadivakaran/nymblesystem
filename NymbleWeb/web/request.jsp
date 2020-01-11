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
                $("#update").validate({
                    rules: {
                        subject:{
                            required: true
                        },
                        expln: {
                            required: true,
                            minlength: 5
                        }  
                    },
                    messages: {
                         
                        subject: {
                            required: "<br/>Please provide subject"
                        },
                        expln: {
                            required: "<br/>Please provide an explanation",
                            minlength: "<br/>Your explanation must be at least 5 characters long" 
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
                        <li><a href="client_home.jsp">Home</a></li>
                        <li><a href="UserBlockedDomServ"  class="active">Blocked Domains </a></li>
                        <li><a href="account.jsp" >Account</a></li>  

                        <li><a href="LogoutServlet?page=index.jsp">Logout</a></li>
                    </ul>
                </div>		<!-- #header ends -->










                <div id="holder" style="margin-left: 150px;margin-right: 150px; height: 550px;">





                    <div class="pagetitle" style="text-align: right; font-size: 25px;">
                        Request To Unblock
                    </div> 


                    <div id="content">

                        <h2>Welcome <%= loginBean.getUsername()%> </h2>
                        <%

                            String blockedID = request.getParameter("block_id");
                            String url = request.getParameter("url");



                        %>



                        <div id="main">
                            <div id="respond">
                                <%if (loginBean != null) {%>
                                <form id="update" name="update_acc" action="RequestSaveServlet" method="post">
                                    <p>
                                        <label for="name"> Username : </label><br/>
                                        <input type="text" disabled="disabled" name="username" id="username" 
                                               size="22" value="<%=loginBean.getUsername()%>"/>
                                        <input type="hidden" name="username" id="username"   size="22" value="<%=loginBean.getUsername()%>"/>

                                    </p>


                                    <p>
                                        <label for="name"> Subject* : </label><br/>
                                        <input type="text" name="subject" id="password"  size="22"
                                               value="" style="width: 400px;"/>
                                        <% if (blockedID != null) {%>
                                        <input type="hidden" name="blocked_id" value="<%= blockedID%>" />
                                        <%}%>

                                    </p>
                                    <p>
                                        <label for="name"> url : </label><br/>
                                        <% if (url != null) {%>
                                        <input type="hidden" name="url" value="<%= url%>"/>
                                        <input type="text" disabled="disabled" name="" value="<%= url%>" style="width: 400px;"/>
                                        <%}%>

                                    </p>
                                    <p>
                                        <label for="name"> Content* : </label><br/>
                                        <textarea id="expln" name="expln"> </textarea>

                                    </p>

                                    <p>
                                        <input name="submit" type="submit" id="submit" value="Submit Form"  class="myButton"/>
                                        <input name="reset" type="reset" id="reset" tabindex="5" value="Reset Form"  class="myButton"/>


                                        <%
                                            String regResp = request.getParameter("insert");
                                            if (regResp != null && regResp.equals("false;")) {
                                        %>
                                        Failed to Update
                                        <%                                                } else if (regResp != null) {
                                        %> Successfully Posted<%                                            }
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