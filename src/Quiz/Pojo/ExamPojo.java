/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz.Pojo;

/**
 *
 * @author abc
 */
public class ExamPojo {
    
    private String Examid;
    private String language;
    private int totalQuestions;

    public ExamPojo(String Examid, String language, int totalQuestions) {
        this.Examid = Examid;
        this.language = language;
        this.totalQuestions = totalQuestions;
    }

    public String getExamid() {
        return Examid;
    }

    public void setExamid(String Examid) {
        this.Examid = Examid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
    
    
    
}
