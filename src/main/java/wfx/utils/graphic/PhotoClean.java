package wfx.utils.graphic;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import wfx.utils.security.MD5Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 14-6-25.
 * Use to clean the duplicated graphic
 */
public class PhotoClean {
    private static final String INDEX_FILE = "_index.img";
    static Font FONT = new Font("΢���ź�", Font.BOLD, 18);

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public static boolean cleanDuplicatedPhotos(String standPath, String cleanPath) {
        //loop the standPath and generate the index file
//        generateIndexFile(standPath);
        Map<String, String> indexMap = loadIndexFile(standPath);
        cleanDuplicatedPhotos(cleanPath, indexMap);
        return false;
    }

    public static Map<String, String> loadIndexFile(String standPath) {
        File indexFile = new File(standPath + File.separator + INDEX_FILE);
        if (!indexFile.exists()) {
            generateIndexFile(standPath);
            indexFile = new File(standPath + File.separator + INDEX_FILE);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(indexFile))) {
            Map<String, String> map = new HashMap<>();
            String line = br.readLine();
            {
                System.out.println("line=" + line);
                System.out.println("line split size is :" + line.split("=").length);
            }
            while (line != null) {
                if (!StringUtils.isEmpty(line)) {
                    String[] strs = line.split("=");
                    map.put(strs[0], strs[1]);
                }
                line = br.readLine();
            }
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void generateIndexFile(String standPath) {
        File file = new File(standPath + File.separator + INDEX_FILE);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String[] suffixes = {"jpg", "bmp"};
        IOFileFilter fileFilter = new SuffixFileFilter(suffixes, IOCase.INSENSITIVE);
        Collection<File> fileList = FileUtils.listFiles(new File(standPath), fileFilter, TrueFileFilter.INSTANCE);
        if (fileList != null && fileList.size() > 0) {
            List<String> resultList = new ArrayList<>(fileList.size());
            for (File tfile : fileList) {
                String md5 = MD5Util.md5(tfile);
                String result = md5 + "=" + tfile.getAbsolutePath();
                resultList.add(result);
            }
            try {
                FileUtils.writeLines(file, resultList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void cleanDuplicatedPhotos(String cleanPath, Map<String, String> indexMap) {
        String[] suffixes = {"jpg", "bmp"};
        IOFileFilter fileFilter = new SuffixFileFilter(suffixes, IOCase.INSENSITIVE);
        Collection<File> fileList = FileUtils.listFiles(new File(cleanPath), fileFilter, TrueFileFilter.INSTANCE);
        for (File file : fileList) {
            String md5 = MD5Util.md5(file);
            if (indexMap.containsKey(md5)) {
                move2TmpFolder(file);
            }
        }
    }

    public static int translateRBG2Gry(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return (r * 19595 + g * 38469 + b * 7472) >> 18;
    }

    /**
     * ����ͼƬλͼ��ֵ������ͼƬ���ƱȽϣ�һ��ͼƬ������ͼ��ԭͼ��ȣ���ֵһ��ȽϽӽ�
     *
     * @param file
     * @return
     */
    public static int[] resizePic2GrayArray(File file, int height, int width)  throws IOException{
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageUtil.resizeJpg(in, out, width, height, 1f);
        ByteArrayInputStream inByte = new ByteArrayInputStream(out.toByteArray());
        BufferedImage image = ImageIO.read(inByte);
        int[] grays = new int[width * height];
        int k = 0;
        System.out.println("x:" + image.getWidth());
        System.out.println("y:" + image.getHeight());
        for(int i = 0; i < image.getHeight(); i++){
            for (int j = 0; j < image.getWidth(); j++){
               Color color = new Color(image.getRGB(j, i));
                int gray = translateRBG2Gry(color);
                grays[k++] = gray;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < grays.length; i++){
            sb.append(grays[i]).append(",");
        }
        System.out.println(sb.toString());
        return grays;
    }

    public static String calPictureString(File file){
        try {
            int[] grays = resizePic2GrayArray(file, WIDTH, HEIGHT);
            int avgGrays = calculateAvgGray(grays);
            System.out.println("avgGrays:" + avgGrays);
            return transGrays2String(grays, avgGrays);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int calPictureHamingDistance(File fileA, File fileB){
        String strA = calPictureString(fileA);
        String strB = calPictureString(fileB);
        System.out.println("strA:" + strA);
        System.out.println("strB:" + strB);
        int distance = calHammingDistance(strA, strB);
        return distance;
    }

    public static boolean move2TmpFolder(File file) {
        String filePath = file.getAbsolutePath();
        Path oldPath = Paths.get(filePath);
        Path newPath = Paths.get("D:\\tmpPhoto\\" + filePath.split(":")[1]);
        try {
            System.out.println("move file \"" + file.getAbsolutePath() + "\" to \"" + newPath.toFile().getAbsolutePath() + "\"");
            Path parentPath = newPath.getParent();
            if (!Files.exists(parentPath)) {
                Files.createDirectories(parentPath);
            }
            Files.move(oldPath, newPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ����ҶȾ����ƽ��ֵ
     *
     * @param grays
     * @return
     */
    public static int calculateAvgGray(int[] grays) {
        if (grays == null || grays.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < grays.length; i++) {
            sum = grays[i] + sum;
        }
        System.out.println("sum:" + sum);
        System.out.println("length:" + grays.length);
        return sum / grays.length;
    }

    public static String transGrays2String(int[] grays, int avg) {
        if (grays == null || grays.length != 64) {
            return null;
        }
        StringBuilder sb = new StringBuilder(64);
        for(int i = 0; i < grays.length; i++){
            if(grays[i] >= avg){
                sb.append("1");
            }else{
                sb.append("0");
            }

        }
        return sb.toString();
    }

    public static int calHammingDistance(String stra, String strb){
        if(stra == null || strb == null || stra.length() != strb.length()){
            return -1;
        }
        int distance = 0;
        char[] chara = stra.toCharArray();
        char[] charb = strb.toCharArray();
        for(int i = 0; i < stra.length(); i++){
            if(chara[i] != charb[i]){
                distance++;
            }
        }
        return distance;
    }

    public static void main(String[] args) {
//        PhotoClean.cleanDuplicatedPhotos("d:\\����\\photo\\", "d:\\photo\\");
//        int avg = 2;
//        int[] graysA = {0,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,0};
//        String a = PhotoClean.transGrays2String(graysA, avg);
//        int[] graysB = {4,2,4,4,2,2,4,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,4,2,2,1,0};
//        String b = PhotoClean.transGrays2String(graysB, avg);
//        System.out.println(PhotoClean.calHammingDistance(a,b));
////        System.out.println(Long.toBinaryString((long)Math.pow(2, 63)));
////        System.out.println(Integer.toBinaryString(1|4));
////        System.out.println(Long.toBinaryString(Long.MIN_VALUE));
//        File fileA = new File("d:\\tmpPhoto\\photo\\qiuqiu\\http_imgload.jpg");
//            File fileB = new File("d:\\tmpPhoto\\photo\\qiuqiu\\http_imgload_small.jpg");
//        int distance = calPictureHamingDistance(fileA, fileB);
//

        cleanDuplicatedPhotos("d:\\����\\photo\\","d:\\photo\\");
    }
}
