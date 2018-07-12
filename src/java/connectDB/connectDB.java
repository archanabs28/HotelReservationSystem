
package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectDB {
     private static Connection con;
	public static Connection connectToDB()
	{	
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("database name","username","password");
                              
                                
			}
			catch(Exception e)
			{
				System.out.println("Exception is"+e);
			}
			return con;	
        }
    public static void main(String[] args) {
        connectDB d=new connectDB();
		d.connectToDB();
    }
}
    

