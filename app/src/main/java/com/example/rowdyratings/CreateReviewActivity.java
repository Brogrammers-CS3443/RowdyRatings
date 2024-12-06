package com.example.rowdyratings;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rowdyratings.model.Professor;
import com.example.rowdyratings.model.Review;
import com.google.android.material.chip.Chip;

import java.util.Map;

public class CreateReviewActivity extends AppCompatActivity {
    private static final String TAG = "CreateReviewActivity";
    private Map<String, Professor> professorsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_review);



        Log.i(TAG, "Start of the create review activity");

        //create EditText boxes
        EditText editClassNumber = findViewById(R.id.classNumBox);
        EditText editProfessorName = findViewById(R.id.profNameBox);
        EditText editGradeText = findViewById(R.id.courseGradeBox);
        EditText editReviewText = findViewById(R.id.reviewWriteupBox);

        //initialize the rating bars
        RatingBar difficultyRatingBar = findViewById(R.id.difficultyRatingInput);
        RatingBar overallCourseRatingBar = findViewById(R.id.courseRatingInput);

        //initialize the checkboxes
        CheckBox classMandatoryCheckBox = findViewById(R.id.checkboxClassMandatory);
        CheckBox wouldTakeAgainCheckBox = findViewById(R.id.checkBoxTakeClassAgain);

        professorsMap = Professor.loadProfessors(this);

        //pretend on click

        //Create Submit review button
        Button submitReviewButton = findViewById(R.id.button);
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classNumber = editClassNumber.getText().toString();
                String professorName = editProfessorName.getText().toString();
                String grade = editGradeText.getText().toString();
                String reviewText= editReviewText.getText().toString();

                double difficultyRating = (double) difficultyRatingBar.getRating();
                double overallCourseRating = (double) overallCourseRatingBar.getRating();

                boolean classMandatory = classMandatoryCheckBox.isChecked();
                boolean wouldTakeAgain = wouldTakeAgainCheckBox.isChecked();

                for(String key: professorsMap.keySet()){

                    if(key.equals(professorName)){

                        Review newReview = new Review(classNumber,professorsMap.get(this) ,difficultyRating, overallCourseRating, grade, classMandatory, wouldTakeAgain, reviewText);
                        professorsMap.get(this).writeReview(newReview);//writes the review created
                        professorsMap.get(this).addReview(newReview);//adds the review
                    }
                }

                Log.i(TAG, "classNumber: " + classNumber + " professorName: " + professorName + " grade recieved: " + grade
                        + " reviewText: " + reviewText + " difficultyRating: " + difficultyRating + " overallCourseRating: " + overallCourseRating
                        + " classMandatory: " + classMandatory + " wouldTakeAagain: " + wouldTakeAgain);


                //call function to loop through and find the professor object by the "String professorName"


                //    Review newReview = new Review(classNumber,Professor testProf, difficultyRating, overallCourseRating, grade, classMandatory, wouldTakeAgain, reviewText);

                //call the write review from the professor that is being passed

                //at the end return to the professor activity page
                launchViewProfessorActivity();
            }
        });

    }
    private void launchViewProfessorActivity(){
        Intent intent = new Intent(this, ViewProfessorActivity.class);
        startActivity(intent);
    }
}