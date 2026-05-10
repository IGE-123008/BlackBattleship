package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShopPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Elementos US11 (Moedas)
    private By shopMenu = By.cssSelector(".cdk-focused > .hide-if-collapsed");
    private By coinsTab = By.linkText("Coins");
    private By firstCoinPack = By.cssSelector("app-currency-product:nth-child(1) .btn");

    public ShopPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void abrirLoja() {
        wait.until(ExpectedConditions.elementToBeClickable(shopMenu)).click();
    }

    public void irParaMoedas() {
        wait.until(ExpectedConditions.elementToBeClickable(coinsTab)).click();
    }

    public void selecionarPrimeiroPacote() {
        wait.until(ExpectedConditions.elementToBeClickable(firstCoinPack)).click();
    }

    // Elementos US12 (Avatares)
    private By monstersCategory = By.linkText("Monsters");
    private By buyAvatarBtn = By.cssSelector(".h-100:nth-child(1) > .h-100 > app-shop-purchase-product-button > .btn");
    private By confirmBtn = By.cssSelector(".btn-secondary:nth-child(2)");

    public void comprarAvatarMonstro() {
        wait.until(ExpectedConditions.elementToBeClickable(monstersCategory)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buyAvatarBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
    }


}