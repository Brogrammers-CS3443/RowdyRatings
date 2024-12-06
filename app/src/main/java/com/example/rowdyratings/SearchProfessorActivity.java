package com.example.rowdyratings;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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

    private void performProfessorSearch(String professorName) {
        searchResultsContainer.removeAllViews(); // Clear previous search results

        boolean isMatchFound = false;
        String trimmedQuery = professorName.trim().toLowerCase(); // Trim and lowercase search query

        System.out.println("Searching for: " + trimmedQuery);
        System.out.println("Available Professors: " + professorsMap.keySet());

        for (String key : professorsMap.keySet()) {
            if (key.toLowerCase().contains(trimmedQuery)) { // Case-insensitive partial matching
                Professor professor = professorsMap.get(key);
                displayProfessors(professor);
                isMatchFound = true;
            }
        }

        if (!isMatchFound) {
            // Display a Toast message when no professor is found
            Toast.makeText(this, "No results found for \"" + professorName + "\"", Toast.LENGTH_SHORT).show();
        }
    }


    private void displayProfessors(Professor professor){
        //create a button for the found professor
        Button professorButton = new Button(this);
        String profSubDescription = professor.getProfName() + "         Overall Rating: " + professor.getOverallRating()+"\n" +
                                    professor.getProfReviews().size() + " ratings";
        professorButton.setText(profSubDescription);

        professorButton.setOnClickListener(view -> {
            Intent intent = new Intent(SearchProfessorActivity.this, ViewProfessorActivity.class);
            intent.putExtra("professorName", professor.getProfName());
            startActivity(intent);
            //launchViewProfActivity();
        });

        //added button
        searchResultsContainer.addView(professorButton);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                800,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;
        professorButton.setLayoutParams(layoutParams);
        professorButton.setBackgroundResource(R.drawable.orange_rounded_corners);
        professorButton.setCompoundDrawablePadding(100);
    }
    /*private void launchViewProfActivity(){
        Intent intent = new Intent(this, ViewProfessorActivity.class);
        startActivity(intent);
    }*/
}