package linh.to.utils;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    private static final String RESOURCE_FOLDER_PATH = "resource";

    public static void emptyResourceFolder() {
        File f = new File(RESOURCE_FOLDER_PATH);
        if (f.isDirectory()) {
            try {
                org.apache.commons.io.FileUtils.cleanDirectory(f);
            } catch (IOException e) {
            }
        } else {
            f.mkdirs();
        }
    }
    public static void writeFile(Object obj, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        writeFile(json, fileName);
    }
    public static void writeFile(String json, String fileName) {
        try {
            File f = new File(RESOURCE_FOLDER_PATH, fileName);
            if (f.exists()) {
                f.deleteOnExit();
            }
            f.createNewFile();
            org.apache.commons.io.FileUtils.writeStringToFile(f, json);
            System.out.println("Write file: " + f.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
