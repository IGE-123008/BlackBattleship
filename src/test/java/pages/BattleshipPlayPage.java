package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BattleshipPlayPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores
    private By missileButton = By.cssSelector(".weapon-button:nth-child(3) > img");
    private By targetCell = By.cssSelector(".cell-4-5");

    public BattleshipPlayPage(WebDriver driver) {
        this.driver = driver;
        // Criamos uma espera de até 10 segundos
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void dispararMissilEspecial() {
        // EM VEZ DE: driver.findElement(...).click();
        // USAMOS: wait.until(...) para garantir que o botão apareceu
        wait.until(ExpectedConditions.visibilityOfElementLocated(missileButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(targetCell)).click();
    }
}