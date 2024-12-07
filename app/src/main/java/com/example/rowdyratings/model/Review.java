package com.example.rowdyratings.model;

/**
 * The Review class will represent a review of the professor object
 * @author Matthew Perez, Jeremy Sellers, Zane Lakhani, Emilio Hernandez
 */
public class Review {
    private String courseNum;
    private Professor professor;
    private double difficultyRating;
    private double courseRating;
    private String courseGrade;
    private boolean mandatoryClass;
    private boolean takeClassAgain;
    private String reviewWriteup;

    /**
     *
     * @param courseNum, the course number
     * @param professor, the professor of that course
     * @param difficultyRating, the difficulty rating of the course
     * @param courseRating, the course rating
     * @param courseGrade, the course grade
     * @param mandatoryClass, if class was mandatory or not
     * @param takeClassAgain, if the student would take the class again
     * @param reviewWriteup, the actual review of the course
     */
    public Review(String courseNum, Professor professor, double difficultyRating, double courseRating, String courseGrade, boolean mandatoryClass, boolean takeClassAgain, String reviewWriteup) {
        this.courseNum = courseNum;
        this.professor = professor;
        this.difficultyRating = difficultyRating;
        this.courseRating = courseRating;
        this.courseGrade = courseGrade;
        this.mandatoryClass = mandatoryClass;
        this.takeClassAgain = takeClassAgain;
        this.reviewWriteup = reviewWriteup;
    }

    /**
     *
     * @param courseNum, the course number
     * @param professor, the professor of that course
     * @param difficultyRating, the difficulty rating of the course
     * @param courseGrade, the course grade
     * @param mandatoryClass, if class was mandatory or not
     * @param takeClassAgain, if the student would take the class again
     * @param reviewWriteup, the actual review of the course
     */
    public Review(String courseNum, Professor professor, double difficultyRating, String courseGrade, boolean mandatoryClass, boolean takeClassAgain, String reviewWriteup) {
        this.courseNum = courseNum;
        this.professor = professor;
        this.difficultyRating = difficultyRating;
        this.courseGrade = courseGrade;
        this.mandatoryClass = mandatoryClass;
        this.takeClassAgain = takeClassAgain;
        this.reviewWriteup = reviewWriteup;
    }


    /**
     * gets the course number
     * @return String, the course number
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * this method sets the course number
     * @param courseNum, the course number
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    /**
     * gets the professor to this review
     * @return Professor, the professor tied to this review
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Sets the professor to this review
     * @param professor, the professor to this review
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * gets the difficulty rating of the review
     * @return double, the difficulty rating of the review
     */
    public double getDifficultyRating() {
        return difficultyRating;
    }

    /**
     * sets the difficulty rating
     * @param difficultyRating, the difficulty rating
     */
    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    /**
     * gets the course rating
     * @return double, the course rating
     */
    public double getCourseRating() {
        return courseRating;
    }

    /**
     * sets the course rating
     * @param courseRating, the course rating
     */
    public void setCourseRating(int courseRating) {
        this.courseRating = courseRating;
    }

    /**
     * gets the course grade
     * @return String, the course grade
     */
    public String getCourseGrade() {
        return courseGrade;
    }

    /**
     * sets the course grade
     * @param courseGrade, the course grade
     */
    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    /**
     * gets the review
     * @return String, the review
     */
    public String getReviewWriteup() {
        return reviewWriteup;
    }

    /**
     * sets the review
     * @param reviewWriteup, the review
     */
    public void setReviewWriteup(String reviewWriteup) {
        this.reviewWriteup = reviewWriteup;
    }

    /**
     * checks if class is mandatory
     * @return boolean, if class is mandatory
     */
    public boolean isMandatoryClass() {
        return mandatoryClass;
    }

    /**
     * sets if class is mandatory
     * @param mandatoryClass, if class is mandatory
     */
    public void setMandatoryClass(boolean mandatoryClass) {
        this.mandatoryClass = mandatoryClass;
    }

    /**
     * returns if the student would take the class again
     * @return boolean, if the student would take the class again
     */
    public boolean isTakeClassAgain() {
        return takeClassAgain;
    }

    /**
     * sets if the student would take the class again
     * @param takeClassAgain, if the student would take the class again
     */
    public void setTakeClassAgain(boolean takeClassAgain) {
        this.takeClassAgain = takeClassAgain;
    }
}
