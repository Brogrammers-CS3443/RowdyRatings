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

//****************************************************
//Emilio
//im dynamically loading the views for the professor name plus their other attributes that represent
//their overall rating, percent that would take again, and level of difficulty
//and the individual reviews
//although i need to wait to some things until we get the professors to load
//****************************************************

/**
 *
 * @author Matthew Perez, Jeremy Sellers, Zane Lakhani, Emilio Hernandez
 */
public class ViewProfessorActivity extends AppCompatActivity {

    private String selectedProfName;
    private Map<String, Professor> professorsMap;
    private TextView professorName;
    private final String TAG = "viewProfessorActivity";

    /**
     * Start of the on create
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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
        double overallRating = professor.calcOverallRating(professor);
        double overallDifficulty = professor.calcAverageLevelOfDifficulty(professor);
        double takeAgain = professor.calcWouldTakeAgain(professor);
        loadProfessorReviews(professor, overallRating, overallDifficulty, takeAgain);



        selectedProfName = getIntent().getStringExtra("professorName");
        displayProfessorName(selectedProfName);



        reviewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                launchProfessorReviewsActivity();
            }
        });


    }

    /**
     * displays professor names
     * @param selectedProfName, the professor name
     */
    private void displayProfessorName(String selectedProfName){
        //this is to display the professors name so we need to pass an professor object and get the name
        //pass an intention with a string of the professors name when they select a professor
        professorName.setText(selectedProfName);
    }

    /**
     * loads the professor reviews
     * @param professor, the professor
     * @return double, the overall rating of the professor
     */
    //kinda realized im gonna need several vertical layout within the horizontal layout if we want
    //the design as the prototype, i think
    private void loadProfessorReviews(Professor professor, double overallRating, double ovrDifficulty, double takeAgain){
        ArrayList<String> reviewArrayList = professor.loadDataInAVD();
        //Scanner scan = new Scanner(String.valueOf(reviewArrayList));
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
                showProfessorReviews(courseNum, rating, difficulty, grade, classMandatory, wouldTakeAgain, reviewWriteUp, overallRating, ovrDifficulty, takeAgain);
            }
        }
    }


    /**
     * Shows the professor reviews
     * @param courseNum, the course number
     * @param rating, the rating
     * @param difficulty, the difficulty
     * @param grade, the grade
     * @param classMandatory, if class is mandatory
     * @param wouldTakeAgain, would take again
     * @param reviewWriteUp, the review
     */
    private void showProfessorReviews(String courseNum, String rating, String difficulty, String grade, String classMandatory, String wouldTakeAgain, String reviewWriteUp, double overallRating, double ovrDifficulty, double takeAgain){
        TextView profRating = findViewById(R.id.text1);
        TextView takeAgainText = findViewById(R.id.text2);
        TextView levelOfDiff = findViewById(R.id.text3);

        profRating.setText(String.valueOf(overallRating));
        takeAgainText.setText(String.valueOf(ovrDifficulty));
        levelOfDiff.setText(String.valueOf(takeAgain));

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

    /**
     * launches the create review activity
     */
    private void launchProfessorReviewsActivity(){
        Intent intent = new Intent(this, CreateReviewActivity.class);
        startActivity(intent);
    }
}