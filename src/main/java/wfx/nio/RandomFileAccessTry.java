package wfx.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by fanxin.wfx on 14-10-22.
 */
public class RandomFileAccessTry {
    public void accessFile(String fileName) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            randomAccessFile.skipBytes(10);
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(32);
            int bytesRead = fileChannel.read(byteBuffer);
            while (bytesRead != -1) {
//                System.out.println("Read" + bytesRead);
                //此处很重要
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    char chr = (char)byteBuffer.get();
                    if('\n' == chr){
                        System.out.println("\n读完一行了");

                        break;
                    }
                    System.out.print(chr);
                }
                byteBuffer.compact();
                bytesRead = fileChannel.read(byteBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RandomFileAccessTry randomFileAccessTry = new RandomFileAccessTry();
        randomFileAccessTry.accessFile("src/main/resources/files/antx.properties");
    }
}
