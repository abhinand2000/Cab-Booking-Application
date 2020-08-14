import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;

public class Reallocation extends TimerTask {
    public void run() {
//        completeTask();
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(6*20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int a=0;// to count number of free(non-busy) drivers
        Connection conn = null;
        int flag =0;
        try {
            // db parameters
            conn=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root");
            Statement stmt = (Statement) conn.createStatement();
            Statement stmt2 = (Statement) conn.createStatement();
            Statement stmt3 = (Statement) conn.createStatement(); 
            Statement stmt4 = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from DRIVERDATA where busy=false");
            ResultSet rs2 = stmt2.executeQuery("select count(*) from locations");
            ResultSet rs3 = stmt3.executeQuery("select * from locations");
            ResultSet rs4 = stmt4.executeQuery("select * from DRIVERDATA");
            rs.next();
            rs2.next();
            a= rs.getInt("count(*)");
//            System.out.println(a);
            int count = rs2.getInt("count(*)");
            System.out.println("count="+count); 
            int ab=a/count;
            int cd=0;
            int ef=a%count;
            if(a<=count)
            {
                while(rs4.next() ){
//                     System.out.println(rs4.getString("dname"));
                    if(rs4.getBoolean("busy")==false)
                    {
                        rs3.next();
                        String d=rs4.getString("vehicleid");//System.out.println(d);
                        String st=rs3.getString("locationName");//System.out.println("--"+st);
                        String query = "UPDATE DRIVERDATA SET location=? WHERE vehicleid=?";
                        PreparedStatement statement = conn.prepareStatement(query);
                        statement.setString(1, st);
                        statement.setString(2, d );
                        statement.executeUpdate();
////                        rs.executeUpdate("UPDATE DRIVERDATA SET LOCATION = '"+location+"',RATING = '"+rating+"',BUSY = '"+busy+"',RIDES='"+rides+"' WHERE VEHICLEID = '"+vid+"' ");
                    }
                }
            }
            else
            {
                rs3.next();
                    while(rs4.next()){
                        if(rs4.getBoolean("busy")==false)
                        {
                            if(ef!=0)
                            {
                                if(cd%(ab+1)==0 &&cd!=0)
                                {
                                    rs3.next();
                                    ef--;
                                }
                            }
                            else
                            {
                                if(cd%ab==0 && cd!=0)
                                {
                                    rs3.next();
                                    ef--;
                                }
                            }
                            String d=rs4.getString("vehicleid");
                            String st=rs3.getString("locationName");//System.out.println("--"+st);
                        String query = "UPDATE DRIVERDATA SET location=? WHERE vehicleid=?";
                        PreparedStatement statement = conn.prepareStatement(query);
                        statement.setString(1, st);
                        statement.setString(2, d );
                        statement.executeUpdate();
                            cd++;
        //                    rs.executeUpdate("UPDATE DRIVERDATA SET LOCATION = '"+location+"',RATING = '"+rating+"',BUSY = '"+busy+"',RIDES='"+rides+"' WHERE VEHICLEID = '"+vid+"' ");
                        }
                        
                    }
            }
        } catch (SQLException e) {
        System.out.println(e);
    }
//         finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                  }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
    }

}