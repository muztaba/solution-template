package com.tigerit.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by seal on 3/25/2016.
 */
public class RegexUtil {

    public static List<String> regexChecker(String regexString, String string) {
        List<String> stringList = new ArrayList<>();

        Matcher matcher = Pattern.compile(regexString).matcher(string);

        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                stringList.add(matcher.group().trim());
            }
        }

        return stringList;
    }

    public static Pair<String, String> splitTableColumnName(String str) {
        String[] tableColName = str.split("\\.");
        Pair<String, String> pair = Pair.makePair(tableColName[0], tableColName[1]);
        return pair;
    }

}
