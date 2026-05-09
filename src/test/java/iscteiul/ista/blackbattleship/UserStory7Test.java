package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserStory7Test {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://papergames.io/en/battleship");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void createTournamentTest() {

        UserStory7 page = new UserStory7(driver);

        page.openHomePage();
        page.clickLogin();
        page.clickGoogleLogin();
    }
}