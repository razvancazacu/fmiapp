package com.webscrapping;

import java.io.IOException;


public class MainUMS {

    public static void main(String[] args) throws IOException {

            UMSConnectionDummy x = new UMSConnectionDummy();
            x.makeConnection();
            x.display();

    }
}
