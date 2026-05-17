package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class para a User Story 16.
 *
 * US16: Como utilizador móvel, quero ter links diretos para as lojas de aplicações
 * (Google Play e App Store) para jogar Batalha Naval no meu smartphone ou tablet.
 *
 * Esta classe contém apenas os localizadores e as ações realizadas na página.
 */
public class UserStory16 {

    private final WebDriver driver;

    private final By googlePlayBadge = By.cssSelector("app-playstore-badge img");
    private final By appStoreBadge = By.cssSelector("app-appstore-badge img");

    /**
     * Construtor da Page Object.
     *
     * @param driver instância do WebDriver usada no teste
     */
    public UserStory16(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Abre a página principal do PaperGames.
     */
    public void openPage() {
        driver.get("https://papergames.io/en/");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(550, 692));
    }

    /**
     * Verifica se o botão da Google Play Store está visível.
     *
     * @return true se o botão estiver visível
     */
    public boolean isGooglePlayVisible() {
        WebElement element = driver.findElement(googlePlayBadge);
        return element.isDisplayed();
    }

    /**
     * Verifica se o botão da App Store está visível.
     *
     * @return true se o botão estiver visível
     */
    public boolean isAppStoreVisible() {
        WebElement element = driver.findElement(appStoreBadge);
        return element.isDisplayed();
    }

    /**
     * Clica no botão da Google Play Store.
     */
    public void clickGooglePlay() {
        driver.findElement(googlePlayBadge).click();
    }

    /**
     * Clica no botão da App Store.
     */
    public void clickAppStore() {
        driver.findElement(appStoreBadge).click();
    }
}
