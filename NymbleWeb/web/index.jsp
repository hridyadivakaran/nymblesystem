<%@page import="com.nymble.util.AppConstants"%>
<%@page import="com.nymble.beans.LoginBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Welcome To Nymble</title>

        <meta name="description" content="..." />
        <meta name="keywords" content="..." />

        <style type="text/css" media="all">
            @import url("css/style.css");

        </style>

        <script type="text/javascript" src="jquery/jquery.js" ></script>
        <script type="text/javascript" src="jquery/jquery.validate.js" ></script>
        <script type="text/javascript">

            $().ready(function() {
                $("#login_form").validate({
                    rules: {
                        first_name: "required",
                        last_name: "required",
                        username: {
                            required: true,
                            minlength: 2
                        },
                        password: {
                            required: true,
                            minlength: 5
                        } 
                    },
                    messages: {
                        username: {
                            required: "<br/>Please enter your username" 
                             
                        }, 
                        password: {
                            required: "<br/>Please provide a password"
                           
                        } 
                    }
                   
                });   
                 
            });
        </script>

    </head>
    <%
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Expires", "0");
        response.setDateHeader("Expires", -1);
    %> 

    <%

        LoginBean loginBean = (LoginBean) session.getAttribute(AppConstants.USER_SESSION);
        if (loginBean != null && loginBean.getUserType().equals(AppConstants.TYPE_ADMIN)) {
            response.sendRedirect("AllUsersServlet");
        } else if (loginBean != null && loginBean.getUserType().equals(AppConstants.TYPE_CLIENT)) {
            response.sendRedirect("client_home.jsp");
        }

    %>


    <body>

        <div id="bgc">
            <div class="wrapper" >		<!-- wrapper begins -->

                <div id="header">
                    <h1>Pseudonym Manager</h1> 

                    <ul>
                        <li><a href="#" class="active">Home</a></li><li><a href="registration.jsp">Register</a></li>



                        <li><a href="aboutus.jsp">About us</a></li>
                    </ul>
                </div>		<!-- #header ends -->


                <div id="holder">


                    <div id="slideshow">

                        <a href="registration.jsp"><img src="images/sh6.jpg" alt="" title="Typography poster" /></a>
                    </div>		<!-- #slideshow ends --> 

                    <div id="slideshowbtm"  style="text-align: left"> <a href="#" class="quotelink"> <%
                        String regResp = request.getParameter("insert");
                        if (regResp != null && regResp.equals("true")) {
                            %>
                            Successfully Registered
                            <%                                                }
                            %></a></div> 


                    <br/>
                    <br/>


                    <div id="content" class="homepage" style="height:400px;">




                        <div id="main">
                            <div id="side_lt">
                                <ul id="twitter_update_list"><li>Pseudonym Manager</li></ul>
                            </div>
                            <p>
                                <br/>
                                <br/></p>
                            <p> 
                                <br/></p>

                            <p style="text-align: justify;width: 500px;">Anonymizing networks such as Tor  route traffic
                                through independent nodes in separate administrative
                                domains to hide a client's IP address. Unfortunately,
                                some users have misused such networks
                                under the cover of anonymity, users have repeatedly
                                defaced popular websites such as Wikipedia. Since website
                                administrators cannot blacklist individual malicious
                                users' IP addresses, they blacklist the entire anonymizing
                                network. Such measures eliminate malicious activity
                                through anonymizing networks at the cost of denying
                                anonymous access to behaving users. In other words,
                                a few "bad apples" can spoil the fun for all.  
                                <br/>
                                We present a secure system called Nymble, which provides all the following properties: anonymous authentication, 
                                backward unlinkability, subjective blacklisting, 
                                fast authentication speeds, rate-limited anonymous connections,
                                revocation auditability (where users can verify 
                                whether they have been blacklisted), and also addresses 
                            </p>
                        </div>		<!-- #main ends -->


                        <div id="side"><ul id="twitter_update_list"><li>User Login</li></ul>
                            <div id="twitter" align="right">


                                <p class="twt">   <form id="login_form" method="post" action="LoginServlet">
                                        <p>
                                            <br/>
                                            <br/></p>
                                        <div id = "uname" >
                                            <label>Username:</label>
                                            <input type="text" name="username" class="login_input" id="username" value="" />
                                        </div>
                                        <div id="uname">   <label>Password:</label> 
                                            <input type="password" name="password" class="login_input" id="password" value="" /> </div>
                                        <div id="uname" >
                                            <li style="list-style:none;display: inline;">&nbsp;&nbsp;
                                                <input type="submit" value="Login" class="myButton"/>

                                            </li> </div>
                                        <li style="list-style:none;display: inline;">
                                            <%String resp = request.getParameter("user_type");
                                                if (resp != null && resp.equals("invalid_user")) {
                                            %>
                                            Invalid Username or Password 
                                            <br/>
                                            <a href="registration.jsp" style="font-weight: bold;">   Click Here  To Register </a>   
                                            <br/>
                                            <a href="registration.jsp" style="font-weight: bold;">   Forgot Password </a>   
                                            <%}%>

                                        </li>
                                    </form>
                                </p>
                            </div>
                        </div>		<!-- #side ends -->



                    </div>		<!-- #content ends -->

                </div>		<!-- #holder ends -->


                <div id="footer" >
                    <p class="left"><a href="#"><span>Blur Studio</span></a></p>
                    <p class="right">Copyright &copy; 2014.Psueudonym</p>
                </div>		<!-- #footer ends -->



            </div>		<!-- wrapper ends -->


        </div> 

    </body>
</html>