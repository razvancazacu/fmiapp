package com.webscrapping;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UMSConnectionDummy implements UMS {
    private String username;
    private String password;
    private ArrayList<Grades> userGrades = new ArrayList<Grades>();
    private Map<String, String> cookiesLogin;
    private Integer StudentYear;

    UMSConnectionDummy() {
        /**
         * @Razvan
         * To be replaced with graphical interface 
         * and respective messages
         */
        Scanner read = new Scanner(System.in);
        System.out.println("Username - CNP");
        this.username = read.next();
        System.out.println("Password - Format 'dd-mm-yyyy'");
        this.password = read.next();
    }

    UMSConnectionDummy(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String makeConnection() {
        try {
            String URL = "https://ums.unibuc.ro/ums/do/secure/inregistrare_user";
            String loginURL = "https://ums.unibuc.ro/ums/do/secure/j_security_check";
            Connection.Response response = Jsoup.connect(URL).timeout(8000)
                    .method(Connection.Method.GET)
                    .execute();
            this.cookiesLogin = response.cookies();
            response = Jsoup.connect(loginURL)
                    .data("j_username", this.username)
                    .data("j_password", this.password)
                    .followRedirects(true)
                    .timeout(11000)
                    .cookies(this.cookiesLogin)
                    .method(Connection.Method.POST).execute();
            if (response.statusCode() != 200)
                return "Error";
            else {
                try {
                    Document selectedCourses = Jsoup.connect("https://ums.unibuc.ro/ums/do/secure/vizualizare_rezultate_evaluari")
                            .timeout(8000)
                            .cookies(this.cookiesLogin)
                            .get();
                    Elements yearSelector = (Elements) selectedCourses.select("td").select("select").select("option");
                    for (int i = 0; i < 2; i++)
                        System.out.println(yearSelector.get(i));
                    Document yearlyCourses = Jsoup.connect("https://ums.unibuc.ro/ums/do/secure/vizualizare_rezultate_evaluari")
                            .timeout(8000)
                            .cookies(this.cookiesLogin)
                            .data("value", "199412")
                            .post();
                    System.out.println(yearlyCourses);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Succes";
    }
        public void itterateGrades (Document yearGrades){
            Elements courses = yearGrades.select("td.celula_tabel_left");
            for (Element x : courses) {
                Grades course = new Grades();
                String text = x.text();
                course.addCourse(text);
                this.userGrades.add(course);
            }
            int i = 0;
            ArrayList<String> gradeList = new ArrayList<String>();
            Elements grades = yearGrades.select("td.celula_tabel_center_top");
            grades.remove(0);
            for (Element x : grades) {
                String text = x.text();
                if (!text.contains("Sem"))
                    gradeList.add(text);
                else {

                    Grades gradeDetails = this.userGrades.get(i);
                    gradeDetails.addGrade(gradeList.get(0), gradeList.get(1), gradeList.get(2), gradeList.get(3), gradeList.get(4));
                    gradeList.clear();
                    this.userGrades.set(i, gradeDetails);
                    i++;
                }

            }
            Grades gradeDetails = this.userGrades.get(i);
            gradeDetails.addGrade(gradeList.get(0), gradeList.get(1), gradeList.get(2), gradeList.get(3), gradeList.get(4));
            gradeList.clear();
            this.userGrades.set(i, gradeDetails);
        }
        public ArrayList<Grades> getGrades () {
            return userGrades;
        }
        @Override
        public void display () {
            for (Grades grades : this.userGrades) {
                grades.display();
            }
        }
    }

