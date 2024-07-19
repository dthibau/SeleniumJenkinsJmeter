import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MySeleniumTest.ScreenshotOnFailure.class)
public class MySeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Vous devrez peut-être spécifier le chemin vers votre ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/home/dthibau/Formations/SeleniumJMeterJenkins/chromedriver-linux64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    @Step("Acessing Home Page")
    public void testHomePage() {
        driver.get("http://localhost:8080");

        // Attendre un peu pour que les résultats soient chargés
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("Gestion du catalogue ", driver.getTitle());
    }

   /* @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/

    public static class ScreenshotOnFailure implements TestWatcher {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            WebDriver driver = ((MySeleniumTest) context.getRequiredTestInstance()).driver;
            if (driver != null) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    Allure.addAttachment("Screenshot", new ByteArrayInputStream(Files.readAllBytes(screenshot.toPath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                driver.quit();
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            // Optional: Code to run when a test is successful
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
            // Optional: Code to run when a test is aborted
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
            // Optional: Code to run when a test is disabled
        }
    }
}
