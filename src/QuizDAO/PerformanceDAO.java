/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizDAO;

import Quiz.DBUtil.DBConnection;
import Quiz.Pojo.Performance;
import Quiz.Pojo.StudentScore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author abc
 */
public class PerformanceDAO {
    public static ArrayList<String> getAllExamId(String studentId) throws SQLException
    {
        ArrayList<String>examidlist=new ArrayList<>();
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Select examid from performance where userid=?");
        ps.setString(1, studentId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            String id=rs.getString(1);
            examidlist.add(id);
        }
        return examidlist;
    }
    public static void addPerformance(Performance performance) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("insert into performance values(?,?,?,?,?,?,?)");
        ps.setString(1, performance.getUserid());
        ps.setString(2, performance.getExamid());
        ps.setInt(3, performance.getRight());
        ps.setInt(4, performance.getWrong());
        ps.setInt(5,performance.getUnattempted());
        ps.setDouble(6,performance.getPer());
        ps.setString(7, performance.getLanguage());
        ps.executeUpdate();
        
    }
    public static ArrayList<Performance> getAllData() throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ArrayList<Performance> performlist= new ArrayList<>();
        ResultSet rs=st.executeQuery("Select * from performance");
        while(rs.next())
        {
            String userid=rs.getString(1);
            String examid=rs.getString(2);
            int right=rs.getInt(3);
            int wrong=rs.getInt(4);
            int unattempted=rs.getInt(5);
            double per=rs.getDouble(6);
            String language=rs.getString(7);
            Performance p= new Performance(examid,language,userid,right,wrong,unattempted,per);
            performlist.add(p);
            
        }
        return performlist;
        
    }
    public static ArrayList<String> getAllStudentId() throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ArrayList<String> studentIdList= new ArrayList<>();
        ResultSet rs=st.executeQuery("Select distinct userid from performance");
        while(rs.next())
        {
            String id=rs.getString(1);
            studentIdList.add(id);
        }
        return studentIdList;
        
        
    }
     public static StudentScore getScore(String studentId,String examId) throws SQLException
     {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Select language,per from performance where userid=? and examid=?");
        ps.setString(1, studentId);
        ps.setString(2, examId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        StudentScore obj=new StudentScore();
        obj.setLanguage(rs.getString(1));
        obj.setPer(rs.getDouble(2));
        return obj;
     }
}
