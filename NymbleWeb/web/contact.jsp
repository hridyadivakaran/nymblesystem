<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Nymble &#9679; Contact us</title>

        <meta name="description" content="..." />
        <meta name="keywords" content="..." />

        <style type="text/css" media="all">
            @import url("css/style.css");
         
        </style>

        <!--[if lt IE 8]><style type="text/css" media="all">@import url("css/ie.css");</style><![endif]-->
        <script type="text/javascript" src="jquery/jquery.js" ></script>
        <script type="text/javascript" src="jquery/jquery.validate.js" ></script>

        <script type="text/javascript">

            $().ready(function() {
                $("#contactForm").validate({
                    rules: {
                        name: "required",
                        telephone:{
                            required: true,
                            number: true,
                            minlength: 11,
                            maxlength: 11
                        },
                        
                        email: {
                            required: true,
                            email: true
                        },
                                    
                        message: "required"
                    },
                    messages: {
                        
                        name: "Please enter your name",
                        telephone: "Please enter a valid telephone number",
                        email: "Please enter a valid email address",
                        agree: "Please accept our policy"
                    }
                   
                });   
                 
            });
        </script>
        <!-- Shared on MafiaShare.net  --><!-- Shared on MafiaShare.net  --></head>




    <body>

        <div id="bgc">

            <div class="wrapper">		<!-- wrapper begins -->






                <div id="header">
                    <h1><a href="#"><span>Blur Studio</span></a></h1>

                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="registration.jsp">Register</a></li>

                        <li><a href="contact.jsp" class="active">Contact</a></li>
                        <li><a href="aboutus.jsp">About us</a></li>
                    </ul>
                </div>		<!-- #header ends -->










                <div id="holder">





                    <div class="pagetitle">
                        <h2>Contact us</h2>
                    </div>









                    <div id="content">




                        <div id="wide" class="contact">

                            <p>Haing trouble using Nymble? Post your queries here and we will get in touch with you</p>


                            <div class="clear sep"></div>


                            <form action="ContactServlet" method="post" id="contactForm" name="contactForm">

                                <fieldset class="left">
                                    <p><label>Your name:</label> <br />
                                        <input type="text" class="text"  name ="name" id ="name"/></p>

                                    <p><label>Your Telephone:</label> <br />
                                        <input type="text" class="text" name ="telephone" id ="telephone"/></p>

                                    <p><label>Your E-mail:</label> <br />
                                        <input type="text" class="text" name ="email" id ="email" /></p>
                                </fieldset>

                                <fieldset class="right">
                                    <p><label>Your message:</label> <br />
                                        <textarea name="message"  id ="message"></textarea></p>

                                    <p><input type="submit" class="submit" value="Send" /> <span>All the fields are mandatory.</span></p>
                                </fieldset>

                            </form>

                        </div>		<!-- #wide ends -->








                    </div>		<!-- #content ends -->




                </div>		<!-- #holder ends -->








                <div id="logos">
                    <ul>
                        <li><a href="http://java.com/"><img src="images/php.png" alt="JAVA" /></a></li>
                        <li><a href="http://mysql.com/"><img src="images/mysql.png" alt="MySQL" /></a></li>
                        <li><a href="http://jquery.com/"><img src="images/jquery.png" alt="jQuery" /></a></li>

                    </ul>
                </div>		<!-- #logos ends -->










                <div id="footer">
                    <p class="left"><a href="#"><span>Blur Studio</span></a></p>
                    <p class="right">Copyright &copy; 2013. Some rights reserved.</p>
                </div>		<!-- #footer ends -->



            </div>		<!-- wrapper ends -->


        </div>






    </body>
</html>