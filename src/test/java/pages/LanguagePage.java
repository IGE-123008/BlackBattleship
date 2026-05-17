package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LanguagePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Seletor do botão do globo (idioma)
    private By languageMenuBtn = By.cssSelector("button[aria-label='Change language'], .mat-mdc-menu-trigger");

    public LanguagePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void abrirMenuIdiomas() {
        wait.until(ExpectedConditions.elementToBeClickable(languageMenuBtn)).click();
    }

    public void mudarParaEspanhol() {
        // Espera as opções do menu aparecerem
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".mat-mdc-menu-item")));

        // Procura todos os botões do menu e clica no que tem "Español"
        List<WebElement> options = driver.findElements(By.cssSelector(".mat-mdc-menu-item"));
        for (WebElement option : options) {
            if (option.getText().contains("Español")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                break;
            }
        }
    }
}