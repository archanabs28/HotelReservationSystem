<%@page import="java.util.Enumeration"%>
<%@page import="bean.BreakfastBean"%>
<%@page import="bean.ServiceBean"%>
<%@page import="bean.RoomReservationBean"%>
<!DOCTYPE html>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="java.util.List"%>
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
   
 
 //   List<RoomReservationBean> list = (List<RoomReservationBean>)session.getAttribute("list");
     int hotelID = Integer.parseInt(request.getParameter("hotelID"));
     
    
     
     List<ServiceBean> serviceList = service.getServiceDetails(hotelID);
     
 String str = request.getRequestURL() + "?";
    Enumeration<String> paramNames = request.getParameterNames();

    while (paramNames.hasMoreElements()) {
        String paramName = paramNames.nextElement();
        System.out.println("" + paramName);
        String[] paramValues = request.getParameterValues(paramName);
        //   System.out.println(""+pa2ramValues.length);
        for (int i = 0; i < paramValues.length; i++) {
            String paramValue = paramValues[i];
            System.out.println("" + paramValue);
            str = str + paramName + "=" + paramValue;
        }
        str = str + "&";
    }
    System.out.println(str.substring(0, str.length() - 1) + "\t");
    System.out.println(request.getParameter("hotelID"));
    System.out.println(request.getParameter("invoiceNo"));
    
   session.setAttribute("hotelID", request.getParameter("hotelID"));
   session.setAttribute("invoiceNo", request.getParameter("invoiceNo"));
     

%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title> Hulton Hotel Reservation</title>
        <meta name="description" content="Smart Health App">


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <style>	
/*            .modal-dialog{
                position: absolute;
                left: 50%;
                 now you must set a margin left under zero - value is a half width your window 
                margin-left: -650px;
                 this same situation is with height - example 
                height: 450px;
                top: 50%;
                width: 40%;
                margin-top: -100px;
            } */
          

        </style>
    </head>

    <script >
       
    </script>	
    <body>
 <nav class= "navbar navbar-inverse navbar-fixed-top" id="my-navbar">
            <div class="container">
                <div class="navbar-header">

                </div><!--navbar header-->

                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <h4 class="navbar-text">Logged in as &nbsp;<%=session.getAttribute("userName")%></h4> 
                    </ul>
                     <ul class="nav navbar-nav">
	  <li><a href="ServiceDetails.jsp">Reservation</a>
        </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="loginPage.jsp">LOGOUT</a>
                    </ul>

                    </nav> 



                    <div class="jumbotron">
                        &nbsp;
                    </div>


                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="text-center">Service details</h1>
                            </div>
                            <form  action="ServiceServlet" method="post" id="ServiceReservePage" onsubmit=" return myFunction(this)">
                                <!--<form class="form-horizontal" id="patientReg" method="post" action="" onsubmit="">-->
                                <!--  <form  action="PatientRegistration" method="post">  -->
                                <div class="modal-body">
                                    <div class="form-group">
                                        <table style="width:120%">
                                            <tr>
                                             <font size="5" >Service provided by Hotel</font>
                                            </tr>
                                             <tr class="blank_row"><td>&nbsp;</td></tr>
                                            <tr>
                                            <th>Service Type </th>
                                             <th> Price</th>
                                            </tr>
                                             <tr class="blank_row"><td>&nbsp;</td></tr>
                                            
                                             <%
                                            for (int i = 0; i < serviceList.size(); i++) {
                                              
                                                ServiceBean bean = serviceList.get(i);
                                                
                                                
                                                 %>
                                            
                                                 <tr>
                                                     <td  width="20%" align="left"><input type="checkbox" name="Stype" value="<%=serviceList.get(i).getSType()%>,<%=serviceList.get(i).getSprice()%>"> &nbsp;&nbsp; <%=serviceList.get(i).getSType()%></td>
                                               
                                                       
                                                <td width="30%" align="left"><b>$ </b><%=serviceList.get(i).getSprice()%></td> 
                                            </tr>
                                            <tr class="blank_row"><td>&nbsp;</td></tr>
                                             
                                                
                                        <%}%>
                                        </table>
                                     
                                    </div>
   
                                                                

                                   
                                    <div class="form-group">

                                        <input type="submit" class="btn btn-block btn-lg btn-primary" value="Reserve"/>

                                    </div>



                                </div>
                            </form>


                        </div>
                    </div>
                                        
                   
                   

                        <script src="https://code.jquery.com/jquery-3.1.1.js"></script>

                        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                        </body>	
                        </html> 