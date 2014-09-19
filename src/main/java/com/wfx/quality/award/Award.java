package com.wfx.quality.award;

import java.util.*;

/**
 * Created by admin on 14-9-2.
 */
public class Award {
    Map<String, Integer> awardMap = new LinkedHashMap<String, Integer>();
    private Integer awardCount = 0;
    public Award(String awardDefineStr){
        if(awardDefineStr.startsWith("@")){
            awardDefineStr = awardDefineStr.substring(1);
        }
        String[] awardStrs = awardDefineStr.split("\\|");
        if(awardStrs != null && awardStrs.length > 0){
            for(String awardStr : awardStrs){
                System.out.println("awardStr:" + awardStr);
                String[] strs = awardStr.split(":");
                if(Integer.valueOf(strs[1]) > 0){
                    awardMap.put(strs[0], Integer.valueOf(strs[1]));
                    awardCount += Integer.valueOf(strs[1]);
                }
            }
        }
    }

    public Integer getAwardCount() {
        return awardCount;
    }

    public Integer getCurrentAwardCount(Integer chooseTime){
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>();
        list.addAll(awardMap.entrySet());
        for(int i = list.size() - 1; i >= 0; i--){
            Integer count = awardMap.get(list.get(i).getKey());
            if(chooseTime <= count){
                return count - chooseTime;
            }else{
                chooseTime = chooseTime - count;
            }
        }
        return 0;
    }

    public String getCurrentAwardName(Integer chooseTime){
        return getCurrentAwardEntry(chooseTime).getKey();
    }

    public Map.Entry<String, Integer> getCurrentAwardEntry(Integer chooseTime){
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>();
        list.addAll(awardMap.entrySet());
        for(int i = list.size() - 1; i >= 0; i--){
            Integer count = awardMap.get(list.get(i).getKey());
            if(chooseTime <= count){
                return list.get(i);
            }else{
                chooseTime = chooseTime - count;
            }
        }
        throw new RuntimeException("奖项不够！");
    }

    /**
     *
     * @return 输出格式:将产生:一(1名),二(2名),三(3名)等奖
     */
    public String getAwardDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("将产生:");
        for(Map.Entry<String, Integer> entry : awardMap.entrySet()){
            sb.append(entry.getKey()).append("(").append(entry.getValue()).append("名) ");
        }
        sb.append("等奖");
        return sb.toString();
    }
}
