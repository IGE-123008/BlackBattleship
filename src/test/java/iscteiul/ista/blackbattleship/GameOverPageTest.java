package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import pages.GameOverPage;

public class GameOverPageTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        // Inicializa o driver antes de cada teste
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/");
    }

    @Test
    @DisplayName("US03 - Pedir Rematch")
    void testRematch() {
        driver.findElement(By.cssSelector("img[alt='Battleship']")).click();

        GameOverPage gameOver = new GameOverPage(driver);

        gameOver.solicitarRematch();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}