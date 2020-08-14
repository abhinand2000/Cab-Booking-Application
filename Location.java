import java.sql.*;
import java.lang.Math;
public class Location 
{
    private int locationID;
    private String locationName;
    private double xcoordinate;
    private double ycoordinate;
    public Location() {}
    public Location(String locationName)
            /*gets Location details from database given location name */
    {
        this.locationName=locationName;
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root"); 
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from LOCATIONS where LOCATIONNAME='"+locationName+"'");
            rs.next();
            locationID=rs.getInt(1);
            xcoordinate=rs.getDouble(3);
            ycoordinate =rs.getDouble(4);
            con.close();
            //System.out.println(locationID+" "+locationName+" "+xcoordinate);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public double distance(Location L)
            /*Calculates Distance between 2 locations */
    {
        double x=L.getxcoordinate();
        double y=L.getycoordinate();
        return (Math.abs(y-ycoordinate)+Math.abs(x-xcoordinate));
    }
    public String getlocationName() //returns Location Name
    {
        return locationName;
    }
    public int getlocationID() //returns Location ID
    {
        return locationID;
    }
    public double getxcoordinate() //returns X Coordinate
    {
        return xcoordinate;
    }
    public double getycoordinate()
    {
        return ycoordinate;
    }
}
