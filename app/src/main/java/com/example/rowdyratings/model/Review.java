package com.example.rowdyratings.model;

public class Review {
    private String courseNum;
    private String courseName;
    private Professor professor;
    private double difficultyRating;
    private double courseRating;
    private String courseGrade;
    private boolean mandatoryClass;
    private boolean takeClassAgain;
    private String reviewWriteup;

    public Review(String courseNum, String courseName, Professor professor, double difficultyRating, double courseRating, String courseGrade, boolean mandatoryClass, boolean takeClassAgain, String reviewWriteup) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.professor = professor;
        this.difficultyRating = difficultyRating;
        this.courseRating = courseRating;
        this.courseGrade = courseGrade;
        this.mandatoryClass = mandatoryClass;
        this.takeClassAgain = takeClassAgain;
        this.reviewWriteup = reviewWriteup;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public double getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(int courseRating) {
        this.courseRating = courseRating;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getReviewWriteup() {
        return reviewWriteup;
    }

    public void setReviewWriteup(String reviewWriteup) {
        this.reviewWriteup = reviewWriteup;
    }

    public boolean isMandatoryClass() {
        return mandatoryClass;
    }

    public void setMandatoryClass(boolean mandatoryClass) {
        this.mandatoryClass = mandatoryClass;
    }

    public boolean isTakeClassAgain() {
        return takeClassAgain;
    }

    public void setTakeClassAgain(boolean takeClassAgain) {
        this.takeClassAgain = takeClassAgain;
    }
}
