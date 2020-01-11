<%@page import="com.nymble.util.AppConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Nymble &#9679; About us</title>

        <meta name="description" content="..." />
        <meta name="keywords" content="..." />

        <style type="text/css" media="all">
            @import url("css/style.css");

        </style>

        <!--[if lt IE 8]><style type="text/css" media="all">@import url("css/ie.css");</style><![endif]-->

        <!-- Shared on MafiaShare.net  --><!-- Shared on MafiaShare.net  --></head>




    <body>

        <div id="bgc">

            <div class="wrapper">		<!-- wrapper begins -->






                <div id="header">
                    <h1>Pseudonym Manager</h1> 

                    <ul>
                        <li><a href="index.jsp">Home</a></li>

                        <%
                            Object obj = session.getAttribute(AppConstants.USER_SESSION);
                            if (obj == null) {

                        %> 
                        <li><a href="registration.jsp">Register</a></li>



                        <%                              }
                        %>
                        <li><a href="aboutus.jsp" class="active">About us</a></li>
                        <%
                            Object obj1 = session.getAttribute(AppConstants.USER_SESSION);
                            if (obj1 != null) {

                        %> 
                        <li><a href="LogoutServlet?page=index.jsp">Logout</a></li>
                        <%                             }
                        %>
                    </ul>
                </div>		<!-- #header ends -->  

                <div id="holder"> 

                    <div class="pagetitle">
                        <h2>About us</h2>
                       
                    </div> 

                    <div id="content"> 

                        <div id="main">
                            <p>We present a secure system called Nymble, which provides
                                all the following properties: anonymous authentication,
                                backward unlinkability, subjective blacklisting,
                                fast authentication speeds, rate-limited anonymous connections,
                                revocation auditability (where users can verify
                                whether they have been blacklisted), and also addresses
                            </p>

                            <h3>Overview</h3>

                            <blockquote>We now present a high-level overview of our Nymble<br />
                                system, and defer the entire protocol description and<br />
                            </blockquote>



                            <h5>Resource-based blocking</h5>

                            <p>To limit the number of identities a user can obtain (called
                                the Sybil attack ), the Nymble system binds nymbles
                                to resources that are sufficiently difficult to obtain in
                                great numbers.</p>
                            <h5>The Pseudonym Manager</h5>

                            <p>Each user is given a pseudonym. Pseudonyms are deterministically
                                chosen based on the controlled resource,
                                ensuring that the same pseudonym is always issued for
                                the same resource.</p>
                            <h5>The Nymble Manager</h5>

                            <p>The Nymble manager provides each users with a set of nymble tickets (nymbles) by which a user can access websites through the anonymizing network.</p>
                            <h5>Time</h5>

                            <p>A user's
                                access within a time period is tied to a single nymble
                                ticket, by the use of different nymble tickets across time
                                periods grants the user anonymity between time periods.
                                Smaller time periods provide users with higher rates
                                of anonymous authentication, while longer time periods
                                allow servers to rate-limit the number of misbehaviors
                                from a particular user before he or she is blocked.</p>
                            <h5>Notifying the user of blacklist status</h5>

                            <p>Users who make use of anonymizing networks expect
                                their connections to be anonymous. If a server obtains
                                a seed for that user, however, it can link that user's
                                subsequent connections. It is of utmost importance, then,
                                that users be notified of their blacklist status before they
                                present a nymble ticket to a server. In our system, the
                                user can download the server's blacklist and verify her
                                status. If blacklisted, the user disconnects immediately.</p>
                        </div>		<!-- #main ends --> 

                        <div id="side"> 
                            <p><img src="images/1291044521102.png" alt="" /></p> 

                            <div id="twitter">
                                <ul id="twitter_update_list"><li>Loading tweets...</li></ul>
                               
                            </div> 
                        </div>		<!-- #side ends --> 
                    </div>		<!-- #content ends --> 
                </div>		<!-- #holder ends -->  
                <!-- #logos ends --> 

                <div id="footer">
                    <p class="left"><a href="#"><span></span></a></p>
                    <p class="right">Copyright &copy; 2013. Some rights reserved.</p>
                </div>		<!-- #footer ends -->



            </div>		<!-- wrapper ends --> 
        </div> 

    </body>
</html>