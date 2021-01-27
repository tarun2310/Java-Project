/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz.Pojo;

import java.util.ArrayList;

/**
 *
 * @author abc
 */
public class QuestionStore {
    
    ArrayList <QuestionPojo> questionlist;
    
    public QuestionStore()
    {
        questionlist=new ArrayList<>();
        
    }
    public void addQuestion(QuestionPojo q)
    {
        questionlist.add(q);
    }
    public QuestionPojo getQuestion(int pos)
    {
        return questionlist.get(pos);
    }
    public void removeQuestion(int pos)
    {
        questionlist.remove(pos);
        
    }
    public void setQuestionsAt(int pos, QuestionPojo q)
    {
        questionlist.add(pos,q);
    }
    public ArrayList<QuestionPojo> getAllQuestions()
    {
        return questionlist;
    }
    public int getCount()
    {
        return questionlist.size();
    }
    public QuestionPojo getQuestionByQno(int qno)
    {
        for(QuestionPojo quest: questionlist)
        {
            if(quest.getQno()==qno)
                return quest;
        }
        return null;
    }
}
