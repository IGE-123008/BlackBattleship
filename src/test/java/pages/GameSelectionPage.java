package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GameSelectionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By battleshipImg = By.cssSelector("img[alt='Battleship']");
    private By playVsRobotBtn = By.xpath("//button[contains(.,'Play vs Robot')]");

    public GameSelectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void selecionarBattleship() {
        wait.until(ExpectedConditions.elementToBeClickable(battleshipImg)).click();
    }

    public void clicarJogarContraRobot() {
        // Procura o botão que contém o texto Robot (independente de maiúsculas/minúsculas)
        By robotBtnSelector = By.xpath("//button[contains(translate(., 'ROBOT', 'robot'), 'robot')]");

        try {
            WebElement robotBtn = wait.until(ExpectedConditions.presenceOfElementLocated(robotBtnSelector));

            // Scroll até ao botão para garantir que está no ecrã
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", robotBtn);

            // Clique via JavaScript (mais robusto que o click() normal)
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", robotBtn);

            // Lidar com o campo de Nickname se ele aparecer
            try {
                WebElement nick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='nickname']")));
                nick.sendKeys("PlayerTest");
                driver.findElement(By.cssSelector("button[type='submit']")).click();
            } catch (Exception e) {
                // Se não pedir nick, ignoramos
            }

        } catch (Exception e) {
            System.out.println("Não foi possível clicar no botão Robot: " + e.getMessage());
        }
    }
}