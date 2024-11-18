package com.example.rowdyratings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewProfessorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_professor);


        FloatingActionButton reviewButton = findViewById(R.id.floatingActionButton);

        reviewButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                launchProfessorReviewsActivity();
            }
        });


    }

    private void launchProfessorReviewsActivity(){
        Intent intent = new Intent(this, ProfessorReviewsActivity.class);
        startActivity(intent);
    }
}