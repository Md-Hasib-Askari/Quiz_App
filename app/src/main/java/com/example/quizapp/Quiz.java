package com.example.quizapp;

public class Quiz {
    private int mQuestion;
    private boolean mAnswer;

    public Quiz(int question, boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public boolean getAnswer() {
        return mAnswer;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
