/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import Service.Service;
import bean.RoomReservationBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Archana
 */
public class ServiceServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] serviceTypes= request.getParameterValues("Stype");
        System.out.println(""+serviceTypes.length);
        List<String> sType=new ArrayList<String>();
        List<String> sPrice= new ArrayList<String>();
        for(String s: serviceTypes){
            String ss[]=s.split(",");
            for(int i=0;i<ss.length;i=i+2){
                sType.add(ss[i]);
                sPrice.add(ss[i+1]);
            }
          
       }
        for(String m:sType){
            System.out.println(m);
        }
        
        for(String n:sPrice){
            System.out.println(n);
        }
        HttpSession session = request.getSession();
         String invoiceNo= (String)session.getAttribute("invoiceNo");
         String hotelID = (String) session.getAttribute("hotelID");
         Service service= new Service();
         service.addReservationService(sType,invoiceNo,hotelID);
        
          session.setAttribute("sType", sType);
          session.setAttribute("sPrice", sPrice);
            getServletConfig().getServletContext().getRequestDispatcher("/ServiceConfirmation.jsp?invoiceNo="+invoiceNo).forward(request,response);
         // response.sendRedirect("ServiceConfirmation.jsp?invoiceNo="+invoiceNo);
          
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
   
}
