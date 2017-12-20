package com.xawl.Util;

import org.junit.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by doter on 2017/7/16.
 */
public class Regex {
    public static boolean isPhone(String pram) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^1[34578]\\d{9}$");
            Matcher matcher = regex.matcher(pram);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    public static boolean isEmail(String pram) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(pram);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean isImage(String pram) {
        boolean flag = false;
        try {
            String regStr = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
            Pattern regex = Pattern.compile(regStr);
            Matcher matcher = regex.matcher(pram);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    public static boolean isWord(String pram) {
        boolean flag = false;
        try {
            String regStr = ".+(.doc|.docx|.txt|.pdf|.pptx|.ppt)$";
            Pattern regex = Pattern.compile(regStr);
            Matcher matcher = regex.matcher(pram);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    public static boolean isVideo(String pram) {
        boolean flag = false;
        try {
            String regStr = ".+(.mp4|.rmvb|.flv|.mpeg|.avi)$";
            Pattern regex = Pattern.compile(regStr);
            Matcher matcher = regex.matcher(pram);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
