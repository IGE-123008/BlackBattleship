package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory7Test {

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
    public void createTournamentTest() {
        UserStory7 page = new UserStory7(driver);

        page.openHomePage();
        page.openMyTournaments();
        page.openCreateTournamentPage();

        page.chooseBattleshipGame();
        page.writeTournamentName("Battleship Test 123029");

        page.clickCreateAndShare();

        String shareLink = page.getShareLink();

        assertNotNull(shareLink);
        assertFalse(shareLink.isBlank());
    }
}