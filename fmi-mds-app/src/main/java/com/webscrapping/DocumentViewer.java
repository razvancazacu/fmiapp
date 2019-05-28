package com.webscrapping;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class DocumentViewer {
    private String pageURL;
    private ArrayList<String> documentList = new ArrayList<String>();

    public DocumentViewer(){
        this.pageURL = "http://fmi.unibuc.ro/ro/documente.txt?fbclid=IwAR3xi2qJL1v7vF_-9nfvswGtCzvub_nuTSwwjNtEIAbBK3bS9eVvFjhVFaY";
    }
    public ArrayList<String> getDocuments() throws IOException {
        Document ArrayFiles = Jsoup.connect(this.pageURL)
                                   .timeout(3000)
                                   .get();
        List<String> fileNames = Arrays.asList(ArrayFiles.text().split("[|]"));
        ArrayList<String> finalFiles = new ArrayList<String>();
        for (String temp : fileNames)
        {
            if(temp.length() >9 || temp.contains("liber")!=false)
                finalFiles.add(temp);
        }
        return finalFiles;
    }
        public static ArrayList<String> search(String searchStr, ArrayList<String> aList)
        {
            ArrayList <String> foundList = new ArrayList<String>();
            boolean found = false;
            Iterator<String> iter = aList.iterator();
            String curItem = "";
            int pos = 0;

            while (iter.hasNext() == true) {
                pos = pos + 1;
                curItem = (String) iter.next();
                if (curItem.contains(searchStr)) {
                foundList.add(curItem);
                }

            }

            if (foundList.size() != 0) {
                return foundList;
            }
            return foundList;

        }
};

