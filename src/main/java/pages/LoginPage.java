package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By accountPage = By.xpath("//h2[contains(text(),'My Account')]");
    private By logoutBtn = By.linkText("Logout");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterLoginCredentials(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        return driver.findElement(accountPage).isDisplayed();
    }
    
    public void clickLogout() {
        driver.findElement(logoutBtn).click();
    }
}
