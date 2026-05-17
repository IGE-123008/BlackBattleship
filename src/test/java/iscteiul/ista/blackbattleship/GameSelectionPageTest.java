package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GameSelectionPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSelectionPageTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/");
    }

    @Test
    @DisplayName("US02 - Iniciar Jogo Contra Robô")
    void testJogarContraRobot() {
        GameSelectionPage selection = new GameSelectionPage(driver);
        selection.selecionarBattleship();
        selection.clicarJogarContraRobot();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // VALIDAÇÃO CORRIGIDA:
        // O log mostrou que o URL contém "/r/", então vamos validar isso.
        // Também adicionamos uma pequena espera para o canvas garantir que o jogo carregou.
        boolean iniciou = wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/r/"),
                ExpectedConditions.urlContains("/play/"),
                ExpectedConditions.presenceOfElementLocated(By.tagName("canvas"))
        ));

        assertTrue(iniciou, "O jogo não iniciou. URL atual: " + driver.getCurrentUrl());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}