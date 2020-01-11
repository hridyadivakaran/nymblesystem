<%@page import="com.nymble.util.AppConstants"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">


    <head profile="http://gmpg.org/xfn/11">
        <title>Nymble | Registration</title>

        <script type="text/javascript" src="jquery/jquery.js" ></script>
        <script type="text/javascript" src="jquery/jquery.validate.js" ></script>
        <script type="text/javascript">

            $().ready(function() {
                
                $.validator.addMethod(
                "regex",
                function (value, element, regexp) {
                    var re = new RegExp(regexp);
                    return this.optional(element) || re.test(value);
                },
                "Invald character "
            );
                
                
                $("#registerForm").validate({
                    rules: {
                        first_name:{
                            required: true,
                            regex:"^[a-zA-Z_]*$"
                             
                        },
                        last_name:{
                            required: true,
                            regex:"^[a-zA-Z_]*$"
                        },
                        username: {
                            required: true,
                            regex:"^[a-zA-Z_]*$",
                            minlength: 2
                        },
                        password: {
                            required: true,
                            minlength: 5
                        },
                        confirm: {
                            required: true,
                            minlength: 5,
                            equalTo: "#password"
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        phone: {
                            required: true,
                            number: true,
                            minlength: 10,
                            maxlength: 10
                        },
                        district: "required",
                        state: "required",
                        nationality: "required",
                        address: "required"
                    },
                    messages: {
                        username: {
                            required: " Please enter your username", 
                            remote:  " username not available",
                            minlength: " Your username must consist of at least 2 characters"
                        },
                        first_name: " Please enter your firstname",
                        last_name: " Please enter your lastname",
                        username: {
                            required: " Please enter a username",
                            minlength: " Your username must consist of at least 2 characters"
                        },
                        password: {
                            required: " Please provide a password",
                            minlength: " Your password must be at least 5 characters long"
                        },
                        confirm: {
                            required: " Please provide a password",
                            minlength: " Your password must be at least 5 characters long",
                            equalTo: " Please enter the same password as above"
                        },
                        email: " Please enter a valid email address",
                        agree: " Please accept our policy"
                    }
                   
                });   
                 
            });
        </script>

        <link rel="stylesheet" href="styles/layout.css" type="text/css" /> 
        <link rel="stylesheet" href="css/style.css" type="text/css" /> 


    </head>


    <body>

        <div id="bgc">

            <div class="wrapper"> 


                <div id="header">
                    <h1>Pseudonym Manager</h1> 

                    <ul>
                        <li><a href="index.jsp">Home</a></li>

                        <li><a href="#" class="active">Register</a></li>


                        <li><a href="aboutus.jsp">About us</a></li>
                    </ul>
                </div>	 


                <div id="holder" style="margin-left: 150px;margin-right: 150px;">  
                    <div class="pagetitle" style="text-align: right; font-size: 25px;">
                        User Registration 
                    </div>  

                    <div id="content"> 
                        <form id="registerForm" name="registerForm"  action="RegisterServlet" method="post">
                            <div id="portfolio">
                                <p><label for="name"> Username* : </label><br/>
                                    <input id="username"   name="username" type="text" class="text"  size="22" placeholder="Username" />

                                </p>
                                <p> <label for="name"> Password* : </label><br/>
                                    <input type="password" class="text" name="password" id="password"  size="22"  
                                           placeholder="Password"/>

                                </p>
                                <p><label for="name">Confirm* : </label><br/>
                                    <input type="password" class="text"  name="confirm" id="confirm"   size="22"
                                           placeholder="Confirm"/>

                                </p>
                                <p> <label for="name">Last Name* : </label><br/>
                                    <input type="text" class="text" name="last_name" id="last_name"  size="22"
                                           placeholder="Last Name"/>

                                </p>
                                <p> <label for="name">First Name* : </label><br/>
                                    <input type="text" class="text" name="first_name" id="first_name"  size="22" 
                                           placeholder="First Name"/>

                                </p>
                                <p> <label for="email">Email* : </label><br/>
                                    <input type="text" class="text"  name="email" id="email"  size="22"  
                                           placeholder="Email id"/>

                                </p>
                                <p><label for="email">Phone* : </label><br/>
                                    <input type="text" class="text" name="phone" id="phone"  size="22" 
                                           placeholder="Phone"/>

                                </p>
                                <p><label for="email">District* : </label><br/>
                                    <input type="text" class="text"  name="district" id="district"   size="22"
                                           placeholder="District"/>

                                </p>
                                <p><label for="email"> State* : </label><br/>
                                    <input type="text" class="text"  name="state" id="state"  size="22"  placeholder="State"/>

                                </p>
                                <p> <label for="email"> Nationality* : </label><br/>
                                    <input type="text" class="text"  name="nationality" id="nationality"  size="22"  
                                           placeholder="Nationality"/>

                                </p>

                                <p>
                                    <textarea name="address"   cols="50%" rows="10" placeholder="Type in your address"></textarea>
                                    <label for="comment" style="display:none;"><small>Address (required)</small></label>
                                </p>
                                <p>

                                    &nbsp;
                                    &nbsp;
                                    &nbsp;
                                    &nbsp;
                                    &nbsp;
                                    &nbsp;
                                    <input name="submit" type="submit" id="submit" value="Register"  class="myButton"/>

                                    <input name="reset" type="reset" id="reset" tabindex="5" value="Clear"  class="myButton"/>
                                    <%
                                        String regResp = request.getParameter("insert");
                                        if (regResp != null && regResp.equals("false")) {
                                    %>
                                    Failed to register
                                    <%                                                }
                                    %>
                                </p>


                            </div>	
                        </form> 


                    </div>	
                </div>	
                <div id="logos">

                </div>		<!-- #logos ends -->










                <div id="footer">
                    <p class="left"><a href="#"><span>Blur Studio</span></a></p>
                    <p class="right">Copyright 2013. Some rights reserved.</p>
                </div>		<!-- #footer ends -->



            </div>		<!-- wrapper ends -->


        </div>




    </body>
</html>