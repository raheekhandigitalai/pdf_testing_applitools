package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class InvoicePreviewPage {

    private RemoteWebDriver driver;
    private WebDriverWait wait;

    private By pdfButton = By.xpath("//a[contains(text(), 'PDF')]");

    public InvoicePreviewPage(RemoteWebDriver driver) {
        this.driver = driver;

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for(String window: driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pdfButton));
    }

    public void clickPDFButton(){
        driver.findElement(pdfButton).click();

        //Wait for download to finish before exiting this method
        File file = new File("C:\\Users\\RaheeKhan\\Desktop\\Clients\\POCs\\BroadridgeFinancial\\pdf_testing\\automation\\INV12345.pdf");
        while (!file.exists()) {
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
