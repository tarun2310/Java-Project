/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author abc
 */
public class DBConnection {
    private static Connection con;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","admin","student");
            JOptionPane.showMessageDialog(null,"Successfully connected to the DataBase");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error Disconnecting From the Database:"+ex,"ERROR!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
    public static void closeConnection()
    {
        if(con!=null)
        try{
        con.close();
        JOptionPane.showMessageDialog(null,"Successfully Disconnected to the Database","SUCCESS!",JOptionPane.INFORMATION_MESSAGE);
    }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error Disconnecting From the Database:"+ex,"ERROR!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    
}
