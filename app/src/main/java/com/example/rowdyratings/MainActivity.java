package com.example.rowdyratings;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rowdyratings.model.Professor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button searchBtn = findViewById(R.id.searchBtn);

        //Create professor and load it to for testing purposes
        Professor newProfessor = new Professor("Hend Alkittawi", null, 2.0, this);
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