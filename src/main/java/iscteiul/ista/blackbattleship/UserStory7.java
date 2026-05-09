package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserStory7 {

    private WebDriver driver;

    public UserStory7(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        driver.get("https://papergames.io/en/battleship");
    }

    public void clickLogin() {
        driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
    }


    public void clickGoogleLogin() {
        driver.findElement(By.xpath("//span[contains(.,'Continue with Google')]")).click();
    }
    public void openMyTournaments() {
        driver.get("https://papergames.io/en/t/my-tournaments");
    }
}