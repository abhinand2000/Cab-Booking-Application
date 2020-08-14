import java.sql.*;
public class Cab {
    
        private String dname,vname,vid,email,phone,location;
        private boolean busy;
        private double rating,rides;
        private Connection con;
        public Cab()
                /*Opens connection to database*/
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
        public void updateDriver(Location endlocation,double stars) 
        {         /*Updates rating and location and driver status to available on ride completion*/
            location=endlocation.getlocationName();
            rating=(rating*rides+stars)/(rides+1);
            rides+=1;
            busy=false;
            try
            {
                Statement st=con.createStatement();
                st.executeUpdate("UPDATE DRIVERDATA SET LOCATION = '"+location+"',RATING = '"+rating+"',BUSY = false,RIDES='"+rides+"' WHERE VEHICLEID = '"+vid+"' ");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        protected void finalize()
                /*Closes connection to database*/
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
        public void setValues(String dname,String vname,String vid,String email,String phone,String location,boolean busy,double rating,double rides)
        {
            /*Sets all class variables */
            this.dname=dname;
            this.vname=vname;
            this.vid=vid;
            this.email=email;
            this.phone=phone;
            this.location=location;
            this.rides=rides;
            this.rating=rating;
            this.busy=busy;
        }
        public void setdname(String dname)
        {
            this.dname=dname;
        }
        public void setvname(String vname)
        {
            this.vname=vname;
        }
        public void setvid(String vid)
        {
            this.vid=vid;
        }
        public void setemail(String email)
        {
            this.email=email;
        }
        public void setphone(String phone)
        {
            this.phone=phone;
        }
        public void setlocation(String location)
        {
            this.location=location;
        }
        public void setbusy(boolean busy)
        {
            this.busy=busy;
        }
        public void setrating(double rating)
        {
            this.rating=rating;
        }
        public void setrides(double rides)
        {
            this.rides=rides;
        }
        public String getlocation()
        {
            return location;
        }
        public String getvehicleid()
        {
            return vid;
        }
        public String getdname()
        {
            return dname;
        }
        public String getvname()
        {
            return vname;
        }
        public String getvid()
        {
            return vid;
        }
        public String getphone()
        {
            return phone;
        }
        public double getrating()
        {
            return rating;
        }
        public double getrides()
        {
            return rides;
        }
        /*
        public boolean driverCheck(String startlocation)
        {
            boolean lc=false;
            try
            {
                Statement st=con.createStatement();
                ResultSet rs = st.executeQuery("select * from DRIVERDATA where BUSY = false ");
                if(!rs.next()) lc=false;
                else lc=true;
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            return lc;
        }
        */
}

