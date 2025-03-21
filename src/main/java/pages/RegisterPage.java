package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {
    
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By privacyPolicy = By.name("agree");
    private By continueButton = By.xpath("//input[@value='Continue']");
    private By successMessage = By.xpath("//*[@id=\"content\"]/p[1]");
    private By accountExistsMessage = By.cssSelector(".alert-danger");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterRegistrationDetails(String fname, String lname, String mail, String phone, String pass) {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(telephone).sendKeys(phone);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).sendKeys(pass);
    }

    public void acceptPrivacyPolicy() {
        driver.findElement(privacyPolicy).click();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public boolean isRegistrationSuccessful() {
        return driver.findElement(successMessage).isDisplayed();
    }

	public boolean isAccountAlreadyExists() {
		try {
            WebElement errorMessage = driver.findElement(accountExistsMessage);
            return errorMessage.getText().contains("already registered") || errorMessage.getText().contains("already exists");
        } catch (NoSuchElementException e) {
            return false;
        }
	}
}
