package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;

public class BrowserManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome");
            initializeDriver(browser);
        }
        return driver.get();
    }

    private static void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            default:
                // Setup ChromeDriver with WebDriverManager
                WebDriverManager.chromedriver().setup();

                // Configure Chrome options to prevent crashes
                ChromeOptions options = new ChromeOptions();
                
                // Essential arguments to prevent Chrome crashes
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--remote-allow-origins=*");
                
                // Additional stability options
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--window-size=1920,1080");
                
                // Set default download directory (optional)
                // options.setExperimentalOption("prefs", Map.of("download.default_directory", downloadPath));
                
                // Headless mode if needed (uncomment for CI/CD environments)
                // options.addArguments("--headless=new");
                
                // Create a user data directory to avoid DevToolsActivePort issues
                File userDataDir = new File(System.getProperty("java.io.tmpdir"), "chrome_user_data_" + System.currentTimeMillis());
                if (!userDataDir.exists()) {
                    userDataDir.mkdir();
                }
                options.addArguments("--user-data-dir=" + userDataDir.getAbsolutePath());
                
                // Create Chrome driver with options
                try {
                    driver.set(new ChromeDriver(options));
                } catch (Exception e) {
                    System.err.println("Failed to initialize Chrome driver with error: " + e.getMessage());
                    // Fallback to try headless mode if normal mode fails
                    options.addArguments("--headless=new");
                    driver.set(new ChromeDriver(options));
                }
                break;
        }

        // Configure driver
        driver.get().manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    // Force create a new driver instance
    public static WebDriver createNewDriver() {
        quitDriver();
        return getDriver();
    }
}