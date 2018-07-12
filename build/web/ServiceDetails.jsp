<%@page import="bean.RoomReservationBean"%>
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
   
    Service service = new Service();
   // String location=request.getParameter("location");
   
    String userName = (String)session.getAttribute("userName");
    out.println(userName);
    
    List<RoomReservationBean> list = service.invoiceDetailsofUser(userName);
     session.setAttribute("list", list);
     
     
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta charset="UTF-8">
            <title> Hulton Hotel Reservation</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                                float:"right";
                            }
                            tr {
                                
                                text-align: center;
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
         <ul class="nav navbar-nav">
	  <li><a href="SearchLocation.jsp">Main Menu</a>
        </ul>
         <ul class="nav navbar-nav">
	 <li><a href="BreakfastReviewPage.jsp">Breakfast Review</a>
        </ul>
        <ul class="nav navbar-nav">
	 <li><a href="ServiceReviewPage.jsp">Service Review</a>
        </ul>
         <ul class="nav navbar-nav">
	 <li><a href="RoomReview.jsp">Room Review</a>
        </ul>
             <ul class="nav navbar-nav navbar-right">
         <li><a href="loginPage.jsp">LOGOUT</a>
             </ul>
  			
  		</nav> 
                                    </div>

                                </div> <!-- container-->

                            </nav> <!-- black nav bar-->
                            <!--jumbotron-->
                           
                            <!--<span style="text-align: center"><h2>Appointment Details</h2></span>--> 
                            <form  name="ListofService" id="" action="ListofServiceServlet" method="get" onsubmit="">
                                <fieldset class="row1">
                                    <legend style="color: #066A75">
                                    </legend>
                                    <table  style="width:60%" cellspacing="0" cellpadding="0" align="center">
                                        <tr><td>&nbsp;</td></tr>
                                        
                                        <tr><td>&nbsp;</td></tr>
                                        <tr>
                                            <th>Invoice Number </th>
                                            <th> Check-In Date</th>
                                            <th>CheckOut Date</th>
                                            
                                        </tr>
                                        <tr><td>&nbsp;</td></tr>
                                        <tr><td>&nbsp;</td></tr>
                                        
                                        <%
                                            for (int i = 0; i < list.size(); i++) {
                                               
                                                RoomReservationBean bean = list.get(i);
                                                
                                                
                                        %>
                                        
                                          <tr>
                                              
                                              <td style="color: #000" width="20%" align="left"><%=list.get(i).getInvoiceNo()%> </td>
                                                                        
                                            
                                                 
                                            <td style="color: #000" width="20%" align="left"><div><%=list.get(i).getCheckInDate()%></div> </td>
                                           <td style="color: #000" width="20%" align="left"><div><%=list.get(i).getCheckOutDate()%></div> </td>
                                         
                                           
                                             
                                            <!--<td style="color: #000" width="10%" align="left"><a href="BreakfastReservation.jsp?hotelID=<%=list.get(i).getHotelID()%>">Reserve Breakfast</a></td>-->  
                                           <td style="color: #000" width="20%" align="left">
                                               <a href="BreakfastReservation.jsp?hotelID=<%=list.get(i).getHotelID()%>&invoiceNo=<%=list.get(i).getInvoiceNo()%>&checkInDate=<%=list.get(i).getCheckInDate()%>&checkOutDate=<%=list.get(i).getCheckOutDate()%>">Reserve Breakfast</a>&emsp;
                                           <a href="ServiceReservation.jsp?hotelID=<%=list.get(i).getHotelID()%>&invoiceNo=<%=list.get(i).getInvoiceNo()%>">Reserve Service</a>
                                           </td>
                                            
                                            
                                          </tr> 
                                        <tr class="blank_row">
                                             <td bgcolor="#FFFFFF" colspan="4"><hr size="40"></hr></td>
                                       </tr>
                                       <%}%>
                                      
                                    </table> 
                                </fieldset>
                            </form>
                            <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
                            <!-- Latest compiled and minified JavaScript -->
                            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
                            </script>
                        </body>
                        </html>