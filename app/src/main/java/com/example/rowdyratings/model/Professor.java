package com.example.rowdyratings.model;

import android.app.Activity;
import android.content.Context;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.util.Log;

public class Professor {
    private String profName;
    private ArrayList<Review> profReviews;
    private double overallRating;

    private final Context context;

    private static final String TAG = "Professor";

    public Professor(String profName, ArrayList<Review> profReviews, double overallRating, Context context) {
        this.profName = profName;
        this.profReviews = profReviews;
        this.overallRating = overallRating;
        this.context = context;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public ArrayList<Review> getProfReviews() {
        return profReviews;
    }

    public void setProfReviews(ArrayList<Review> profReviews) {
        this.profReviews = profReviews;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public void addReview(Review profReview){
        if(profReview != null)
            profReviews.add(profReview);
    }

    public static Map<String, Professor> loadProfessors(Context context){
        Map<String, Professor> professorsMap = new HashMap<>();
        AssetManager manager = context.getAssets();
        try{
            InputStream file = manager.open("SampleRRData.csv");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String profName = tokens[0].trim();
                double overallRating = Double.parseDouble(tokens[1].trim());
                String courseNum = tokens[2].trim();
                String courseName = tokens[3].trim();
                int difficultyRating = Integer.parseInt(tokens[4].trim());
                int courseRating = Integer.parseInt(tokens[5].trim());
                String courseGrade = tokens[6].trim();
                boolean mandatoryClass = Boolean.parseBoolean(tokens[7].trim());
                boolean takeClassAgain = Boolean.parseBoolean(tokens[8].trim());
                String reviewWriteup = tokens[9].trim();

                Professor professor = professorsMap.getOrDefault(profName, new Professor(profName, new ArrayList<>(), overallRating, new Activity()));

                Review profReview = new Review(courseNum, courseName, professor, difficultyRating, courseRating, courseGrade, mandatoryClass, takeClassAgain, reviewWriteup);
                assert professor != null;
                professor.addReview(profReview);

                professorsMap.putIfAbsent(profName, professor);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return professorsMap;
    }

    //create a function that will attempt to read from file in avd memory
    //if file does not exist we will create that file and call a function to write to the files in
    //avd memory
    public void initializeFilesAVD(){
        try{
            Log.i(TAG,"Atempting to read from file...");
            InputStream professorNamesFile = context.openFileInput("professorNames.csv");
            Log.i(TAG,"SUCCESS");
            InputStream professorReviewsFile = context.openFileInput("professorReviews.csv");

            professorNamesFile.close();
            professorReviewsFile.close();

            //call method to read through csv and write to it
            loadProfessorNamesFromCSV();
            loadProfessorReviewsFromCSV();
        }catch(IOException e1){
            Log.i(TAG,"AVD file is not found. Creating one...");

            try{
                OutputStream profNameOutputFile = context.openFileOutput("professorNames.csv", Context.MODE_PRIVATE);
                profNameOutputFile.close();

                OutputStream professorReviewsOutputFile = context.openFileOutput("professorReviews.csv", Context.MODE_PRIVATE);
                professorReviewsOutputFile.close();

                //call methods to load from the csv in assets
            }catch(IOException e2){
                Log.e(TAG,"Failed ot initialize avd");
            }
        }
    }

    //create a method that will open the professorName.csv file in assets and write to it in avd memory
    public void loadProfessorNamesFromCSV(){
        //get the asseets manager from our assets directory
        AssetManager manager = context.getAssets();
        //Create log  message to see if the load method is called correctly
        Log.d(TAG,"Start of the loading from the csv");
        Scanner scan = null;
        OutputStream out = null;

        //Create a string for the professorName.csv file
        String professorNamesCSV = "professorNames.csv";

        try{
            //create an input stream and assign it to the professNames.csv
            InputStream profNameFile = manager.open(professorNamesCSV);

            scan = new Scanner(profNameFile);



            out = context.openFileOutput("professorNames.csv", Context.MODE_PRIVATE);
            Log.i(TAG, "SUCCESS");

            while(scan.hasNextLine()){
                String professorName = scan.nextLine().trim();
                //if professorName line is not empty then we will write to our file in avd
                if(!professorName.isEmpty()){
                    out.write(professorName.getBytes(StandardCharsets.UTF_8));
                    out.write("\n".getBytes(StandardCharsets.UTF_8));
                    Log.i(TAG,"After adding the professor: " + professorName);
                }

            }
            Log.i(TAG,"Added all of the prof names to file in avd successfully!");

        out.close();
        scan.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //create a method that will open the professorReview.csv file in assets and write to it in avd memory
    public void writeReview(Review newReview){
        String profName = newReview.getProfessor().getProfName();
        String courseNum = newReview.getCourseNum();
        double rating = newReview.getCourseRating();
        double difficulty = newReview.getDifficultyRating();
        boolean mandatory = newReview.isMandatoryClass();
        boolean wouldTakeAgain = newReview.isTakeClassAgain();
        String review = newReview.getReviewWriteup();

        //create logic for writing these values to avd

    }
    public void loadProfessorReviewsFromCSV(){
        //get the asseets manager from our assets directory
        AssetManager manager = context.getAssets();
        //Create log  message to see if the load method is called correctly
        Log.d(TAG,"Start of the loading from the csv");
        Scanner scanner = null;
        OutputStream out2 = null;

        //Create a string for the professorName.csv file
        String professorReviewCSV = "professorReviews.csv";

        try{
            //create an input stream and assign it to the professNames.csv
            InputStream profNameFile = manager.open(professorReviewCSV);

            scanner = new Scanner(profNameFile);



            out2 = context.openFileOutput("professorReviews.csv", Context.MODE_PRIVATE);
            Log.i(TAG, "SUCCESS");

            //write the header format to the file in avd
            String professorReviewHeaderFormat = scanner.nextLine();
            out2.write(professorReviewHeaderFormat.getBytes(StandardCharsets.UTF_8));
            out2.write("\n".getBytes(StandardCharsets.UTF_8));
            Log.i(TAG,"After adding the professor review: " + professorReviewHeaderFormat);

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();

                out2.write(line.getBytes(StandardCharsets.UTF_8));
                out2.write("\n".getBytes(StandardCharsets.UTF_8));
            }
            Log.i(TAG,"Added all of the prof names to file in avd successfully!");

            out2.close();
            scanner.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //create a function that adds a review to avd


    //returns average of overall ratings
    public double calcOverallRating(){
        double overallRating = 0;

        for(Review review : profReviews){
            overallRating += review.getCourseRating();
        }
        return overallRating / profReviews.size();
    }

    //returns average of difficulty ratings
    public double calcDifficultyRating(){
        double difficultyRating = 0;

        for(Review review : profReviews){
            difficultyRating += review.getDifficultyRating();
        }
        return difficultyRating / profReviews.size();
    }
}
