package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GameOverPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Seletor do botão de Rematch
    private By rematchBtn = By.cssSelector(".front > .ng-star-inserted");

    public GameOverPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(150));
    }

    public void solicitarRematch() {
        // Espera que o botão esteja visível e clicável antes de carregar
        wait.until(ExpectedConditions.elementToBeClickable(rematchBtn)).click();
    }
}