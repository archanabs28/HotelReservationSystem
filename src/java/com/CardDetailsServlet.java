/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import Service.Service;
import bean.CreditCardBean;
import bean.HotelBean;
import bean.ReservationBean;
import bean.RoomReservationBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Archana
 */
public class CardDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        CreditCardBean cardBean = new CreditCardBean();
        ReservationBean reserveBean = new ReservationBean();
        System.out.println("CardDetailsServlet ---->");
        HttpSession session = request.getSession();
        String emailID = (String) session.getAttribute("userName");
        String cardNumber = request.getParameter("cardnumber");
        String cardType = request.getParameter("cardType");
        String NameonCard = request.getParameter("nameOnCard");
        String billingAddress = request.getParameter("billingAddress");
        String securityCode = request.getParameter("securityCode");
        String ExpMonth = request.getParameter("Month");
        String ExpYear = request.getParameter("Year");
        System.out.println("Card Details In Servlet\t" + cardNumber + "\t" + cardType + "\t" + NameonCard + "\t" + emailID);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String currentdate = (dtf.format(localDate)).toString();

        String price = (String) session.getAttribute("price");
        String hotelID = (String) session.getAttribute("hotelID");
        String roomNo = (String) session.getAttribute("roomNo");
        String desc = (String) session.getAttribute("desc");
        String checkInDate = (String) session.getAttribute("date");
        String checkOutDate = (String) session.getAttribute("date1");

        System.out.println(hotelID + "\t" + price + "\t" + roomNo + "\t" + desc + "\t" + checkInDate + "\t" + checkOutDate);

        cardBean.setCNumber(cardNumber);
        cardBean.setCType(cardType);
        cardBean.setName(NameonCard);
        cardBean.setBAddress(billingAddress);
        cardBean.setExpDate(ExpMonth + "/" + ExpYear);
        int code = Integer.parseInt(securityCode);
        cardBean.setSecurityCode(code);
        Service service = new Service();
        int isInserted = 0;
        boolean isCardNotPresent = service.checkCNumber(cardNumber);

        if (isCardNotPresent) {
            isInserted = service.addCardDetails(cardBean);
        }

        reserveBean.setCNumber(cardBean.getCNumber());
        reserveBean.setRDate(currentdate);
        service.addReservationDetails(reserveBean, emailID);

        RoomReservationBean roomResBean = new RoomReservationBean();
        roomResBean.setCheckInDate(checkInDate);
        roomResBean.setCheckOutDate(checkOutDate);
        int hID = Integer.parseInt(hotelID);
        roomResBean.setHotelID(hID);
        roomResBean.setRoomNo(roomNo);

        service.addRoomReservationDetails(roomResBean, reserveBean);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = (String) session.getAttribute("date");
        Date date1 = formatter.parse(date);

        String date2 = (String) session.getAttribute("date1");
        Date date3 = formatter.parse(date2);

        long days = (date1.getTime() - date3.getTime()) / 86400000;
        long diff = Math.abs(days);

        request.setAttribute("reserveBean", reserveBean);
        request.setAttribute("roomResBean", roomResBean);
        request.setAttribute("cardBean", cardBean);
        
        session.setAttribute("cardnumber", cardNumber);
        session.setAttribute("cardExp", (ExpMonth+"/"+ExpYear));

        getServletConfig().getServletContext().getRequestDispatcher("/ConfirmationPage.jsp?NoofDays=" + diff).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(CardDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(CardDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
