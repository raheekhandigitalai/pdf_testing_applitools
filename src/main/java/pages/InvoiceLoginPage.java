package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvoiceLoginPage {

    private RemoteWebDriver driver;
    private WebDriverWait wait;

    private By emailField        = By.id("email");
    private By passwordField     = By.id("password");
    private By loginButton       = By.xpath("//span[contains(text(), 'Login')]/parent::button");

    private By newInvoiceButton  = By.xpath("//span[contains(text(), 'New Invoice')]");

    public InvoiceLoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void navigateToLoginPage() {
        driver.navigate().to("https://app.invoicesimple.com/login");
    }

    public void login() {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).sendKeys(System.getProperty("sites.invoices.username"));
        driver.findElement(passwordField).sendKeys(System.getProperty("sites.invoices.password"));
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(newInvoiceButton));
        driver.findElement(newInvoiceButton).click();
    }

}
