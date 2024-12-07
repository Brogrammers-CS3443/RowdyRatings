package com.example.rowdyratings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rowdyratings.model.Professor;
import com.example.rowdyratings.model.Review;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

//****************************************************
//Emilio
//im dynamically loading the views for the professor name plus their other attributes that represent
//their overall rating, percent that would take again, and level of difficulty
//and the individual reviews
//although i need to wait to some things until we get the professors to load
//****************************************************
public class ViewProfessorActivity extends AppCompatActivity {

    private String selectedProfName;
    private Map<String, Professor> professorsMap;
    private TextView professorName;
    private final String TAG = "viewProfessorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_professor);

        professorName = findViewById(R.id.professorNameTextView);
        FloatingActionButton reviewButton = findViewById(R.id.floatingActionButton);

        professorsMap = Professor.loadProfessors(this);
        selectedProfName = getIntent().getStringExtra("professorName");
        Professor professor = professorsMap.get(selectedProfName);
        if(professor == null){
            Log.e(TAG, "Error! Professor was not found !!!" + professorName);
            return;
        }
        loadProfessorReviews(professor);
        double testOverall = loadProfessorReviews(professor);
        Log.d(TAG, "Overall Rating: " + testOverall);

        selectedProfName = getIntent().getStringExtra("professorName");
        displayProfessorName(selectedProfName);



        reviewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                launchProfessorReviewsActivity();
            }
        });


    }

    private void displayProfessorName(String selectedProfName){
        //this is to display the professors name so we need to pass an professor object and get the name
        //pass an intention with a string of the professors name when they select a professor
        professorName.setText(selectedProfName);
    }

    //kinda realized im gonna need several vertical layout within the horizontal layout if we want
    //the design as the prototype, i think
    private double loadProfessorReviews(Professor professor){
        ArrayList<String> reviewArrayList = professor.loadDataInAVD();
        //Scanner scan = new Scanner(String.valueOf(reviewArrayList));
        double overallRating = 0;
        int counter = 0;
        for(String avdData : reviewArrayList){
            String[] tokens = avdData.split(",");
            String profName = tokens[0];
            if(profName.equals(professor.getProfName())){
                String courseNum = tokens[1];
                String rating = tokens[2];
                String difficulty = tokens[3];
                String grade = tokens[4];
                String classMandatory = tokens[5];
                String wouldTakeAgain = tokens[6];
                String reviewWriteUp = tokens[7];
                showProfessorReviews(courseNum, rating, difficulty, grade, classMandatory, wouldTakeAgain, reviewWriteUp);
                counter++;
                double tempRating = Double.parseDouble(rating);
                overallRating += tempRating;
            }
        }
        return overallRating / counter;
    }

    private void showProfessorReviews(String courseNum, String rating, String difficulty, String grade, String classMandatory, String wouldTakeAgain, String reviewWriteUp){
        LinearLayout verticalLayout = findViewById(R.id.professorReviewHolder);

        TextView courseInfo = new TextView(this);
        String topInfo = courseNum + "\t Grade: " + grade;
        courseInfo.setBackgroundResource(R.drawable.rounded_corners);
        courseInfo.setText(topInfo);
        courseInfo.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        verticalLayout.addView(courseInfo);

        Space spaceBetween = new Space(this);
        spaceBetween.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                2
        ));
        verticalLayout.addView(spaceBetween);

        TextView reviewInfo = new TextView(this);
        reviewInfo.setBackgroundResource(R.drawable.rounded_corners);
        String reviewInfoViewText = "Rating: " + rating + " Difficulty: " + difficulty +
                                    " Class Mandatory?: " + classMandatory +
                                    "\n" +  reviewWriteUp;
        reviewInfo.setText(reviewInfoViewText);
        courseInfo.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        verticalLayout.addView(reviewInfo);

    }

    private void launchProfessorReviewsActivity(){
        Intent intent = new Intent(this, CreateReviewActivity.class);
        startActivity(intent);
    }
}