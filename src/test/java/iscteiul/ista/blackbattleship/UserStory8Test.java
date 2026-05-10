package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory8Test {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void openHistoryTest() {
        UserStory8 page = new UserStory8(driver);

        page.openHistoryPage();

        String title = page.getPageTitle();

        assertTrue(
                title.equals("History") || title.equals("You are not authenticated")
        );
    }
}