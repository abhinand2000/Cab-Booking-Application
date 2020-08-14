import java.sql.*;
public class CustomerDatabase 
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
    public void registerAccount(String name,String username,String email,String password,String phonenumber,Customer C)
    {
        try
        {
            Statement st=con.createStatement();
            st.executeUpdate("INSERT INTO CUSTOMERDATA (NAME,USERNAME,PASSWORD,EMAIL,WALLET,PHONENUMBER) "
            +"VALUES ('"+name+"','"+username+"','"+password+"','"+email+"',0.0,'"+phonenumber+"')");
            ResultSet rs=st.executeQuery("select userid from customerdata where username = '"+username+"'");
            rs.next();
            C.setusername(username);
            C.setpassword(password);
            C.setname(name);
            C.setuserID(rs.getInt(1));
            C.setwallet(0.0);
            C.setemail(email);
            C.setphonenumber(phonenumber);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public boolean registerCheck(String name,String username,String email,String password,String phonenumber)
            /*Checks if username or emailid is already taken. Returns true if we can register false if we cannot register*/
    {
        boolean rc=false;
        try
        {
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select * from CUSTOMERDATA where USERNAME = '"+username+"' OR EMAIL = '"+email+"' OR PHONENUMBER = '"+phonenumber+"'");
            if(!rs.next()) rc=true;
            else rc=false;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return rc;
    }
    public boolean LoginCheck(String username,String password)
            /*Checks if Login Credentials are valid. Return true if valid and false if not valid */
    {
        boolean lc=false;
        try
        {
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select * from CUSTOMERDATA where USERNAME = '"+username+"' AND PASSWORD = '"+password+"' ");
            if(!rs.next()) lc=false;
            else lc=true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return lc;
    }
    public void LogIn(String username, String password,Customer C)
            /* Logs in using credentials and sets all values of customer object from database*/
    {
        try
        {
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select * from CUSTOMERDATA where USERNAME = '"+username+"'");
            C.setusername(username);
            C.setpassword(password);
            rs.next();
            C.setname(rs.getString(2));
            C.setuserID(rs.getInt(1));
            C.setwallet(rs.getDouble(6));
            C.setemail(rs.getString(5));
            C.setphonenumber(rs.getString(7));
            st.executeUpdate("update customerdata set log = true where username = '"+username+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void updatewallet(double amount,Customer C)
    {
        C.setwallet(C.getwallet()+amount);
        try
        {
            Statement st=con.createStatement();
            st.executeUpdate("UPDATE CUSTOMERDATA SET WALLET = '"+C.getwallet()+"' WHERE USERNAME = '"+C.getusername()+"' ");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void logout(Customer C)
    {
        try
        {
            Statement st=con.createStatement();
            st.executeUpdate("UPDATE CUSTOMERDATA SET log = false WHERE USERNAME = '"+C.getusername()+"' ");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public boolean isloggedin(String username, String password)
    {
        boolean lc=false;
        try
        {
             Statement st=con.createStatement();
             ResultSet rs = st.executeQuery("select * from CUSTOMERDATA where USERNAME = '"+username+"'");
             rs.next();
             lc=rs.getBoolean(8);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return lc;
    }
    public void logallout()
    {
        try
        {
            Statement st=con.createStatement();
            st.executeUpdate("update customerdata set log=0");
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
