
<%@page import="bean.Breakfast_ReviewBean"%>
<%@page import="bean.BreakfastBean"%>
<%@page import="bean.ServiceBean"%>
<%@page import="bean.RoomReservationBean"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html>
<%@page import="javax.servlet.http.HttpSession" %>
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
    String str=request.getRequestURL()+"?";
Enumeration<String> paramNames = request.getParameterNames();

while (paramNames.hasMoreElements())
{
    String paramName = paramNames.nextElement();
    System.out.println(""+paramName);
    String[] paramValues = request.getParameterValues(paramName);
  //   System.out.println(""+paramValues.length);
    for (int i = 0; i < paramValues.length; i++) 
    {
        String paramValue = paramValues[i];
         System.out.println(""+paramValue);
        str=str + paramName + "=" + paramValue;
    }
    str=str+"&";
}
System.out.println(str.substring(0,str.length()-1)+"\t");
    Service service=new Service();
    String hotelIDString=request.getParameter("hotelID");
    String InvoiceNo=request.getParameter("invoiceNo");
    List<RoomBean> list=new ArrayList<RoomBean>();
    list=(List<RoomBean>)session.getAttribute("tlist");


%>

<html lang="en">

<head>
	<meta charset="utf-8">
	<title> Top5 Ratings</title>
	<meta name="description" content="Smart Health App">


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<Style>	
.modal-dialog{
   position: absolute;
   left: 50%;
   /* now you must set a margin left under zero - value is a half width your window */
   margin-left: -950px;
   /* this same situation is with height - example */
   height: 600px;
   top: 0%;
   width: 120%;
   margin-top: -0px;
} 
.modal-dialog1{
   position: absolute;
   left: 70%;
   /* now you must set a margin left under zero - value is a half width your window */
   margin-left: -550px;
   alignment-adjust:auto;
   /* this same situation is with height - example */
   height: 1050px;
   top: 40%;
   width: 45%;
   margin-top: -0px;
   
} 
</style>
</head>

	
<body>

  		<nav class= "navbar navbar-inverse navbar-fixed-top" id="my-navbar">
                    <div class="container">
	<div class="navbar-header">
		
	</div><!--navbar header-->
	
	<div class="collapse navbar-collapse" id="navbar-collapse">
	<ul class="nav navbar-nav">
	 
        </ul>
              <ul class="nav navbar-nav">
	  <li><a href="HomePage.jsp">Home</a>
        </ul>
              <ul class="nav navbar-nav ">
         <li><a href="Statistics.jsp">Statistics</a>
             </ul>
             <ul class="nav navbar-nav navbar-right">
                 
                 <li><a href="loginPage.jsp">LOGOUT</a>
             </ul>
  			
  		</nav> 

  		

  		<div class="jumbotron">
			<div class="container text-center" style="max-height: 100px">
				<h2>Customer STATISTICS </h2>
			</div>
                </div>


 <div class="modal-dialog1">
    <div class="modal-content">
        <div class="modal-header">
            <h1 class="text-center">Top five Ratings</h1>
        </div>
         <form class="form-horizontal" name="ServiceReservation" id="ServiceReservation" action="ServiceReservationServlet" method="get" onsubmit="">
             <div class="modal-body modal-lg" >
                 <div class="form-inline">
                                    <table  style="width:50%" cellspacing="0" cellpadding="0">
                                        
                                        <tr>
                                            <th>CUSTOMER NAME</th>
                                              <th >MAX_AMOUNT</th>
                                                                             
                                              
                                        </tr>

                                        <tr><td>&nbsp;</td></tr>
                                        <tr><td>&nbsp;</td></tr>
                                        <%
                                            for (int i = 0; i < list.size(); i++) {
                                                System.out.println("inside the if in top 5  jsp and i value is: "+i);
                                                RoomBean bean = list.get(i);
                                                
                                        %>
                                        
                                          <tr>
                                              <td style="color: #000" width="50%" align="left" ><div><%=bean.getCName()%>&nbsp;</div></td>
                                              <td  font="arial-bold" size="13" style="color: #000" width="50%" align="left"><div>$ <%=bean.getPrice()%>&nbsp;</div></td>
                                             
                                              
                                             <tr class="blank_row">
                                                 <td >&nbsp;</td>
                                       </tr>
                                                 
                                              <% }  %>
                                            
                                          </tr> 
                                      
                                        
                                        
                                    </table>
                                             
                 </div>
             </div>
           </form>

    

  		
    <script src="https://code.jquery.com/jquery-3.1.1.js"> </script>
  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"> </script>
</body>	
</html> 