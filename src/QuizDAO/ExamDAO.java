/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizDAO;

import Quiz.DBUtil.DBConnection;
import Quiz.Pojo.ExamPojo;
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
public class ExamDAO {
    
    public static String getExamId() throws SQLException
    {
        String qry="Select count(*) as totalrows from exam";
        int rowCount;
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(qry);
        rs.next();
        rowCount=rs.getInt(1);
        String newId="EX-"+(rowCount+1);
        return newId;
    }
    public static boolean addExam(ExamPojo newExam) throws SQLException
    {
        String qry="insert into exam values(?,?,?)";
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(qry);
        ps.setString(1, newExam.getExamid());
        ps.setString(2, newExam.getLanguage());
        ps.setInt(3, newExam.getTotalQuestions());
        int ans=ps.executeUpdate();
        return ans==1;
    }
    public static ArrayList<String> getExamIdBySubject(String subject) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Select examid from exam where language=?");
        ps.setString(1, subject);
        ResultSet rs=ps.executeQuery();
        ArrayList<String>examlist=new ArrayList<>();
        while(rs.next())
        {
            examlist.add(rs.getString(1));
        }
        return examlist;
    }
    public static int getQuestionCountByExam(String examid) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Select total_question from exam where examid=?");
        ps.setString(1, examid);
        ResultSet rs=ps.executeQuery();
        rs.next();
        int rowcount=rs.getInt(1);
        return rowcount;
    }
    public static boolean isPaperSet(String subject) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Select examid from exam where language=?");
        ps.setString(1, subject);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
     public static ArrayList<String> getExamIdBySubject(String userid,String subject) throws SQLException
     {
         ArrayList<String>examlist=new ArrayList<>();
         Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Select examid from exam where language=? minus select examid from performance where userid=?");
        ps.setString(1, subject);
        ps.setString(2, userid);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            examlist.add(rs.getString(1));
            
        }
        return examlist;
        
     }
}
