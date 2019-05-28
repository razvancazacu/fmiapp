package com.webscrapping;

public class Grades implements UMS{
    private String course;
    private String activity;
    private String exam;
    private String finalGrade;
    private String credits;
    private String score;
    private Boolean restanta;

    void addCourse(String name){
        this.course = name;
    }
    void addGrade(String a, String b, String c, String d, String e){
        this.activity = a;
        this.exam = b;
        this.finalGrade = c;
        this.credits = d;
        this.score = e;
        float result;
        if(!c.equals("-")) {
            result = Float.parseFloat(c);
            if (result < 5)
                this.restanta = true;
            else {
                this.restanta = false;
            }
        }
        else{
            this.restanta = true;
        }

    }
    public String getCourse()
    {
        return this.course;
    }
    public String getFinalGrade(){
        return this.finalGrade;
    }
    @Override
    public void setUsername(String user) {
        System.out.println("wrong camp");
    }
    public String getCredits(){
        return this.credits;
    }
    public int totalScore(){
        if (Integer.parseInt(this.finalGrade) >=5)
            return Integer.parseInt(this.finalGrade) * Integer.parseInt(this.credits);
        return 0;
    }
    @Override
    public void setPassword(String pass) {
        System.out.println("wrong camp");

    }


    @Override
    public void display(){
        System.out.println(this.course +" "+this.activity+" "+this.exam+" "+this.finalGrade +" "+this.credits+" "+this.score);
        if(this.restanta)
            System.out.print("RESTANTA");
        else{
            System.out.print("TRECUT");
        }
        System.out.println();
    }
}
