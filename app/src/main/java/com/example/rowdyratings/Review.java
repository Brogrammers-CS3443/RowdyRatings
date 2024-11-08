package com.example.rowdyratings;

public class Review {
    private String courseNum;
    private String courseName;
    private Professor professor;
    private int difficultyRating;
    private int courseRating;
    private String courseGrade;
    private String reviewWriteup;
    private boolean mandatoryClass;
    private boolean takeClassAgain;

    public Review(String courseNum, String courseName, Professor professor, int difficultyRating, int courseRating, String courseGrade, String reviewWriteup, boolean mandatoryClass, boolean takeClassAgain) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.professor = professor;
        this.difficultyRating = difficultyRating;
        this.courseRating = courseRating;
        this.courseGrade = courseGrade;
        this.reviewWriteup = reviewWriteup;
        this.mandatoryClass = mandatoryClass;
        this.takeClassAgain = takeClassAgain;
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

    public int getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public int getCourseRating() {
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
