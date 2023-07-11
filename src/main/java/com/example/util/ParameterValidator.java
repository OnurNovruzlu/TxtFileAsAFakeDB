package com.example.util;

import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class ParameterValidator {
    private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_+.-]+)@([a-zA-Z]+)\\.([a-zA-Z]+)$";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9_.+*?!@#$%^&()=]{4,}$";
    private static final Pattern patternEm = Pattern.compile(EMAIL_PATTERN);

    public static boolean validate(String email){
        Matcher matcher = patternEm.matcher(email);
        return !matcher.matches();
    }
    public static boolean validatePsw(String password){
        Pattern patternPsw = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = patternPsw.matcher(password);
        return !matcher.matches();
    }
}
