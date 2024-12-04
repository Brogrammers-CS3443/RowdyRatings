package com.example.rowdyratings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rowdyratings.model.Professor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

//****************************************************
//Emilio
//im dynamically loading the views for the professor name plus their other attributes that represent
//their overall rating, percent that would take again, and level of difficulty
//and the individual reviews
//although i need to wait to some things until we get the professors to load
//****************************************************
public class ViewProfessorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_professor);

        TextView courseTextView = findViewById(R.id.professorNameTextView);
        FloatingActionButton reviewButton = findViewById(R.id.floatingActionButton);

        reviewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                launchProfessorReviewsActivity();
            }
        });


    }

    //need to handle loading of professors from csv before we can do this
    private void displayProfessorName(){
        //this is to display the professors name so we need to pass an professor object and get the name
        //or pass an intention with a string of the professors name when they select a professor
    }

    //kinda realized im gonna need several vertical layout within the horizontal layout if we want
    //the design as the prototype, i think
    private void showProfessorReviews(){
        LinearLayout verticalLayout = findViewById(R.id.professorReviewHolder);
        //*******this is just temp until we get the professors to load********
        //for(int i=0; i<reviews.size(); i++){
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView professorCourseTextView = new TextView(this);
            professorCourseTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            professorCourseTextView.setBackgroundResource(R.drawable.rounded_corners);
            professorCourseTextView.setPadding(4,4,4,4);
            professorCourseTextView.setTextColor(Color.BLACK);
            //professorCourseTextView.setText(reviews.getCourseName);

            TextView professorReviewTextView = new TextView(this);
            professorReviewTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            professorReviewTextView.setBackgroundResource(R.drawable.rounded_corners);
            professorReviewTextView.setPadding(4,4,4,4);
            professorReviewTextView.setTextColor(Color.BLACK);
            //professorReviewTextView.setText

            horizontalLayout.addView(professorReviewTextView);

        //}

        verticalLayout.addView(horizontalLayout);
    }

    private void launchProfessorReviewsActivity(){
        Intent intent = new Intent(this, CreateReviewActivity.class);
        startActivity(intent);
    }
}