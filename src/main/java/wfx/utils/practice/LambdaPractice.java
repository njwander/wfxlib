package wfx.utils.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by fanxin.wfx on 14-12-21.
 */
public class LambdaPractice {
    public interface LambdaIntf {
        public String getFirstLetter(String str);
    }

    public void testCollection() {
        List<String> list = new ArrayList<String>();
        list.add("asdf");
        list.add("fsef");
        list.add("aefs");
        Stream<String> stream = list.stream();
        stream.filter((str -> {
            return str.startsWith("a");
        }));
//        System.out.println(listnew);
    }

    public static void main(String[] args) {
        LambdaPractice lambdaPractice = new LambdaPractice();
        lambdaPractice.testCollection();
    }
}
