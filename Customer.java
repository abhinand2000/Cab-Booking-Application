import java.sql.*;
public class Customer
 {
	private String name,username,email,password,phonenumber;
        private int userID;
	private double wallet;
        //private Connection con;
        //public Customer()
                //Opens connection to database
        //{
            /*
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
            */
        //}
        /*
        public boolean registerCheck(String name,String username,String email,String password,String phonenumber)
                //Checks if username or emailid is already taken. Returns true if we can register false if we cannot register
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
            
            //return cdb.registerCheck(name, username, email, password, phonenumber);
            
        }
        */
        /*
	public void registerAccount(String name,String username,String email,String password,String phonenumber)
                //Create Account and add it to the database
	{
            this.name=name;
            this.username=username;
            this.password=password;
            this.email=email;
            this.phonenumber=phonenumber;
            wallet=0.0;
            try
            {
                Statement st=con.createStatement();
                st.executeUpdate("INSERT INTO CUSTOMERDATA (NAME,USERNAME,PASSWORD,EMAIL,WALLET,PHONENUMBER) "
                +"VALUES ('"+name+"','"+username+"','"+password+"','"+email+"',0.0,'"+phonenumber+"')");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            //cdb.registerAccount(name, username, email, password, phonenumber, this);
            
	}
        public boolean LoginCheck(String username,String password)
                //Checks if Login Credentials are valid. Return true if valid and false if not valid 
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
            
            //return cdb.LoginCheck(username, password);
            
        }
	public void LogIn(String username, String password)
                // Logs in using credentials and sets all values of customer object from database
	{
            try
            {
                Statement st=con.createStatement();
                ResultSet rs = st.executeQuery("select * from CUSTOMERDATA where USERNAME = '"+username+"'");
                this.username=username;
                this.password=password;
                rs.next();
                this.name=rs.getString(2);
                this.userID=rs.getString(1);
                this.wallet=rs.getDouble(6);
                this.email=rs.getString(5);
                this.phonenumber=rs.getString(7);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            //cdb.LogIn(username, password, this);
            
	}
	public void addMoney(double money) //Add money to wallet
            //Adds money to wallet and updates database
	{
            wallet+=money;
            try
            {
                Statement st=con.createStatement();
                st.executeUpdate("UPDATE CUSTOMERDATA SET WALLET = '"+wallet+"' WHERE USERNAME = '"+username+"' ");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            //wallet+=money;
            //cdb.updatewallet(wallet, username);
            
	}
	public void deductMoney(double money)
                Deducts Money from wallet and updates Database
	{
            wallet-=money;
            
            try
            {
                Statement st=con.createStatement();
                st.executeUpdate("UPDATE CUSTOMERDATA SET WALLET = '"+wallet+"' WHERE USERNAME = '"+username+"' ");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            //wallet-=money;
            //cdb.updatewallet(wallet, username);
            
	}
        */
	boolean checkTotal(double tripFare)
                /*Checks if total amount is sufficient for booking*/
	{
		if(wallet<300.0||wallet<tripFare)
			return false;
		else
			return true;
	}
        public double getwallet()
        {
            return wallet;
        }
        public String getusername()
        {
            return username;
        }
        public String getname()
        {
            return name;
        }
        public String getphonenumber()
        {
            return phonenumber;
        }
        public int getuserID()
        {
            return userID;
        }
        public String getemail()
        {
            return email;
        }
        public void setname(String name)
        {
            this.name=name;
        }
        public void setusername(String username)
        {
            this.username=username;
        }
        public void setuserID(int userID)
        {
            this.userID=userID;
        }
        public void setpassword(String password)
        {
            this.password=password;
        }
        public void setemail(String email)
        {
            this.email=email;
        }
        public void setphonenumber(String phonenumber)
        {
            this.phonenumber=phonenumber;
        }
        public void setwallet(double wallet)
        {
            this.wallet=wallet;
        }
        /*
        protected void finalize()
                //Closes connection to database
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
        */
}
