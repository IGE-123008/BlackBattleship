package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserStory6 {

    private final WebDriver driver;

    public UserStory6(WebDriver driver) {
        this.driver = driver;
    }

    public void openMyTournamentsPage() {
        driver.get("https://papergames.io/en/t/my-tournaments");
    }

    public String getPageTitle() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}