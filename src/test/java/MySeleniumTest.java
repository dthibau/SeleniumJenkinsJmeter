import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Vous devrez peut-être spécifier le chemin vers votre ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/home/dthibau/Formations/SeleniumJMeterJenkins/chromedriver-linux64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testHomePage() {
        driver.get("http://localhost:8080");

        // Attendre un peu pour que les résultats soient chargés
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("Gestion du catalogue produit", driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
