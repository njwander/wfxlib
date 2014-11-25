package wfx.utils.practice;

import java.io.*;

/**
 * Created by fanxin.wfx on 14-11-20.
 */
public class DataInputStreamPractice {
    public void saveData2FileWithDataIS(String fileName) {
        try{
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
            dos.writeUTF("abceeeedddd");
            System.exit(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataInputStreamPractice dataInputStreamPractice = new DataInputStreamPractice();
        dataInputStreamPractice.saveData2FileWithDataIS("D:\\test.dat");
    }
}
