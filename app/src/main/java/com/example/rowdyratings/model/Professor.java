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

    public Professor(String profName, double overallRating) {
        this.profName = profName;
        this.profReviews = new ArrayList<>();
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

    public void addReview(Review profReview){
        if(profReview == null)
            return;
        if(profReview != null)
            profReviews.add(profReview);
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
                Professor professor = new Professor(tokens[0], Double.parseDouble(tokens[1]));

                Review profReview = new Review(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9], tokens[10]);
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
