package wfx.utils.security;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by admin on 14-6-25.
 */
public class MD5Util {

    public static String md5(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return md5(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        } else {
            return md5(s.getBytes());
        }
    }

    public static String md5(byte[] btInput) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        File file = new File("d:\\mysql-connector-java-5.1.6.jar");
        System.out.println(MD5Util.md5(file));
    }
}
