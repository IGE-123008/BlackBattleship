package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserStory7 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public UserStory7(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openHomePage() {
        driver.get("https://papergames.io/en/battleship");
    }

    public void openMyTournaments() {
        driver.get("https://papergames.io/en/t/my-tournaments");
    }

    public void openCreateTournamentPage() {
        driver.get("https://papergames.io/en/t/create-tournament");
    }

    public void chooseBattleshipGame() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div:nth-child(1) > .mat-mdc-form-field .mat-mdc-form-field-infix")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option[contains(.,'Battleship')]")
        )).click();
    }

    public void writeTournamentName(String name) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input")
        ));
        input.clear();
        input.sendKeys(name);
    }

    public void clickCreateAndShare() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Create and share')]")
        )).click();
    }

    public String getShareLink() {
        WebElement linkInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input")
        ));
        return linkInput.getAttribute("value");
    }

    public void copyShareLink() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".fa-copy")
        )).click();
    }

    public void goToTournament() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Go to tournament')]")
        )).click();
    }
}