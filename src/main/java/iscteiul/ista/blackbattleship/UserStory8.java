package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserStory8 {

    private final WebDriver driver;

    public UserStory8(WebDriver driver) {
        this.driver = driver;
    }

    public void openHistoryPage() {
        driver.get("https://papergames.io/en/match-history");
    }

    public String getPageTitle() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}