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
String street = (String) session.getAttribute("messageStreet");

out.println(reserve.getInvoiceNum()+"\t"+roomReserve.getCheckInDate()+"\t"+location+"\t"+street);
String totalAmount = (String)session.getAttribute("totalAmount");

System.out.println("Total Cost for room\t"+totalAmount);
String noofDays = request.getParameter("NoofDays");

String card = (String)session.getAttribute("cardnumber");
String mask= "xxxxxxxxxxxx####";

int index = 0;
      StringBuilder masked = new StringBuilder();
      for (int i = 0; i < mask.length(); i++) {
         char c = mask.charAt(i);
         if (c == '#') {
            masked.append(card.charAt(index));
            index++;
         } else if (c == 'x') {
            masked.append(c);
            index++;
         } else {
            masked.append(c);
         }
      }

String cardExp = (String)session.getAttribute("cardExp");

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
            
         <ul class="nav navbar-nav">
	  <li><a href="SearchLocation.jsp">Search Hotel</a>
        </ul>
             <ul class="nav navbar-nav navbar-right">
         <li><a href="loginPage.jsp">LOGOUT</a>
             </ul>
  			
  		</nav> 

  		

  		<div class="jumbotron">
			<!--<div class="container text-center" style="max-height: 100px">-->
                            <!--<h2>Your Booking is done</h2>-->
                            <!--<div class="container text-center"><font size="7">Your Booking is done </font></div>-->
			<!--</div>-->
                         
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
                                    <table  style="width:120%" cellspacing="0" cellpadding="0">
                                        
                                        <tr></tr>
                                       
                                        
                                          <tr>
                                              
                                              <td><div class="img"><img src="img/Image1.jpeg" width="190" height="100"> </img></div>
                                                 
                                              </td>
                                              
                                              <!--<td> Address : </td>-->
                                              <td>
                                          <table width="90%">
                                              <tr width="50%">
                                                  <td style="color: #000" width="30%" align="left" colspan="3"><b>Address :</b> <%=street%> , <%=location%></td>
                                               
                                              </tr>
                                              <tr>
                                                  <td>&nbsp;</td>
                                              </tr>
                                              <tr > 
                                                  <th >Check-in</th>
                                                  <th> Number of Days</th>
                                                  <th >Check-out</td></th>
                                              </tr>
                                              <tr>
                                                  <td>&nbsp;</td>
                                              </tr>
                                              <tr>
                                                  <td style="color: #000" width="2%" align="left"><%=roomReserve.getCheckInDate()%></td>
                                                  <td style="color: #000" width="2%" align="left">&nbsp;&nbsp;&emsp;&emsp;&emsp;<%=noofDays%></td>
                                                  <td style="color: #000" width="2%" align="left"><%=roomReserve.getCheckOutDate()%></td>
                                                  
                                              </tr>
                                              <tr>
                                                  <td>&nbsp;</td>
                                              </tr>
                                              <tr width="80%">
                                                   <td style="color: #000" width="30%" align="left" colspan="3"><b>Card Number :</b> <%=masked.toString()%></td>
                                              </tr>
<!--                                              <tr>
                                                  <td>&nbsp;</td>
                                              </tr>-->
                                              <tr width="80%">
                                                   <td style="color: #000" width="30%" align="left" colspan="3"><b> Card Expiration :</b> <%=cardExp%></td>
                                              </tr>
                                              <tr>
                                                  <td>&nbsp;</td>
                                              </tr>
                                              <tr width="80%">
                                                   <td style="color: #000" width="30%" align="left" colspan="3"><b> Total Amount :$ </b> <%=totalAmount%></td>
                                              </tr>
                                              
                                          </table>
                                              </td>
                                              
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