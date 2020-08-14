import java.sql.*;
import java.lang.Math;
public class Booking
{
    private Cab D=new Cab();
    private Customer C=new Customer();
    public Location pickup=new Location();public Location destination=new Location();
    private double distance, tripFare,time,unitFare,speed;
    private CustomerDatabase cdb=new CustomerDatabase();
    private DriverDatabase ddb=new DriverDatabase();
    public Booking() {}
    public Booking(Customer C,Location pickup,Location destination,CustomerDatabase cdb,DriverDatabase ddb)
    {
        /*Constructor which takes customer,pickup point and destination point as parameters
        Also initialises distance, fare, time
        */
        this.C=C;
        this.pickup=pickup;
        this.destination=destination;
        unitFare=10.0;
        speed=30.0;
        distance=pickup.distance(destination);
        tripFare=unitFare*distance;
        time=distance/speed;
        time*=10;   
        this.cdb=cdb;
        this.ddb=ddb;
        D=new Cab();
    }
    public Cab getdriver()
    {
            return D;
    }
    public boolean availableCab()
            /*Checks if any Driver is available returns true if available and false if not */
    {
        /*
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
        */
        return ddb.availableCab();
    }
    public boolean checkMoney()
            /*Checks if customer has enough money returns true if he has money false if not */
    {
        if(C.checkTotal(tripFare)) return true;
        else return false;
    }
    public void findCab()
    {
        /*
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
        */
        ddb.findCab(pickup,D);
    }
    public void endTrip(double rating)
        /* Ends the Trip */
    {
        ddb.updateDriver(destination, rating, D);
        cdb.updatewallet(-1*tripFare, C);
        /* Customer will rate the driver.
        Driver rating will be updated. Driver status will be changed to available in database
        Driver new location will be destination
        */
        //C.deductMoney(tripFare);
        /*Deduct trip fare from wallet of customer*/
    }
    public double getfare()
    {
        return tripFare;
    }
    public double gettime()
    {
        return time;
    }
}
