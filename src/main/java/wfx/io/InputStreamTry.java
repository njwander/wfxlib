package wfx.io;

import java.io.*;

/**
 * 验证java.io.InputStream类的一些功能
 * Created by fanxin.wfx on 14-12-9.
 */
public class InputStreamTry {
    public void testCharset() {
        try {
            //中文编码使用GB18030或者GBK，不区分大小写
            //如果不指定编码，那么InputStreamReader默认以UTF-8进行编码
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream("d:\\chi.txt"));
            BufferedReader bf = new BufferedReader(inputStream);
            try {
                System.out.println(bf.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                int ch = inputStream.read();
                while (ch != -1) {
                    System.out.print((char) ch);
                    ch = inputStream.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InputStreamTry inputStreamTry = new InputStreamTry();
        inputStreamTry.testCharset();
    }
}
