package com.thor.vertify;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * Created by 1b-dell on 2016/9/22.
 */
public class ImageIdentifyTess4j {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        File imageFile = new File("D:/validcode.jpg");
        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath("C:/tessdata");
        try {
            String result = tessreact.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
