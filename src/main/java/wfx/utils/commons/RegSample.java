package wfx.utils.commons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fanxin.wfx on 14-11-15.
 */
public class RegSample {

    public static List<String> parseQuoteStringFromFile(String filePath) {
        try {
            String str = FileUtils.readFileToString(new File(filePath));
            return parseQuoteString(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> parseQuoteString(String str) {
        return parseRegString(str, "\"\\w+\"");
    }

    public static List<String> parseRegString(String str, String regex) {
        List<String> resultList = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            String word = m.group();
            resultList.add(word);
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<String> resultList = RegSample.parseQuoteStringFromFile("d:\\排期正确配置.txt");
        Collections.sort(resultList);
        Collections.shuffle(resultList);
        for (String str : resultList) {
            System.out.println(str);
        }
    }
}
