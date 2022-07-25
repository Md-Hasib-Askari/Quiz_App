package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtQuestion, mTxtScore;
    private ProgressBar mProgressBar;
    private int mQuestionIndex = 0, mScore = 0;
    private boolean mCurrentTxtScore = false;

    private final Quiz[] questionList = {
            new Quiz(R.string.question_1, true),
            new Quiz(R.string.question_2, true),
            new Quiz(R.string.question_3, false),
            new Quiz(R.string.question_4, false),
            new Quiz(R.string.question_5, true),
            new Quiz(R.string.question_6, true),
            new Quiz(R.string.question_7, false),
            new Quiz(R.string.question_8, true),
            new Quiz(R.string.question_9, false),
            new Quiz(R.string.question_10, true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mQuestionIndex = savedInstanceState.getInt("questionIndex");
            Log.i("INDEX", String.valueOf(mQuestionIndex));
            mScore = savedInstanceState.getInt("score");
            mCurrentTxtScore = savedInstanceState.getBoolean("currentTxtScore");

        } else {
            mQuestionIndex = 0;
            mScore = 0;
        }

        mTxtQuestion = findViewById(R.id.txtQuestion);
        mTxtScore = findViewById(R.id.txtScore);
        mProgressBar = findViewById(R.id.progressBar);
        Button btnTrue = findViewById(R.id.btnTrue);
        Button btnFalse = findViewById(R.id.btnFalse);

        mProgressBar.setMax(questionList.length);

        mTxtQuestion.setText(questionList[mQuestionIndex].getQuestion());

        if (mCurrentTxtScore) {
            mTxtScore.setText("Correct!");
        } else {
            mTxtScore.setText("Wrong!");
        }

        btnTrue.setOnClickListener(view -> updateQuestion(true));
        btnFalse.setOnClickListener(view -> updateQuestion(false));

    }

    private void checkAnswer(boolean userAnswer) {
        if (userAnswer == questionList[mQuestionIndex].getAnswer()) {
            mCurrentTxtScore = true;
            mTxtScore.setText("Correct!");
            mScore++;
        } else {
            mTxtScore.setText("Wrong!");
            mCurrentTxtScore = false;
        }
    }

    private void updateQuestion(boolean userAnswer) {
        checkAnswer(userAnswer);
        mQuestionIndex++;
        mProgressBar.incrementProgressBy(1);

        if (mQuestionIndex < questionList.length) {
            mTxtQuestion.setText(questionList[mQuestionIndex].getQuestion());
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quiz Over!");
            builder.setMessage("You scored " + mScore + " out of " + mProgressBar.getMax());
            builder.setPositiveButton("Close Application", (dialogInterface, i) -> finish());
            builder.setCancelable(false);
            builder.create().show();

        }

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("questionIndex", mQuestionIndex);
        outState.putInt("score", mScore);
        outState.putBoolean("currentTxtScore", mCurrentTxtScore);
    }
}