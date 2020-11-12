package com.example.demo.models.businesslogic;

import com.example.demo.models.businesslogic.backend.QuestionBack;
import com.example.demo.models.businesslogic.frontend.QuestionFront;
import com.example.demo.models.entities.AnswerObject;
import com.example.demo.models.entities.EnumData.QuestionType;
import com.example.demo.models.entities.Law;
import com.example.demo.models.entities.QuestionLaw;
import com.example.demo.models.entities.Response;
import com.example.demo.utils.HyperText;

import java.util.ArrayList;
import java.util.List;

public abstract class Question implements QuestionFront, QuestionBack {
    
    protected com.example.demo.models.entities.Question questionData;

    protected List<Response> studentResponses = new ArrayList<>();
    
    protected boolean isFinalResponse = false;
    
    public Question(com.example.demo.models.entities.Question questionData) {
        this.questionData = questionData;
    }

    @Override
    public List<Law> getPositiveLaws() {

        // TODO
//        List<Law> allLaws = questionData.getLaws();
        List<Law> positiveLaws = new ArrayList<>();

//        for (QuestionLaw l : allLaws) {
//
//            if (l.getLawName().isPositiveLaw()) {
//                positiveLaws.add(l);
//            }
//        }
        
        return positiveLaws;
    }

    @Override
    public List<Law> getNegativeLaws() {

        // TODO
//        List<Law> allLaws = questionData.getLaws();
        List<Law> negativeLaws = new ArrayList<>();

//        for (Law l : allLaws) {
//
//            if (!l.isPositiveLaw()) {
//                negativeLaws.add(l);
//            }
//        }

        return negativeLaws;
    }

    @Override
    public int answerObjectsCount() {
        
        return questionData.getAnswerObjects().size();
    }

    @Override
    public void addAnswerObject(AnswerObject newObject) {
        
        questionData.getAnswerObjects().add(newObject);
    }

    @Override
    public void setAnswerObjects(List<AnswerObject> objects) {

        questionData.setAnswerObjects(objects);
    }

    @Override
    public void addResponse(Response r) {
        
        studentResponses.add(r);
    }

    @Override
    public void addFullResponse(List<Response> responses) {

        studentResponses = responses;
        isFinalResponse = true;
    }

    public void FinalResponse(boolean isFinalResponse) {

        this.isFinalResponse = isFinalResponse;
        
    }
    
    public boolean isFinalResponse() {
        
        return isFinalResponse;
    }
    
    @Override
    public List<AnswerObject> getAnswerObjects() {
        
        return questionData.getAnswerObjects();
    }

    @Override
    public HyperText getQuestionText() {
        
        return new HyperText(questionData.getQuestionText());
    }

    @Override
    public AnswerObject getAnswerObject(int index) {
        
        return questionData.getAnswerObjects().get(index);
    }

    @Override
    public QuestionType getQuestionType() {
        
        return questionData.getQuestionType();
    }
    
    public com.example.demo.models.entities.Question getQuestionData() {
        
        return questionData;
    }
    
}
