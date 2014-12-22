package wfx.utils.commons;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 字符串分析
 * Created by fanxin.wfx on 14-11-15.
 */
public class StringAnalyse {

    /**
     * 从控制台读取数据
     *
     * @return
     */
    public static String readStringFromConsole() {
        try {
            char[] bytes = new char[1000000];
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            int i = bf.read(bytes);
            return String.valueOf(bytes, 0, i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从控制台读取数据
     *
     * @return
     */
    public static String readCommandFromConsole() {
        try {
            char[] bytes = new char[512];
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            int i = bf.read(bytes);
            return String.valueOf(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统计word的数量
     */
    public static void statisticWordsNum() {
        List<String> wordList = getListString(readStringFromConsole());
        Collections.sort(wordList);
        Map<String, Integer> map = new HashMap<>();
        for (String word : wordList) {
            Integer count = map.get(word);
            if (count == null) {
                map.put(word, 1);
            } else {
                map.put(word, count + 1);
            }
        }
        System.out.println(map);
    }

    /**
     * 计算idb数据库中对数据库的分表。
     * 招商取模为256
     *
     * @param id
     * @return
     */
    public static int getIdbGroup(long id, int mod) {
        String idStr = Long.toString(id);
        int tailId;
        if (idStr.length() < 4) {
            tailId = Integer.parseInt(idStr);
        } else {
            tailId = Integer.parseInt(idStr.substring(idStr.length() - 4));
        }
        System.out.println("tailId:" +  tailId);
        return tailId % mod;
    }

    private static List<String> getListString(String s) {
        String[] strings = s.split("\n");
        return Arrays.asList(strings);
    }

    public static void menu() {
        System.out.println("********************************************");
        System.out.println("请选择需要使用的功能：");
        System.out.println("1.统计单词数量");
        System.out.println("********************************************");
    }

    public static void main(String[] args) {
        System.out.println(StringAnalyse.getIdbGroup(113280016505l, 256));
    }
}
