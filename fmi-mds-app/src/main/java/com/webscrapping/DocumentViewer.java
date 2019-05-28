package com.webscrapping;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.*;
import java.util.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;


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
        downloadFile(finalFiles.get(0));
        return finalFiles;
    }
        public static ArrayList<String> search(String searchStr, ArrayList<String> aList) throws IOException {
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
        public static void downloadFile(String filelink) throws IOException {
            System.out.println("opening connection");
            URL url = new URL("http://fmi.unibuc.ro/ro/"+filelink);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(new File("temporary.pdf"));

            System.out.println("reading from resource and writing to file...");
            int length = -1;
            byte[] buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            System.out.println("File downloaded");
        }
};

