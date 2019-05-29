package com.mds;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public  class  pdfOpener {
    public pdfOpener()
   {
        try {
            File myFile = new File("temporary.pdf");
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
        }
    }
}
