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

import java.util.ArrayList;
import java.util.Map;
import com.example.rowdyratings.model.Professor;
import com.example.rowdyratings.model.Review;

/**
 *
 * @author Matthew Perez, Jeremy Sellers, Zane Lakhani, Emilio Hernandez
 */
public class SearchProfessorActivity extends AppCompatActivity {
    private Map<String, Professor> professorsMap;
    private LinearLayout searchResultsContainer;
    private Professor professor;

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
        setContentView(R.layout.activity_search_professor);

        ArrayList<Review> profReviews = new ArrayList<>();
        professor = new Professor("Hend Alkittawi", profReviews,2.0,this);
        professor.initializeFilesAVD();

        SearchView searchView = findViewById(R.id.professor_search_view);
        searchResultsContainer = findViewById(R.id.profContainer);




        professorsMap = Professor.loadProfessors(this);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             *
             * @param query the query text that is to be submitted
             *
             * @return boolean, the on query
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                performProfessorSearch(query);
                return true;
            }

            /**
             *
             * @param newText the new content of the query text field.
             *
             * @return boolean, the query
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    /**
     * Searches for the professor
     * @param professorName, the name of the professor
     */
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

    /**
     * Displays the professors
     * @param professor, the professor object
     */
    private void displayProfessors(Professor professor){
        //create a button for the found professor
        Button professorButton = new Button(this);
        double overallRating = professor.calcOverallRating(professor);
        int numOfReviews = professor.numberOfReviews(professor);
        String profSubDescription = professor.getProfName() + "         Overall Rating: " + overallRating+"\n" +
                                    numOfReviews + " reviews";
        professorButton.setText(profSubDescription);

        professorButton.setOnClickListener(view -> {
            Intent intent = new Intent(SearchProfessorActivity.this, ViewProfessorActivity.class);
            intent.putExtra("professorName", professor.getProfName());
            startActivity(intent);
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

}