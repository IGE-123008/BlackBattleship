package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class para a User Story 10.
 *
 * Esta classe valida os links das redes sociais usando a Page Object Class UserStory10.
 */
public class UserStory10Test {

    private WebDriver driver;
    private UserStory10 page;

    /**
     * Inicializa o WebDriver antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        page = new UserStory10(driver);
    }

    /**
     * Testa se os links das redes sociais estão visíveis no footer.
     */
    @Test
    void userStory10() throws InterruptedException {
        page.openPage();

        Thread.sleep(1500);
        page.scrollToFooter();

        assertTrue(page.isDiscordVisible(), "O link Discord deve estar visível.");
        assertTrue(page.isXVisible(), "O link X deve estar visível.");
        assertTrue(page.isFacebookVisible(), "O link Facebook deve estar visível.");
        assertTrue(page.isYoutubeVisible(), "O link YouTube deve estar visível.");
        assertTrue(page.isInstagramVisible(), "O link Instagram deve estar visível.");
    }

    /**
     * Fecha o browser após cada teste.
     */
    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
