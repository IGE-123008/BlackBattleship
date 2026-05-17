package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class para a User Story 09.
 */
public class UserStory09Test {

    private WebDriver driver;
    private UserStory09 page;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        page = new UserStory09(driver);
    }

    @Test
    void userStory09() throws InterruptedException {
        page.openFriendsPage();

        Thread.sleep(2000);

        assertTrue(page.isFriendsPageOpened(), "A página Friends deve ser aberta.");
        assertTrue(page.hasFriendsContent(), "A página deve mostrar conteúdo relacionado com Friends ou login.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}