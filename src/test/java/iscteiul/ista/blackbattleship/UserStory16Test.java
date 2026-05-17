package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class para a User Story 16.
 *
 * Esta classe contém apenas o código JUnit que usa os métodos definidos
 * na Page Object Class UserStory16.
 */
public class UserStory16Test {

    private WebDriver driver;
    private UserStory16 page;

    /**
     * Inicializa o WebDriver antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        page = new UserStory16(driver);
    }

    /**
     * Testa se os links diretos para Google Play e App Store estão disponíveis.
     */
    @Test
    void userStory16() throws InterruptedException {
        page.openPage();

        Thread.sleep(1500);

        assertTrue(page.isGooglePlayVisible(), "O botão da Google Play deve estar visível.");
        assertTrue(page.isAppStoreVisible(), "O botão da App Store deve estar visível.");

        page.clickGooglePlay();
        Thread.sleep(2000);

        page.openPage();
        Thread.sleep(1500);

        page.clickAppStore();
        Thread.sleep(2000);
    }

    /**
     * Fecha o browser após cada teste.
     */
    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
