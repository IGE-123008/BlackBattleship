package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BattleshipPlayPage;

import java.time.Duration;

public class BattleshipPlayPageTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/");

        driver.findElement(By.cssSelector("img[alt='Battleship']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".w-100:nth-child(2) > .btn"))).click();
    }

    @Test
    @DisplayName("US01 - Testar Uso de Míssil Especial")
    void testUsoArmaEspecial() {
        BattleshipPlayPage playPage = new BattleshipPlayPage(driver);
        playPage.dispararMissilEspecial();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}