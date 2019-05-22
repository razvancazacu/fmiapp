package webscrapping;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class umsConnectionDummy {
    private final String Username ;
    private String Password;
    private ArrayList<grades> user = new ArrayList<grades>();
    umsConnectionDummy(){
        Scanner read = new Scanner(System.in);
        System.out.println("Username - CNP");
        this.Username = read.next();
        System.out.println("Password - Format 'dd-mm-yyyy'");
        this.Password = read.next();
    }
    umsConnectionDummy(String Username,String Password){
        this.Username = Username;
        this.Password = Password;
    }
    void makeConnection(){
        try {
            String URL = "https://ums.unibuc.ro/ums/do/secure/inregistrare_user";
            String loginURL = "https://ums.unibuc.ro/ums/do/secure/j_security_check";
            Connection.Response response = Jsoup.connect(URL).timeout(3000)
                    .method(Connection.Method.GET)
                    .execute();
            Map<String, String> login = response.cookies();
            response = Jsoup.connect(loginURL)
                    .data("j_username", this.Username)
                    .data("j_password", this.Password)
                    .followRedirects(true)
                    .timeout(11000)
                    .cookies(login)
                    .method(Connection.Method.POST).execute();

            Document note = Jsoup.connect("https://ums.unibuc.ro/ums/do/secure/vizualizare_rezultate_evaluari")
                    .timeout(8000)
                    .cookies(login)
                    .get();
            Elements materii = note.select("td.celula_tabel_left");
            for (Element x : materii){
                grades a = new grades();
                String text = x.text();
                a.addCourse(text);
                this.user.add(a);
            }
            int i =0;
            int ok =0;
            ArrayList<String> a = new ArrayList<String>();
            Elements grades = note.select("td.celula_tabel_center_top");
            grades.remove(0);
            for (Element x : grades){
                String text = x.text();
                if (text.contains("Sem") == false)
                    a.add(text);
                else{

                        grades b = this.user.get(i);
                        b.addGrade(a.get(0), a.get(1), a.get(2), a.get(3), a.get(4));
                        a.clear();
                        this.user.set(i, b);
                        i++;
                    }

            }
            grades b = this.user.get(i);
            b.addGrade(a.get(0), a.get(1), a.get(2), a.get(3), a.get(4));
            a.clear();
            this.user.set(i, b);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void display(){
        for(grades a : this.user){
            a.display();
        }
    }
}
