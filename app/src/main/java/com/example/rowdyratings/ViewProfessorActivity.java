package com.example.rowdyratings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rowdyratings.model.Professor;
import com.example.rowdyratings.model.Review;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

//****************************************************
//Emilio
//im dynamically loading the views for the professor name plus their other attributes that represent
//their overall rating, percent that would take again, and level of difficulty
//and the individual reviews
//although i need to wait to some things until we get the professors to load
//****************************************************
/**
 * The Review class will represent a review of the professor object
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
        displayProfessorName(selectedProfName);



        reviewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                launchProfessorReviewsActivity();
            }
        });


    }

    /**
     * displays the professor name
     * @param selectedProfName, the professor name
     */
    private void displayProfessorName(String selectedProfName){
        //this is to display the professors name so we need to pass an professor object and get the name
        //pass an intention with a string of the professors name when they select a professor
        professorName.setText(selectedProfName);
    }


    //kinda realized im gonna need several vertical layout within the horizontal layout if we want
    //the design as the prototype, i think

    /**
     * shows the reviews for the professor
     * @param professor, the professor object that we are searching with
     */
    private void showProfessorReviews(Professor professor){
        Log.d(TAG, "Professor Reviews: " + professor.getProfReviews());
        LinearLayout verticalLayout = findViewById(R.id.professorReviewHolder);

        for(Review review : professor.getProfReviews()){
            TextView professorCourseTextView = new TextView(this);
            professorCourseTextView.setText(review.getCourseNum());


            verticalLayout.addView(professorCourseTextView);

        }
    }

    /**
     * launches the activity to navigate to the create review screen
     */
    private void launchProfessorReviewsActivity(){
        Intent intent = new Intent(this, CreateReviewActivity.class);
        startActivity(intent);
    }
}