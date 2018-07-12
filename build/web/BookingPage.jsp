<%@page import="bean.CreditCardBean"%>
<%@page import="bean.RoomReservationBean"%>
<%@page import="bean.ReservationBean"%>
<!DOCTYPE html>
<%@page import="javax.servlet.http.HttpSession" %>

<% 
ReservationBean reserve= (ReservationBean)request.getAttribute("reserveBean");
RoomReservationBean roomReserve= (RoomReservationBean) request.getAttribute("roomResBean");
CreditCardBean cardDetails =(CreditCardBean) request.getAttribute("cardBean");

String location = (String) session.getAttribute("Location");
String street = request.getParameter("messageStreet");

out.println(reserve.getInvoiceNum()+"\t"+roomReserve.getCheckInDate()+"\t"+location+"\t"+street);


%>
<html lang="en">

<head>
	<meta charset="utf-8">
	<title> Confirmation Page</title>
	<meta name="description" content="Smart Health App">


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

</head>
	
<script type="text/javascript">
   
    
   
</script>	


	
<body>

  <nav class= "navbar navbar-inverse navbar-fixed-top" id="my-navbar">
                    <div class="container">
	<div class="navbar-header">
		
	</div><!--navbar header-->
	
	<div class="collapse navbar-collapse" id="navbar-collapse">
	<ul class="nav navbar-nav">
	 <h4 class="navbar-text">Logged in as &nbsp;<%=session.getAttribute("userName") %></h4> 
        </ul>
             <ul class="nav navbar-nav navbar-right">
         <li><a href="loginPage.jsp">LOGOUT</a>
             </ul>
  			
  		</nav> 

  		

  		<div class="jumbotron">
			<div class="container text-center" style="max-height: 100px">
                            <h1>Your Booking is done</h1>
			</div>
		</div>


 <div class="modal-dialog modal-lg" >
    <div class="modal-content">
        <div class="modal-header">
          <h4>Booking ID : <%=reserve.getInvoiceNum()%>  . All details will be sent to <%=session.getAttribute("userName") %></h4>
          <h6>Thank you for booking Hulton Hotel. Use the above booking id in all communication with Hulton Hotel. </h6>
        </div>
         <!--<form  action="SearchLocationServlet" method="post" id="loginPage" onsubmit=" return myFunction(this)">-->
             <p style="color: red">
                                   
                                </p>
         <div   class="modal-body  modal-dialog modal-lg form-inline">
             
             <div class="form-group">
                <h3> You Just Booked Hulton Hotel </h3>
                 <!--<input type="search" id="Location" name="Location" placeholder="location" class="form-control input-lg"/>-->
                 <fieldset class="row1">
                                    <legend style="color: #066A75">
                                    </legend>
                                    <table  style="width:80%" cellspacing="0" cellpadding="0">
                                        
                                        <tr></tr>
                                       
                                        
                                          <tr>
                                              
                                              <td><div class="img"><img src="img/Image1.jpeg" width="140" height="100"> </img></div>
                                                 
                                              </td>
                                              
                                              <!--<td> Address : </td>-->
                                              <td>
                                          <table width="200%">
                                              <tr width="100%">
                                                  <td>Address : <%=street%> , <%=location%></td>
                                               
                                              </tr>
                                              <tr > 
                                                  <td >Check-in</td>
                                                 
                                                  <td >Check-out</td></tr>
                                              <tr>
                                                  <td><%=roomReserve.getCheckInDate()%></td>
                                                  <td><%=roomReserve.getCheckOutDate()%></td>
                                                  
                                              </tr>
                                              
                                          </table>
                                              </td>
                                              <!--<td><div class="img"><img src="img/db1.jpg" width="140" height="100"> </img></div>-->
                                              
                                                  <!--</td>-->    
                                              
                                               <!--<td><div class="img"><img src="img/del1.jpg" width="140" height="100"> </img></div>-->
                                              
                                               <!--</td>--> 
                                              
                                               <!--<td><div class="img"><img src="img/suite1.jpg" width="140" height="100"> </img></div>-->
                                              
                                               <!--</td>--> 
                                         </tr> 
                                      
                                       
                                        
                                    </table> 
                                </fieldset>
             </div>

             
         </div>
       </form>

    </div>
 </div>

  		
    <script src="https://code.jquery.com/jquery-3.1.1.js"> </script>
  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"> </script>
 
</body>	
</html> 