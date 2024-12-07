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

import java.util.ArrayList;
import java.util.Map;

public class CreateReviewActivity extends AppCompatActivity {
    private static final String TAG = "CreateReviewActivity";
    private Map<String, Professor> professorsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_review);



        Log.d(TAG, "Start of the create review activity");

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

                Professor professor = professorsMap.get(professorName);
                //Log.i(TAG,"Start of the on click method...");

                if(professor == null){
                    Log.e(TAG, "Error! Professor was not found !!!" + professorName);
                    return;
                }

                //Create and add a new review based on the input data
                Review newReview = new Review(classNumber, professor, difficultyRating, overallCourseRating, grade,
                        classMandatory, wouldTakeAgain, reviewText);

                Log.i(TAG, "Review was added: " + newReview.getReviewWriteup());

                Log.i(TAG,"Professor retrieved successfully! Professor retrieved: " + professor.getProfName());

                //professor.writeReview(newReview);//write review is the only thing causing the crash
                //for testing purposes, create a review object and pass it to the newProfessors write review method
                Review testReview = new Review("3443", professor,  2.0, 4.0, "A+", true, true, "Great class, would" +
                        " love to take again!!! This is a test review!");

                professor.addReview(testReview);
                professor.writeReview(testReview);

                Log.i(TAG, "Added review for: " + professor.getProfName());

                professor.addReview(newReview);

                //NOW ADD THE REAL REVIEW
                Log.i(TAG,"Now adding the actual review for : " + professor.getProfName());
                professor.writeReview(newReview);
                //professor.addReview(newReview);
                Log.i(TAG,"Added the review successfully!");

                //call the loadavd method
                ArrayList<String> testArrayList = professor.loadDataInAVD();
                for(String avdData: testArrayList){
                    Log.i(TAG,"AVD Line: " + avdData);
                }


            //    for(String key: professorsMap.keySet()){

            //        if(key.equals(professorName)){
            //            Professor professor = professorsMap.get(professorName);
            //            if(professor == null){
            //                Log.e(TAG,"Professor not found: " + professorName);
            //                return;
            //            }
            //            Review newReview = new Review(classNumber,professorsMap.get(this) ,difficultyRating, overallCourseRating, grade, classMandatory, wouldTakeAgain, reviewText);
            //            Log.i(TAG,"After creating the review: " + newReview.getReviewWriteup() + " " + newReview.getCourseGrade() + " " + newReview.getCourseNum() +
            //                    " " + newReview.getProfessor() + "etc...");

            //            System.out.println(newReview);
                        //professorsMap.get(professorName).writeReview(newReview);//writes the review created
                        //professorsMap.get(professorName).addReview(newReview);//adds the review

                        //Review newReview = new Review(classNumber,professor,difficultyRating, overallCourseRating, grade, classMandatory, wouldTakeAgain, reviewText);
                        //professor.writeReview(newReview);//writes the review created
                        //professor.addReview(newReview);//adds the review
            //        }
            //    }

                Log.i(TAG, "classNumber: " + classNumber + " professorName: " + professorName + " grade recieved: " + grade
                        + " reviewText: " + reviewText + " difficultyRating: " + difficultyRating + " overallCourseRating: " + overallCourseRating
                        + " classMandatory: " + classMandatory + " wouldTakeAagain: " + wouldTakeAgain);


                //call function to loop through and find the professor object by the "String professorName"


                //    Review newReview = new Review(classNumber,Professor testProf, difficultyRating, overallCourseRating, grade, classMandatory, wouldTakeAgain, reviewText);

                //call the write review from the professor that is being passed

                //at the end return to the professor activity page
                //launchViewProfessorActivity();
            }
        });

    }

    //create a method that will iterate through the map and return the professor object
    private Professor findProfessorByName(String profName){
        for(String key: professorsMap.keySet()){
            if(key.equalsIgnoreCase(profName)){
                return professorsMap.get(key);
            }
        }
        return null;
    }
    private void launchViewProfessorActivity(){
        Intent intent = new Intent(this, ViewProfessorActivity.class);
        startActivity(intent);
    }
}