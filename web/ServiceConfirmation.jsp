<%@page import="java.util.List"%>
<%@page import="bean.CreditCardBean"%>
<%@page import="bean.RoomReservationBean"%>
<%@page import="bean.ReservationBean"%>
<!DOCTYPE html>
<%@page import="javax.servlet.http.HttpSession" %>

<% 

String invoiceNo = request.getParameter("invoiceNo");

List<String> servicetype =(List<String>) session.getAttribute("sType");
List<String> servicePrice = (List<String>)session.getAttribute("sPrice");
int sum=0;
for(int i=0;i<servicePrice.size();i++){
    sum=sum+Integer.parseInt(servicePrice.get(i));
}

%>
<html lang="en">

<head>
	<meta charset="utf-8">
	<title> Service Confirmation Page</title>
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
	  <li><a href="ServiceDetails.jsp">Reservation</a>
        </ul>
        
             <ul class="nav navbar-nav navbar-right">
         <li><a href="loginPage.jsp">LOGOUT</a>
             </ul>
  			
  		</nav> 

  		

  		<div class="jumbotron">
<!--			<div class="container text-center" style="max-height: 100px">
                            <h1>Your Booking is done</h1>
			</div>-->
		</div>


 <div class="modal-dialog modal-lg" >
    <div class="modal-content">
        <div class="modal-header">
          <h4>Booking ID : <%=invoiceNo%>  
        </div>
         <!--<form  action="SearchLocationServlet" method="post" id="loginPage" onsubmit=" return myFunction(this)">-->
             <p style="color: red">
                                   
                                </p>
         <div   class="modal-body  modal-dialog modal-lg form-inline">
             
             <div class="form-group">
                <h3> Service Selected  </h3>
                 <!--<input type="search" id="Location" name="Location" placeholder="location" class="form-control input-lg"/>-->
                 <fieldset class="row1">
                                    <legend style="color: #066A75">
                                    </legend>
                                    <table  style="width:50%" cellspacing="0" cellpadding="0">
                                        
                                        <tr>
                                            <th>Service </th>
                                            <th> Price</th>
                                        </tr>
                                         <tr><td>&nbsp;</td></tr>
                                        <% for(int i=0;i<servicetype.size();i++){%>
                                        <tr>
                                           
                                            <td style="color: #000" width="10%" align="left"><%=servicetype.get(i)%></td> 
                                            <td style="color: #000" width="10%" align="left"><b>$&nbsp;</b><%=servicePrice.get(i)%></td>
                                            
                                          
                                        </tr>
                                         <tr>
                                                <tr class="blank_row">
                                                    <td bgcolor="#FFFFFF" colspan="4">&nbsp;</td>
                                       </tr>
                                        
                                          <%}%>
                                         
                                              <td tyle="color: #000" width="10%" align="left">Total</td>
                                              <td style="color: #000" width="10%" align="left"><b>$ </b><%=sum%></td>
                                                 
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