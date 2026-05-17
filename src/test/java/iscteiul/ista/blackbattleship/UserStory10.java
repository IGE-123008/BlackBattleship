package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Class para a User Story 10.
 *
 * US10: Como membro da comunidade, quero ter acesso aos links das redes sociais
 * (Discord, X, Facebook, YouTube, Instagram) para acompanhar novidades e eventos.
 *
 * Esta classe contém os localizadores e ações sobre os links sociais.
 */
public class UserStory10 {

    private final WebDriver driver;

    private final By discordLink = By.linkText("Discord");
    private final By xLink = By.linkText("𝕏");
    private final By facebookLink = By.linkText("Facebook");
    private final By youtubeLink = By.linkText("YouTube");
    private final By instagramLink = By.linkText("Instagram");

    /**
     * Construtor da Page Object.
     *
     * @param driver instância do WebDriver usada no teste
     */
    public UserStory10(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Abre a página principal do PaperGames.
     */
    public void openPage() {
        driver.get("https://papergames.io/en/");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1275, 692));
    }

    /**
     * Faz scroll até ao fundo da página para tornar visíveis os links sociais.
     */
    public void scrollToFooter() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0, 500)");
        Thread.sleep(700);

        js.executeScript("window.scrollBy(0, 500)");
        Thread.sleep(700);

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
    }

    /**
     * Verifica se o link do Discord está visível.
     *
     * @return true se estiver visível
     */
    public boolean isDiscordVisible() {
        return driver.findElement(discordLink).isDisplayed();
    }

    /**
     * Verifica se o link do X está visível.
     *
     * @return true se estiver visível
     */
    public boolean isXVisible() {
        return driver.findElement(xLink).isDisplayed();
    }

    /**
     * Verifica se o link do Facebook está visível.
     *
     * @return true se estiver visível
     */
    public boolean isFacebookVisible() {
        return driver.findElement(facebookLink).isDisplayed();
    }

    /**
     * Verifica se o link do YouTube está visível.
     *
     * @return true se estiver visível
     */
    public boolean isYoutubeVisible() {
        return driver.findElement(youtubeLink).isDisplayed();
    }

    /**
     * Verifica se o link do Instagram está visível.
     *
     * @return true se estiver visível
     */
    public boolean isInstagramVisible() {
        return driver.findElement(instagramLink).isDisplayed();
    }
}
