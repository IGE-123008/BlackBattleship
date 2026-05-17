package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class para a User Story 08.
 *
 * Esta classe valida o acesso à área social/chat usando a Page Object Class UserStory08.
 */
public class UserStory08Test {

    private WebDriver driver;
    private UserStory08 page;

    /**
     * Inicializa o WebDriver antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        page = new UserStory08(driver);
    }

    /**
     * Testa se o utilizador consegue abrir uma área social/comunidade associada ao jogo.
     */
    @Test
    void userStory08() throws InterruptedException {
        page.openPage();

        Thread.sleep(1500);

        page.openFirstGame();
        Thread.sleep(2000);

        assertTrue(page.isCommunityItemVisible(), "O item social/comunidade deve estar visível.");

        page.openCommunityItem();
        Thread.sleep(1500);
    }

    /**
     * Fecha o browser após cada teste.
     */
    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
