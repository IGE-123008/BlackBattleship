package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}