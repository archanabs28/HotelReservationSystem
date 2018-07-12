<%@page import="bean.Breakfast_ReviewBean"%>
<%@page import="bean.Service_ReviewBean"%>
<%@page import="bean.RoomReservationBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="bean.RoomBean"%>
<%@page import="Service.Service" %>
<%@page import="javax.servlet.ServletException" %>
<%@page import="javax.servlet.http.HttpServlet" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="javax.servlet.http.HttpServletResponse" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="java.util.ArrayList" %>



<%
  
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta charset="UTF-8">
            <title>Review Submission Page</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                
                <META http-equiv="refresh" content="10;URL=http://localhost:8080/HotelReservation/SearchLocation.jsp"></META>
                <!-- Latest compiled and minified CSS -->
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

                    <!-- Optional theme -->
                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

                        </head>
                        <style>
                            table {
                                margin-left: auto;
                                margin-right: auto;
                                border:none;
                            }
                            tr {
                                
                                text-align: center;
                            }
                            .modal-dialog1{
   position: absolute;
   left: 70%;
   /* now you must set a margin left under zero - value is a half width your window */
   margin-left: -550px;
   alignment-adjust:auto;
   /* this same situation is with height - example */
   height: 1050px;
   top: 30%;
   width: 45%;
   margin-top: -0px;
   
} 
                        </style>
                        <body>
                            <nav class="navbar navbar-inverse navbar-fixed-top" id="my-navbar">
                                       <div class="container">
	<div class="navbar-header">
		
	</div><!--navbar header-->
	
	<div class="collapse navbar-collapse" id="navbar-collapse">
	<ul class="nav navbar-nav">
	 <h4 class="navbar-text">Logged in as &nbsp;<%=session.getAttribute("userName") %></h4> 
        </ul>
             <ul class="nav navbar-nav navbar-right">
               <li><a href="ServiceReviewPage.jsp">Service Review</a>
               <li><a href="BreakfastReviewPage.jsp">BreakFast Review</a>
               <li><a href="RoomReview.jsp">Room Review</a>
               <li><a href="loginPage.jsp">LOGOUT</a>
             </ul>
  			
  		</nav> 
                                    </div>

                                </div> <!-- container-->

                            </nav> <!-- black nav bar-->
                            <!--jumbotron-->
                            <div class="jumbotron" style="padding-bottom:0px; margin-bottom:0px">
<!--                                <div class="container text-center">
                                    <h1>Breakfast  Review</h1>
                                    <p>Using Data Mining</p>
                                </div>-->
                            </div><!-- End jumbotron-->

    <div class="modal-dialog1">
    <div class="modal-content">
<!--        <div class="modal-header">
            <h1 class="text-center">Breakfast Information</h1>
        </div>-->
         <form class="form-horizontal" name="BreakfastReservation" id="ServiceReservation" action="BreakfastReviewConfirmationServlet" method="get" onsubmit="">
             <div class="modal-body" align="left" >
                 <div class="form-inline">
                               
                    
                     <h4><font size="6">Thank you for your feedback</font></h4>
                     <br/>
                     <br/>
                       <h6><font size="3"> After 10 seconds page will automatically redirect to Home page</font></h6>
                     <br/>
                    <br/>
                     
                                        
                                  
                            </form>
</div>
             </div>         
                            <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
                            <!-- Latest compiled and minified JavaScript -->
                            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
                            </script>
                        </body>
                        </html>