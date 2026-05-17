package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class para a User Story 08.
 */
public class UserStory08 {

    private final WebDriver driver;

    private final By firstGameImage = By.cssSelector(".game-item:nth-child(1) .img-fluid");
    private final By firstChatOrCommunityItem = By.cssSelector(".item:nth-child(1) .text-truncate");

    public UserStory08(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://papergames.io/en/");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1275, 692));
    }

    public void acceptCookiesIfVisible() {
        try {
            driver.findElement(By.cssSelector(".fc-cta-consent")).click();
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }
    }

    public void openFirstGame() {
        acceptCookiesIfVisible();
        driver.findElement(firstGameImage).click();
    }

    public boolean isCommunityItemVisible() {
        acceptCookiesIfVisible();
        return driver.findElement(firstChatOrCommunityItem).isDisplayed();
    }

    public void openCommunityItem() throws InterruptedException {
        acceptCookiesIfVisible();

        WebElement element = driver.findElement(firstChatOrCommunityItem);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        Thread.sleep(800);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
}