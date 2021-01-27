/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizDAO;

import Quiz.DBUtil.DBConnection;
import Quiz.Pojo.QuestionPojo;
import Quiz.Pojo.QuestionStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author abc
 */
public class QuestionDAO {
    
    public static void addQuestions(QuestionStore qstore) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        String qry="insert into questions values(?,?,?,?,?,?,?,?,?)";
        ArrayList<QuestionPojo> questionlist=qstore.getAllQuestions();
        PreparedStatement ps=con.prepareStatement(qry);
        for(QuestionPojo obj: questionlist)
        {
           
             ps.setString(1, obj.getExamid());
             ps.setInt(2, obj.getQno());
             ps.setString(3, obj.getQuestion());
             ps.setString(4, obj.getAnswer1());
             ps.setString(5, obj.getAnswer2());
             ps.setString(6, obj.getAnswer3());
             ps.setString(7, obj.getAnswer4());
             ps.setString(8, obj.getCorrectAnswer());
             ps.setString(9, obj.getLanguage());
            ps.executeUpdate();
        }
        
    }
    public static ArrayList<QuestionPojo>getQuestionsByExamId(String examId) throws SQLException
    {
        ArrayList<QuestionPojo>questionlist=new ArrayList<>();
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from questions where examid=? order by qno");
        ps.setString(1, examId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            int qno=rs.getInt(2);
            String question=rs.getString(3);
            String option1=rs.getString(4);
            String option2=rs.getString(5);
            String option3=rs.getString(6);
            String option4=rs.getString(7);
            String correctAnswer=rs.getString(8);
            String language=rs.getString(9);
            QuestionPojo obj=new QuestionPojo(examId,qno,language,option1,option2,option3,option4,correctAnswer,question);
            questionlist.add(obj);
        }
        return questionlist;
        
    }
    public static void updateQuestions(QuestionStore qstore) throws SQLException
    {
        Connection con=DBConnection.getConnection();
        ArrayList<QuestionPojo> questionlist=qstore.getAllQuestions();
        PreparedStatement ps=con.prepareStatement("update questions set question=?,answer1=?,answer2=?,answer3=?,answer4=?,correct_answer=? where examid=? and qno=?");
        for(QuestionPojo obj: questionlist)
        {
            ps.setString(1,obj.getQuestion());
            ps.setString(2,obj.getAnswer1());
            ps.setString(3,obj.getAnswer2());
            ps.setString(4,obj.getAnswer3());
            ps.setString(5,obj.getAnswer4());
            ps.setString(6,obj.getCorrectAnswer());
            ps.setString(7,obj.getExamid());
            ps.setInt(8, obj.getQno());
              ps.executeUpdate();
        }
    }
    
}
