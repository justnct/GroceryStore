package com.example.pj_grocerystore.model;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatNumber {
    public static String formatNumber(int number){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(number);
        return  str1;
    }
}
