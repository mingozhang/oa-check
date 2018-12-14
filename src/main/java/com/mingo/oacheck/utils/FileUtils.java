package com.mingo.oacheck.utils;

import java.io.*;

/**
 * @author mingo
 * @create 2018-12-03 14:56
 * @desc
 **/
public class FileUtils {

    public static String readFile(String path) throws IOException {
        String result = "";
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                br = new BufferedReader(isr);
                String str;
                while ((str = br.readLine()) != null) {
                    result = result.concat(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                isr.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return result;
    }

    /**
     * 覆盖写入
     * @param content
     * @param filepath
     * @throws IOException
     */
    public static void writeTxtFile(String content, String filepath) throws IOException {
        FileOutputStream fos = null;
        try {
            File file = new File(filepath);
            fos = new FileOutputStream(file);
            fos.write(content.getBytes("gbk"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}
