package com.example.rowdyratings.model;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.icu.util.Output;
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
        profReviews.add(profReview);
    }


    public static Map<String, Professor> loadProfessors(Context context) {
        Map<String, Professor> professorsMap = new HashMap<>();
        String profFileName = "professorNames.csv"; //ensure file exists in AVD

        //make sure the files are initialized


        //access internal storage file
        File internalProfFile = new File(context.getFilesDir(), profFileName);

        //check if file exists
        if (!internalProfFile.exists()) {
            System.err.println("File does not exist in internal storage: " + internalProfFile.getAbsolutePath());
            return professorsMap; //return empty map if file not found
        }

        try (InputStream file = new FileInputStream(internalProfFile);
             Scanner scanner = new Scanner(file)) {

            //skip header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            //read and process each line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String profName = line;

                //Professor professor = professorsMap.getOrDefault(profName.trim(), new Professor(profName.trim(), new ArrayList<>(), 0, new Activity()));
                Professor professor = professorsMap.getOrDefault(profName.trim(), new Professor(profName.trim(), new ArrayList<>(), 0, context));
                professorsMap.putIfAbsent(profName.trim(), professor);

                professorsMap.putIfAbsent(profName, professor);
                System.out.println(professor);
            }
        } catch (IOException e) {
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
        String grade = newReview.getCourseGrade();
        boolean mandatory = newReview.isMandatoryClass();
        boolean wouldTakeAgain = newReview.isTakeClassAgain();
        String review = newReview.getReviewWriteup();

        //create logic for writing these values to avd
        try{
            Log.i(TAG,"Attempting to write to file");

            OutputStream out = context.openFileOutput("professorReviews.csv", Context.MODE_APPEND);
            out.write(profName.getBytes(StandardCharsets.UTF_8));
            out.write(",".getBytes(StandardCharsets.UTF_8));
            out.write(courseNum.getBytes(StandardCharsets.UTF_8));
            out.write(",".getBytes(StandardCharsets.UTF_8));
            out.write(String.valueOf(rating).getBytes(StandardCharsets.UTF_8));
            out.write(",".getBytes(StandardCharsets.UTF_8));
            out.write(String.valueOf(difficulty).getBytes(StandardCharsets.UTF_8));
            out.write(",".getBytes(StandardCharsets.UTF_8));
            out.write(grade.getBytes(StandardCharsets.UTF_8));
            out.write(",".getBytes(StandardCharsets.UTF_8));
            out.write(String.valueOf(mandatory).getBytes(StandardCharsets.UTF_8));
            out.write(",".getBytes(StandardCharsets.UTF_8));
            out.write(String.valueOf(wouldTakeAgain).getBytes(StandardCharsets.UTF_8));
            out.write(",".getBytes(StandardCharsets.UTF_8));
            out.write(review.getBytes(StandardCharsets.UTF_8));
            out.write("\n".getBytes(StandardCharsets.UTF_8));
            out.close();

        }catch (IOException e){
            Log.i(TAG, "Failed to write to file. " );
        }
        Log.i(TAG, "Adding review to AVD");
        addReview(newReview);
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

    //loop through avd
    //split lines in tokens array
    //add it to the reviews in professor
    //the parameter that

    //create a method that will loop through the avd memory
    //
    public ArrayList<String> loadDataInAVD(){
        ArrayList<String> avdData = new ArrayList<>();
        try{
            Log.d(TAG,"Attempting to open file from avd");
            InputStream professorReviewsFile = context.openFileInput("professorReviews.csv");
            Log.d(TAG,"Successfully opened file from AVD!");
            Scanner scan = new Scanner(professorReviewsFile);//loop through the file in avd

            scan.nextLine();//skip the header
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                avdData.add(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return avdData;
    }
}
