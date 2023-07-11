package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_+.-]+)@([a-zA-Z]+)\\.([a-zA-Z]+)$");
        Matcher matcher = pattern.matcher("onur.novruzlu@mail.ru");

        System.out.println(matcher.matches());
    }

}
