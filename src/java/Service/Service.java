
package Service;


import bean.BreakfastBean;
import bean.Breakfast_ReviewBean;
import bean.CreditCardBean;
import bean.CustomerBean;
import bean.HotelBean;
import bean.LoginBean;
import bean.RResv_BreakfastBean;
import bean.RResv_ServiceBean;
import bean.ReservationBean;
import bean.RoomBean;
import bean.RoomReservationBean;
import bean.Room_ReviewBean;
import bean.ServiceBean;
import bean.Service_ReviewBean;
import connectDB.connectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Service {
     public boolean login(String userName, String password)
    {
       
        boolean flag=false;
        try
        {
            Connection con=connectDB.connectToDB();
            Statement stmt=con.createStatement();
            String query="select * from HotelLogin";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                String name=rs.getString("UserName");
                String pass=rs.getString("password");
                
                if(userName.equals(name) && password.equals(pass))
                {
                    
                    flag=true;
                    break;
                }
            }
        }
        catch(Exception e)
        {
                System.out.println("Exception in login - " + e);
        }
        return flag;
    }
    
    public int insertCustomer(CustomerBean customer){
         int j=0;
         int i=0;
         
        try{
           System.out.println("inside the service method in insertCustomer!!!");
            Connection con=connectDB.connectToDB();
            Statement st=con.createStatement();
            String query="insert into Customer(CName,Address,Phone_No,Email) Values('"+customer.getName()+"','"+customer.getAddress()+"','"+customer.getPhonenumber()+"','"+customer.getEmail()+"')";
            System.out.println("Query value is:"+ query);
            j=st.executeUpdate(query);
            System.out.println("value of j after inserting the values into customer table is: "+j);
            
        } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
        if(j>0){
            i++;
           }else{
            System.out.println("Not inserted into the Customer Table!!!");
        }
       
        return i; 
    }
    public void insertIntoLogin(LoginBean login){
        int j=0;
        try{
            Connection con=connectDB.connectToDB();
            Statement st=con.createStatement();
            String Query="INSERT INTO HotelLogin(UserName,password) VALUES('"+login.getUsername()+"','"+login.getPassword()+"')";
            j=st.executeUpdate(Query);
            
        } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public boolean forgotPassword(String username,String password){
        int i=0;
        boolean value=false;
        try{
            Connection con=connectDB.connectToDB();
            Statement st=con.createStatement();
            String Query="Select * from HotelLogin WHERE UserName='"+username+"'";
            System.out.println("Inside forgot password method, and query is: "+Query);
            ResultSet rs=st.executeQuery(Query);
            while(rs.next()){
                String Query1="Update HotelLogin SET password='"+password+"' where UserName='"+username+"'";
               i= st.executeUpdate(Query1);
               if(i>0){
                   value= true;
               }
            }
        } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
        return value;
    }
    public List<HotelBean> findLocation(String location){
        boolean isPresent = false;
        String street, city, zipcode;
        HotelBean ht = null;
        List<HotelBean> list = new ArrayList<HotelBean>();
                
         try {
             Connection conn = connectDB.connectToDB();
             Statement stmt = conn.createStatement();
             String searchQuery = "select * from Hotel where state='"+location+"'";
             System.out.println("Search Location "+searchQuery);
             ResultSet rs = stmt.executeQuery(searchQuery);
             if( rs != null){
                 while(rs.next()){
                     ht=new HotelBean();
                     street = rs.getString("Street");
                     city = rs.getString("State");
                     zipcode = rs.getString("zip");
                    ht.setStreet(street);
                    ht.setState(city);
                    ht.setZip(zipcode);
                    list.add(ht);
                     System.out.println(street+"\t"+city+"\t"+zipcode);
                 }
                 isPresent = true;
             }else{
                 System.out.println("Hotel is not found in this location");
             }
           //  conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        return list;
    }
    
    public List<RoomBean> findRoomsDetails(String location, String street,String checkInDate, String checkOutDate){
        String roomType,description;
        int hotelID=0,capacity=0;
        double price;
        
        
        RoomBean room = null;
        List<RoomBean> list = new ArrayList<RoomBean>();
        List<RoomBean> availabilityList = new ArrayList<RoomBean>();
        List<RoomBean> newlist = new ArrayList<RoomBean>();
        Service service=new Service();
                
         try {
             Connection conn = connectDB.connectToDB();
             Statement stmt = conn.createStatement();
             String searchHotel = "select * from Hotel where state='"+location+"' AND street='"+street+"'";
             System.out.println("Search Location "+searchHotel);
             ResultSet rs = stmt.executeQuery(searchHotel);
             if( rs != null){
                 while(rs.next()){
                 hotelID = rs.getInt("HotelID");                     }
                 System.out.println(hotelID);
             }else{
                 System.out.println("Hotel is not found in this location");
             }
             
             Statement stmt1 = conn.createStatement();
             String searchRoom = "select * from Room where HotelID="+hotelID;
             System.out.println("Search Room "+searchRoom);
             ResultSet rs1 = stmt1.executeQuery(searchRoom);
             if(rs1 !=null){
                 while(rs1.next()){
                     room = new RoomBean();
                     roomType = rs1.getString("RType");
                     description = rs1.getString("Description");
                     price = rs1.getDouble("Price");
                     capacity = rs1.getInt("Capacity");
                     room.setHotelID(rs1.getInt("HotelID"));
                     room.setRoomNo(rs1.getString("RoomNo"));
                     room.setFloor(rs1.getInt("Floor"));
                     room.setRoomType(roomType);
                     room.setDescription(description);
                     room.setPrice(price);
                     room.setCapacity(capacity);
                     System.out.println(roomType+"\t"+description+"\t"+price+"\t"+capacity);
                     list.add(room);
                 }
                 if(list.size()>0){
                      availabilityList=service.checkAvailabilityOfRooms(list, checkInDate, checkOutDate, hotelID);
                    newlist= service.discounted_room(list, checkInDate, checkOutDate);
                     
                 }
             }else{
                 System.out.println("Rooms is not found in this location");
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
    public List<RoomBean> discounted_room(List<RoomBean> l,String CheckIndate, String checkOutDate){
        try {
        Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        for(int i=0;i<l.size();i++){
             
             
            String Query="SELECT Discount FROM Discounted_Room WHERE HotelID="+l.get(i).getHotelID()+" AND RoomNo="+l.get(i).getRoomNo()+" AND StartDate<='"+CheckIndate+"' AND EndDate>='"+checkOutDate+"'";
            System.out.println("Query is: "+Query);
            ResultSet rs=stmt.executeQuery(Query);
            if(rs.next()){
                System.out.println("Inside the if loop:in discounted_room method");
                l.get(i).setDiscountedPrice(l.get(i).getPrice()-(l.get(i).getPrice()*(rs.getDouble("Discount")/100)));
                System.out.println(l.get(i).getDiscountedPrice());
            }
        }   
        }catch(Exception e){
            
        }
        return l;
    }
    
    public boolean checkCNumber(String cNumber){
        long cardNumber=0;
        boolean isCardNotPresent=false;
        Connection conn = connectDB.connectToDB();
         try {
             Statement stmt = conn.createStatement();
            
           
             String isCard= "select CNumber from  Credit_Card where CNumber='"+cNumber+"'";
             System.out.println(isCard);
             ResultSet isCardRs = stmt.executeQuery(isCard);
             if(isCardRs.next()){
                    // cardNumber=isCardRs.getInt("CNumber");
                    isCardNotPresent=false;
              }else{
                 System.out.println("Card Not present");
                 isCardNotPresent= true;
             }
              conn.close();
             } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
                  
         return isCardNotPresent;
    }
    
    public int addCardDetails(CreditCardBean bean){
        Connection connection = connectDB.connectToDB();
        int i=0;
         try {
             Statement st= connection.createStatement();
           //  long CNumber= Long.parseLong(UIDetails.get("cardNumber"));
             
             
             String insertStmt = "insert into Credit_Card values('"+bean.getCNumber()+"','"+bean.getCType()+"','"+bean.getName()+"','"+bean.getBAddress()+"',"+bean.getSecurityCode()+",'"+bean.getExpDate()+"')";
             
             System.out.println(insertStmt);
             
          i=  st.executeUpdate(insertStmt);
             
             System.out.println("Inserted successfully"+i);
             connection.close();
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return i;
        
    }
    
    public void addReservationDetails(ReservationBean bean,String emailID){
        System.out.println("Adding reservation data with email\t"+emailID);
        Connection connection = connectDB.connectToDB();
        int id=0;
         try {
             Statement stmt = connection.createStatement();
             String searchCustomer = "select CID from Customer where Email='"+emailID+"'";
              ResultSet cust = stmt.executeQuery(searchCustomer);
              while(cust.next()){
                  id=cust.getInt("CID");
                  bean.setCID(id);
                  System.out.println("Customer ID with email id\t"+id+"\t"+emailID);
              }
             Statement stm2= connection.createStatement();
              String insertResevation="insert into Reservation(CID,CNumber,RDate) values ("+id+",'"+bean.getCNumber()+"','"+bean.getRDate()+"')";
              System.out.println("Inserting reservation table \t"+insertResevation);
              stm2.executeUpdate(insertResevation);
              
              connection.close();
              
              
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
    }
    
    public void addRoomReservationDetails(RoomReservationBean reserve, ReservationBean resvBean ){
        Connection conn = connectDB.connectToDB();
        long invoiceNo;
         try {
             Statement stmt = conn.createStatement();
             String getInvoice = "select InvoiceNo from Reservation where CID="+resvBean.getCID()+" AND CNumber='"+resvBean.getCNumber()+"' AND RDate='"+resvBean.getRDate()+"'";
             
             System.out.println(""+getInvoice);
             ResultSet resv= stmt.executeQuery(getInvoice);
             if(resv.next()){
                 invoiceNo = resv.getLong("InvoiceNo");
                 System.out.println(""+invoiceNo);
                 resvBean.setInvoiceNum(invoiceNo);
                 reserve.setInvoiceNo(invoiceNo);
             }else{
                 System.out.println("No Invoice Number");
             }
             
             if(resvBean.getInvoiceNum()>0){
               Statement stmt1 = conn.createStatement();  
               String insertRoom="insert into Room_Reservation values ('"+reserve.getCheckInDate()+"',"+reserve.getHotelID()+","+resvBean.getInvoiceNum()+",'"+reserve.getRoomNo()+"','"+reserve.getCheckOutDate()+"')";
               System.out.println(insertRoom);
               stmt1.executeUpdate(insertRoom);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    public List<RoomBean> checkAvailabilityOfRooms(List<RoomBean> l,String CheckIndate, String checkOutDate,int hotelID){
  try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        for(int i=0;i<l.size();i++){
        String Query="select RoomNo from Room_Reservation where HotelID="+hotelID+" AND CheckInDate<'"+checkOutDate+"' AND CheckoutDate>'"+CheckIndate+"'";
        System.out.println("availability query is: "+Query);
         ResultSet rs=stmt.executeQuery(Query);
         if(rs!=null){
         while(rs.next()){
             System.out.println("inside while loop checking avaialbility!!!!!");
            if(l.get(i).getRoomNo().equals(rs.getString("RoomNo"))){
                 System.out.println("inside while loop checking avaialbility and matchiing room number!!!!!");
                l.get(i).setAvailability("unavailable");
                
            }
         }
         }
        }
        conn.close();
}        catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
    return l;
}
    
    public  List<RoomReservationBean> invoiceDetailsofUser(String userName){
         Connection conn = connectDB.connectToDB();
         RoomReservationBean roomRes = null;
         List<RoomReservationBean> listOfReservation = new ArrayList<RoomReservationBean>();
         int cid=0;
         try {
             Statement stmt = conn.createStatement();
             String cidSelect = "select CID from Customer where Email='"+userName+"'";
             System.out.println(cidSelect);
             ResultSet rset=stmt.executeQuery(cidSelect);
             if(rset!=null){
                 while(rset.next()){
                    cid=rset.getInt("CID");
                     System.out.println("CID from Customer"+cid);
                 }
                 
             }else{
                 System.out.println("Customer details not found");
             }
             
             if(cid>0){
             
             String invoice_details="select * from Reservation NATURAL JOIN Room_Reservation where CID="+cid;
             Statement stm2= conn.createStatement();
             ResultSet rs= stmt.executeQuery(invoice_details);
             if(rs!=null){
                 while(rs.next()){
                     roomRes = new RoomReservationBean();
                     roomRes.setInvoiceNo(rs.getLong("InvoiceNo"));
                     roomRes.setCid(rs.getInt("CID"));
                     roomRes.setCardNumber(rs.getString("CNumber"));
                     roomRes.setCheckInDate(rs.getString("CheckInDate"));
                     roomRes.setHotelID(rs.getInt("HotelID"));
                     roomRes.setRoomNo(rs.getString("RoomNo"));
                     roomRes.setCheckOutDate(rs.getString("CheckoutDate"));
                     listOfReservation.add(roomRes);
                 }
                 
             }
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listOfReservation;
    }
    
    public List<ServiceBean> getServiceDetails(int hotelID){
         Connection conn = connectDB.connectToDB();
         ServiceBean sb=null;
         List<ServiceBean> serviceList = new ArrayList<ServiceBean>();
         try {
             Statement stmt = conn.createStatement();
             String select_Service = "select * from Service where HotelId="+hotelID;
             ResultSet rs= stmt.executeQuery(select_Service);
             
             if(rs!=null){
                 while(rs.next()){
                     sb=new ServiceBean();
                     sb.setSType(rs.getString("SType"));
                     sb.setHotelID(rs.getInt("HotelID"));
                     sb.setSprice(rs.getInt("SPrice"));
                     serviceList.add(sb);
                 }
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        return serviceList;
        
    }
    
    public List<BreakfastBean> getBreakfastDetails(int hotelID){
        
         Connection conn = connectDB.connectToDB();
         BreakfastBean bb=null;
         List<BreakfastBean> breakfastList = new ArrayList<BreakfastBean>();
         try {
             Statement stmt = conn.createStatement();
             String select_Breakfast = "select * from Breakfast where HotelId="+hotelID;
             ResultSet rs= stmt.executeQuery(select_Breakfast);
             
             if(rs!=null){
                 while(rs.next()){
                     bb=new BreakfastBean();
                     bb.setBType(rs.getString("BType"));
                     bb.setHotelID(rs.getInt("HotelID"));
                     bb.setDescription(rs.getString("Description"));
                    bb.setBPrice(rs.getInt("BPrice"));
                    breakfastList.add(bb);
                 }
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        return breakfastList;
        
    }
    public void addReservationService(List<String> stypes, String Num, String ID){
        
        int hotelID= Integer.parseInt(ID);
        int invoiceNum= Integer.parseInt(Num);
        RResv_ServiceBean rServBean= new RResv_ServiceBean();
         Connection conn = connectDB.connectToDB();
          Statement stmt,stmt2;
         try {
             stmt = conn.createStatement();
             String selectRoomResv = "select * from Room_Reservation where InvoiceNo="+invoiceNum;
             ResultSet rs= stmt.executeQuery(selectRoomResv);
             
                 while(rs.next()){
                                 
                     
                     rServBean.setRoomNo(rs.getString("RoomNo"));
                     rServBean.setCheckInDate(rs.getString("CheckInDate"));
                     rServBean.setHotelID(rs.getInt("HotelID"));
                     
                 }
             
                 
            for(String service : stypes){
                stmt2 = conn.createStatement();
                String insertRoomResv = "insert into RRESV_SERVICE values('"+service+"',"+rServBean.getHotelID()+",'"+rServBean.getRoomNo()+"','"+rServBean.getCheckInDate()+"')";
           //  ResultSet rs= stmt.executeQuery(selectRoomResv);
                System.out.println(insertRoomResv);
              stmt2.executeUpdate(insertRoomResv);
                
            }     
             
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
             
             
        
    }
    
    public int getMaximumOccupancyInRoom(String invoiceNo){
        int capacity=0;
        int invoiceNum=Integer.parseInt(invoiceNo);
        Connection conn = connectDB.connectToDB();
         try {
             Statement stmt = conn.createStatement();
             String selectMax ="Select Capacity from Room_Reservation Natural Join Room where InvoiceNo="+invoiceNum;
             
              ResultSet rs= stmt.executeQuery(selectMax);
             
                 while(rs.next()){
                     capacity = rs.getInt("Capacity");
                 }
                                 
             System.out.println("Maximum Occupancy-->"+capacity);
             
             
             conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
          
        
        
        return capacity;
    }

    public void addReservationBreakfast(String invoiceNo, String ID, List<String> BType, List<String> quantities) {
        RResv_BreakfastBean breakfastBean= new RResv_BreakfastBean();
        Connection conn = connectDB.connectToDB();
         try {
        int hotelID= Integer.parseInt(ID);
        int invoiceNum= Integer.parseInt(invoiceNo);
             Statement stmt, stmt2;
             stmt = conn.createStatement();
                       
             String selectRoomResv = "select * from Room_Reservation where InvoiceNo="+invoiceNum;
             ResultSet rs= stmt.executeQuery(selectRoomResv);
             
                 while(rs.next()){
                     breakfastBean.setRoomNo(rs.getString("RoomNo"));
                     breakfastBean.setCheckInDate(rs.getString("CheckInDate"));
                     breakfastBean.setHotelID(rs.getInt("HotelID"));
                     
                 }
                 
                 
                 for(int i=0;i<BType.size();i++){
                     stmt2=conn.createStatement();
                     String insertstmt="insert into RRESV_BREAKFAST values('"+BType.get(i)+"',"+hotelID+",'"+breakfastBean.getRoomNo()+"','"+breakfastBean.getCheckInDate()+"',"+quantities.get(i)+")";
                     System.out.println(insertstmt+"\n\n");
                      stmt2.executeUpdate(insertstmt);
                 }
                 conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
     public int insertIntoService_Review(Service_ReviewBean review){
      int j=0;
      try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query= "insert into Service_Review(Rating,Text,HotelID,CID,SType)values("+review.getRating()+",'"+review.getText()+"',"+review.getHotelID()+","+review.getCID()+",'"+review.getSType()+"')";
        System.out.println("Query is :"+Query);
       j= stmt.executeUpdate(Query);
      
  }      catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
       return j;
}
     
      public List<Service_ReviewBean> getServiceDetailswithUserName(String UserName){
      Service_ReviewBean servicereview=null;
      List<Service_ReviewBean> list=new ArrayList<Service_ReviewBean>();
      int CID=0;
  
    try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query=  "select CID from Customer where Email='"+UserName+"'";
        ResultSet rs=stmt.executeQuery(Query);
        if(rs.next()){
            CID=rs.getInt("CID");
          }
        
       String Query1="SELECT * FROM RRESV_SERVICE WHERE (CheckInDate,HotelID,RoomNo) IN(select CheckInDate,HotelID,RoomNo from Room_Reservation where InvoiceNo IN(select InvoiceNo from Reservation where CID="+CID+"))"; 
       ResultSet rs1=stmt.executeQuery(Query1);
        while(rs1.next()){
            servicereview=new Service_ReviewBean();
            servicereview.setCID(CID);
            servicereview.setCheckInDate(rs1.getString("CheckInDate"));
            servicereview.setHotelID(rs1.getInt("HotelID"));
            servicereview.setRoomNo(rs1.getString("RoomNo"));
            servicereview.setSType(rs1.getString("SType"));
            list.add(servicereview);
        }
        
  }      catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
    return list;
}
 
      
        public List<Breakfast_ReviewBean> getBreakfastDetailswithUserName(String UserName){
     Breakfast_ReviewBean breakfastreview=null;
      List<Breakfast_ReviewBean> list=new ArrayList<Breakfast_ReviewBean>();
      int CID=0;
  
    try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query=  "select CID from Customer where Email='"+UserName+"'";
        ResultSet rs=stmt.executeQuery(Query);
        if(rs.next()){
            CID=rs.getInt("CID");
          }
        
       String Query1="SELECT * FROM RRESV_BREAKFAST WHERE (CheckInDate,HotelID,RoomNo) IN(select CheckInDate,HotelID,RoomNo from Room_Reservation where InvoiceNo IN(select InvoiceNo from Reservation where CID="+CID+"))"; 
       ResultSet rs1=stmt.executeQuery(Query1);
        while(rs1.next()){
            breakfastreview=new Breakfast_ReviewBean();
            breakfastreview.setCID(CID);
            breakfastreview.setCheckInDate(rs1.getString("CheckInDate"));
            breakfastreview.setHotelID(rs1.getInt("HotelID"));
            breakfastreview.setRoomNo(rs1.getString("RoomNo"));
            breakfastreview.setBType(rs1.getString("BType"));
            list.add(breakfastreview);
        }
        
  }      catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
    return list;
}
        
          public int insertIntoBreakfast_Review(Breakfast_ReviewBean review){
      int j=0;
      try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query= "insert into Breakfast_Review(Rating,Text,HotelID,CID,BType)values("+review.getRating()+",'"+review.getText()+"',"+review.getHotelID()+","+review.getCID()+",'"+review.getBType()+"')";
        System.out.println("Query is :"+Query);
      j= stmt.executeUpdate(Query);
      
  }      catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
       return j;
}
          
           public int insertIntoRoom_Review(Room_ReviewBean review){
     int j=0;
      try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query= "insert into Room_Review(Rating,Text,CID,HotelID,RoomNo)values("+review.getRating()+",'"+review.getText()+"',"+review.getCID()+","+review.getHotelID()+",'"+review.getRoomNo()+"')";
        System.out.println("Query is :"+Query);
       j= stmt.executeUpdate(Query);
      
  }      catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
       return j;
     
     
 }
 public  List<RoomReservationBean>  getRoomReservationDetails(String username){
     RoomReservationBean room_Reservation=null;
     List<RoomReservationBean> list=new ArrayList<RoomReservationBean>();
     int CID=0;
     try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query="select CID from Customer where Email='"+username+"'";
        ResultSet rs=stmt.executeQuery(Query);
        if(rs.next()){
           CID=rs.getInt("CID");
        }
      String Query1="select * from Room_Reservation natural join Reservation where CID="+CID;
      ResultSet rs1=stmt.executeQuery(Query1);
      while(rs1.next()){
           room_Reservation = new RoomReservationBean();
          room_Reservation.setCid(CID);
          room_Reservation.setCheckInDate(rs1.getString("CheckInDate"));
          room_Reservation.setCheckOutDate(rs1.getString("CheckoutDate"));
          room_Reservation.setHotelID(rs1.getInt("HotelID"));
          room_Reservation.setRoomNo(rs1.getString("RoomNo"));
          room_Reservation.setInvoiceNo(rs1.getLong("InvoiceNo"));
          room_Reservation.setCardNumber(rs1.getString("CNumber"));
          list.add(room_Reservation);
      }
 
}        catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
     return list;
 }

    public List<Breakfast_ReviewBean> highestRatedBreakfast(String startDate, String endDate) {
         
             Breakfast_ReviewBean br= null;
             List<Breakfast_ReviewBean> list = new ArrayList<Breakfast_ReviewBean>();
             try{
             Connection conn = connectDB.connectToDB();
             Statement stmt = conn.createStatement();
             
             String Query="select sum(Rating) as Rating, BType from Breakfast_Review Natural Join RRESV_BREAKFAST where CheckInDate BETWEEN '"+startDate+"' and '"+endDate+"' Group by Btype";
           
            
             ResultSet rs1=stmt.executeQuery(Query);
             while(rs1.next()){
                 br = new Breakfast_ReviewBean();
                 br.setRating(rs1.getInt("Rating"));
                 br.setBType(rs1.getString("BType"));
                
                 list.add(br);
             }
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
    }
    
    public  ArrayList< Breakfast_ReviewBean> getBrekfastStatistics(String fromDate, String toDate){
          Breakfast_ReviewBean bean=null;
          ArrayList< Breakfast_ReviewBean> list=new ArrayList< Breakfast_ReviewBean>();
         try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query=  "select Max(Rating) AS Max_Rating, BType from Breakfast_Review Natural Join RRESV_BREAKFAST where CheckInDate BETWEEN '"+fromDate+"' and '"+toDate+"' Group by Btype order by Max_Rating desc"; 
        System.out.println("Query is: "+Query); 
        ResultSet rs=stmt.executeQuery(Query);
        while(rs.next()){
         bean=new  Breakfast_ReviewBean();
         bean.setBType(rs.getString("BType"));
         bean.setRating(rs.getInt("Max_Rating"));
          list.add(bean);
        }
      }  catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
      return list;
      
      
}
        public  ArrayList< Service_ReviewBean> getServiceStatistics(String fromDate, String toDate){
          Service_ReviewBean bean=null;
          ArrayList< Service_ReviewBean> list=new ArrayList< Service_ReviewBean>();
         try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query=  "select Max(Rating) AS Max_Rating, SType from Service_Review Natural Join RRESV_SERVICE where CheckInDate BETWEEN '"+fromDate+"' and '"+toDate+"' Group by Stype order by Max_Rating desc"; 
        System.out.println("Query is: "+Query); 
        ResultSet rs=stmt.executeQuery(Query);
        while(rs.next()){
         bean=new  Service_ReviewBean();
         bean.setSType(rs.getString("SType"));
         bean.setRating(rs.getInt("Max_Rating"));
          list.add(bean);
        }
      }  catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
      return list;
      
      
}
        public  ArrayList< RoomBean> gettop5Statistics(String fromDate, String toDate){
          RoomBean bean=null;
          ArrayList< RoomBean> list=new ArrayList< RoomBean>();
         try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query=  "SELECT sum(R.Price) AS Max_Amount, C.CName AS CustomerName\n" +
"FROM Reservation RE\n" +
"Inner join Room_Reservation RR on RE.InvoiceNo=RR.InvoiceNo\n" +
"Inner join Room R on RR.RoomNo=R.RoomNo and RR.HotelID = R.HotelID\n" +
"Inner join Customer C on C.CID=RE.CID\n" +
"where RR.CheckInDate Between '"+fromDate+"' AND '"+toDate+"'\n" +
"Group by RE.CID,C.CName ORDER BY Max_Amount DESC"; 
        System.out.println("Query is: "+Query); 
        ResultSet rs=stmt.executeQuery(Query);
        while(rs.next()){
         bean=new  RoomBean();
         bean.setCName(rs.getString("CustomerName"));
         bean.setPrice(rs.getInt("Max_Amount"));
          list.add(bean);
        }
      }  catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
      return list;
      
      
}
        public  ArrayList< Room_ReviewBean> getroomTypeStatistics(String fromDate, String toDate){
          Room_ReviewBean bean=null;
          ArrayList< Room_ReviewBean> list=new ArrayList< Room_ReviewBean>();
         try {
         Connection conn = connectDB.connectToDB();
        Statement stmt = conn.createStatement();
        
        String Query=  "Select max(Max_Rating) as MAX_RATING, T.HotelID,T.Room_Type as ROOM_TYPE \n" +
"from (SELECT Distinct(R.HotelID), Max(RR.Rating) as Max_Rating, R.Rtype As Room_Type From Room_Review as RR Inner Join Room R on R.RoomNo=RR.RoomNo and R.HotelID=RR.HotelID Inner Join Room_Reservation RE on RE.RoomNo=RR.RoomNo and RE.HotelID=RR.HotelID Where RE.CheckInDate Between '"+fromDate+"' and '"+toDate+"' Group by R.Rtype, R.HotelID Order by Max_Rating desc) T \n" +
"group by T.HotelID order by MAX_RATING DESC";
        System.out.println("Query is: "+Query); 
        ResultSet rs=stmt.executeQuery(Query);
        while(rs.next()){
         bean=new  Room_ReviewBean();
         bean.setRtype(rs.getString("Room_Type"));
         bean.setHotelID(rs.getInt("HotelID"));
         bean.setRating(rs.getInt("Max_Rating"));
          list.add(bean);
        }
      }  catch (SQLException ex) {
             Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
         }
      return list;
      
      
}
}

