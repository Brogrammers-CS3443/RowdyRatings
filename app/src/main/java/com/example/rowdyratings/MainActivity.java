package com.example.rowdyratings;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rowdyratings.model.Professor;
import com.example.rowdyratings.model.Review;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button searchBtn = findViewById(R.id.searchBtn);


        Professor newProfessor = new Professor("Hend Alkitawwi", null, 2.0, this);
        Log.i("MainActivity", "After adding the new professor: " + newProfessor.getProfName());
        newProfessor.initializeFilesAVD();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    launchSearchProfessorActivity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void launchSearchProfessorActivity(){
        Intent intent = new Intent(this, SearchProfessorActivity.class);
        startActivity(intent);
    }

}