package com.example.rowdyratings;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.Map;
import com.example.rowdyratings.model.Professor;



public class SearchProfessorActivity extends AppCompatActivity {
    private Map<String, Professor> professorsMap;
    private LinearLayout searchResultsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_professor);

        SearchView searchView = findViewById(R.id.professor_search_view);
        searchResultsContainer = findViewById(R.id.profContainer);

        professorsMap = Professor.loadProfessors(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performProfessorSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void performProfessorSearch(String professorName){
        searchResultsContainer.removeAllViews(); // Clear previous search results

        //checks if the professor exists in the map
        if (professorsMap.containsKey(professorName)) {
            Professor professor = professorsMap.get(professorName);
            displayProfessors(professor);
        } else {
            //displays a Toast message when no professor is found
            Toast.makeText(this, "No results found for \"" + professorName + "\"", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayProfessors(Professor professor){
        //create a button for the found professor
        Button professorButton = new Button(this);
        professorButton.setText(professor.getProfName());

        professorButton.setOnClickListener(view -> {
            Intent intent = new Intent(SearchProfessorActivity.this, ProfessorReviewsActivity.class);
            intent.putExtra("professorName", professor.getProfName());
            startActivity(intent);
        });

        //added button
        searchResultsContainer.addView(professorButton);
    }

}