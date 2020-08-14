/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomerDatabase cdb=new CustomerDatabase();
        cdb.openConnection();
        DriverDatabase ddb=new DriverDatabase();
        ddb.openConnection();
        LoginForm F=new LoginForm(cdb,ddb);
        Delay D=new Delay();
        
        Thread T1=new Thread(new Runnable()
        {
            public void run()
            {
                D.delay();
            }
        });
        Thread T2=new Thread(new Runnable()
        {
            public void run()
            {
                F.setVisible(true);
            }            
        });
        
        T1.start();
        T2.start();
    }
}
