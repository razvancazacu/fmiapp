package webscrapping;

public class grades {
    private String Course;
    private String activity;
    private String exam;
    private String Final;
    private String credits;
    private String score;
    private Boolean restanta;

    public void addCourse(String name){
        this.Course = name;
    }
    public void addGrade(String a, String b, String c , String d,String e){
        this.activity = a;
        this.exam = b;
        this.Final  = c;
        this.credits = d;
        this.score = e;
        float result;
        if(c.equals("-")==false) {
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
    public void display(){
        System.out.println(this.Course+" "+this.activity+" "+this.exam+" "+this.Final+" "+this.credits+" "+this.score);
        if(this.restanta == true)
            System.out.print("RESTANTA");
        else{
            System.out.print("TRECUT");
        }
        System.out.println();
    }
}
