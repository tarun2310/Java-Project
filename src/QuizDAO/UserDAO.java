/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizDAO;

import Quiz.DBUtil.DBConnection;
import Quiz.Pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author abc
 */
public class UserDAO {
    
    public static boolean validateUser(UserPojo user) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Select * from users where USERID=? and PASSWORD=? and USERTYPE=?");
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        
        ResultSet rs=ps.executeQuery();
        return rs.next();
       
    }
    public static boolean registerStudent(UserPojo user) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Insert into users values(?,?,?)");
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ps.setString(3,user.getUserType());
        int result=ps.executeUpdate();
        if(result==1)
            return true;
        return false;
    }
    public static boolean changePassword(String userid,String password) throws SQLException
    {
        UserPojo user=new UserPojo();
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("update users set password=? where userid=?");
        ps.setString(1, password);
        ps.setString(2, userid);
        int result=ps.executeUpdate();
        if(result==1)
            return true;
        return false;
        
        
    }
    
}
