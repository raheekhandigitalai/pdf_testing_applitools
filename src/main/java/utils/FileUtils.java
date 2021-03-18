package utils;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class FileUtils {

    /**
     * Moves file from one location to another.
     * Deletes file from destination if it already exists, before moving
     * @param file file to move
     * @param destination pathname to move file to
     * @return was move successful
     */
    public static boolean moveFile(File file, String destination) {

        File existingFile = new File(destination);
        if(existingFile.exists()){
            existingFile.delete();
        }

        return file.renameTo(new File(destination));
    }

    public static void downloadFileFromSeleniumAgent(RemoteWebDriver driver) {
        byte[] content = null;
        Object res = driver.executeScript("seetest:client.getFile(\"INV12345.pdf\")");
        content = Base64.getDecoder().decode((String) res);
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File("C:\\Users\\RaheeKhan\\Desktop\\Clients\\POCs\\BroadridgeFinancial\\pdf_testing\\automation\\INV12345.pdf"), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
