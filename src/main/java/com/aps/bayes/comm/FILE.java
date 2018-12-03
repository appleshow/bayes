package com.aps.bayes.comm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 */
public class FILE {
    private final static String ENCODING = "UTF-8";

    /**
     * @param filePath
     * @return
     */
    public static String readTxtFile(String filePath) {
        return readTxtFile(new File(filePath));
    }

    /**
     * @param file
     * @return
     */
    public static String readTxtFile(File file) {
        String lineText = "";

        try {
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING);
                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTextTemp;
                while ((lineTextTemp = bufferedReader.readLine()) != null) {
                    lineText += lineTextTemp;
                }
                read.close();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return lineText;
        }

    }
}
