import java.sql.*;
public class DriverDatabase 
{
    private Connection con;
    public void openConnection() //Open Connection to Customer Database
    {
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root");  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public boolean availableCab()
            /*Checks if any Driver is available returns true if available and false if not */
    {
       boolean available=false;
       try
       {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root"); 
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select * from driverdata where BUSY = FALSE");
            if(!rs.next()) available=false;
            else available=true;
            con.close();
        }
      catch(Exception e)
      {
          System.out.println(e);
      }
      return available;
    }
    public void updateDriver(Location endlocation,double stars,Cab C) 
    {         /*Updates rating and location and driver status to available on ride completion*/
        C.setlocation(endlocation.getlocationName());
        C.setrating((C.getrating()*C.getrides()+stars)/(C.getrides()+1));
        C.setrides(C.getrides()+1);
        C.setbusy(false);
        try
        {
            Statement st=con.createStatement();
            st.executeUpdate("UPDATE DRIVERDATA SET LOCATION = '"+C.getlocation()+"',RATING = '"+C.getrating()+"',BUSY = false,RIDES='"+C.getrides()+"' WHERE VEHICLEID = '"+C.getvid()+"' ");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void findCab(Location pickup, Cab D)
        /*Finds a suitable driver*/
        /*Finds the driver closest to the pickup point. If 2 drivers are at same distance it picks the one with higher rating*/
        /*Makes Driver status as Busy */
    {
        double distanceMin=200;
        double distanceTemp;
        double ratingTemp;
        double ratingMax=0.0;
         try
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root"); 
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select * from driverdata where BUSY = FALSE");
            //Loop to find the closest driver with highest rating
            while(rs.next())
            {
                Location driverloc = new Location(rs.getString(6));
                distanceTemp=Math.abs(pickup.getxcoordinate()-driverloc.getxcoordinate());
                if(distanceTemp<distanceMin)
                {
                    D.setValues(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getBoolean(7),rs.getDouble(8),rs.getDouble(9));
                    distanceMin=distanceTemp;
                    ratingMax=rs.getDouble(8);
                }
                else if(distanceTemp==distanceMin)
                {
                    ratingTemp=rs.getDouble(8);
                    if(ratingTemp>ratingMax)
                        D.setValues(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getBoolean(7),rs.getDouble(8),rs.getDouble(9));
                    ratingMax=ratingTemp;
                }
            }
            String vidtemp=D.getvehicleid();
            st.executeUpdate("update driverdata set BUSY = true where VEHICLEID='"+vidtemp+"' "); //Make driver busy
            con.close();
        }
      catch(Exception e)
      {
          System.out.println(e);
      }
    }
    public void CloseConnection()
    {
        try
        {
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
