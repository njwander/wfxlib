package wfx.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 密码生成工具，用于辅助密码词典的生成，根据指定的词根，生成对应的词典库。
 * Created by fanxin.wfx on 14-12-28.
 */
public class DictionaryGenerator {
    public static List<String> generateDictionaryWithDeep(String[] words, String[] splits, int deep) {
        long count = (long) Math.pow((double) words.length, (double) deep);
        for (long i = 0; i < count; i++) {
            String[] genWord = new String[deep];

        }
        return null;
    }

    public static List<String> generateDictionary(String[] strs) {
        if (strs == null || strs.length < 2) {
            return new ArrayList<>();
        }
        List<String> resultList = new ArrayList<String>();
        for (int i = 0; i < strs.length; i++) {
            resultList.add(strs[i]);
            for (int j = 0; j < strs.length; j++) {
                resultList.add(strs[i] + strs[j]);
                resultList.add(strs[i] + "." + strs[j]);
                resultList.add(strs[i] + "_" + strs[j]);
//                resultList.add(strs[i] + "-" + strs[j]);
                for (int k = 0; k < strs.length; k++) {
                    resultList.add(strs[i] + strs[j] + strs[k]);
                    resultList.add(strs[i] + "." + strs[j] + "." + strs[k]);
                    resultList.add(strs[i] + strs[j] + "." + strs[k]);
                    resultList.add(strs[i] + "." + strs[j] + strs[k]);
                    resultList.add(strs[i] + "_" + strs[j] + "_" + strs[k]);
                    resultList.add(strs[i] + "_" + strs[j] + strs[k]);
                    resultList.add(strs[i] + strs[j] + "_" + strs[k]);
//                    resultList.add(strs[i] + "-" + strs[j] + "-" + strs[k]);
                    for (int l = 0; l < strs.length; l++) {
                        resultList.add(strs[i] + strs[j] + strs[k] + strs[l]);
                        resultList.add(strs[i] + "." + strs[j] + "." + strs[k] + "." + strs[l]);
                        resultList.add(strs[i] + "." + strs[j] + strs[k] + "." + strs[l]);
                        resultList.add(strs[i] + "_" + strs[j] + "_" + strs[k] + "_" + strs[l]);
                        resultList.add(strs[i] + "_" + strs[j] + strs[k] + "_" + strs[l]);
//                        resultList.add(strs[i] + "-" + strs[j] + "-" + strs[k] + "-" + strs[l]);
                    }
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        String[] words = {"yqq", "12096", "yqq", "1982", "wfy", "0527", "yeguo", "929", "snooby", "yeguo", "thirsty", "louise", "322550", "324929", "0929", "yqj", "0324", "801", "0801", "800225"};
        String[] splits = {"", ".", "_"};
        List<String> resultList = DictionaryGenerator.generateDictionary(words);
//        List<String> resultList = DictionaryGenerator.generateDictionaryWithDeep(words, splits, 4);
        System.out.println(resultList.size());
        try {
            FileOutputStream fos = new FileOutputStream(new File("D:\\result.list"));
            IOUtils.writeLines(resultList, "\n", fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
