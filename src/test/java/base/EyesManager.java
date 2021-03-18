package base;

import com.applitools.eyes.selenium.Eyes;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

public class EyesManager {

    private Eyes eyes;
    private String appName;
    private RemoteWebDriver driver;

    public EyesManager(RemoteWebDriver driver, String appName) {
        this.driver = driver;
        this.appName = appName;

        eyes = new Eyes();
        eyes.setApiKey(System.getProperty("applitools.api.key"));
    }

    public void validateWindow() {
        eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkWindow();
        eyes.close();
    }

    public static boolean validatePDF(String filepath) throws IOException, InterruptedException {
        String command = String.format(
                "java -jar " + System.getProperty("user.dir") + "\\resources\\ImageTester.jar -k %s -f %s",
                System.getProperty("applitools.api.key"),
                filepath);

        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        String stream = IOUtils.toString(process.getInputStream(), "UTF-8");
        System.out.println(stream);

        if (stream != null && stream.contains("Mismatch")) {
            return false;
        }

        return true;
    }

    public void abort(){
        eyes.abortIfNotClosed();
    }

    public Eyes getEyes(){
        return eyes;
    }
}
