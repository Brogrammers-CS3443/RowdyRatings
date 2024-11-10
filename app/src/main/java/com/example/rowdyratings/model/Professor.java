package com.example.rowdyratings.model;

import android.content.Context;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStream;
import android.content.res.AssetManager;

public class Professor {
    private String profName;
    private ArrayList<Review> profReviews;
    private double overallRating;

    public Professor(String profName, ArrayList<Review> profReviews, double overallRating) {
        this.profName = profName;
        this.profReviews = profReviews;
        this.overallRating = overallRating;
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

    public void loadProfessors(Context context){
        AssetManager manager = context.getAssets();
        try{
            InputStream file = manager.open("SampleRRData.csv");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String profName = tokens[0];
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

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
