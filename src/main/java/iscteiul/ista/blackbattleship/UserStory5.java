package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserStory5 {

    private final WebDriver driver;

    public UserStory5(WebDriver driver) {
        this.driver = driver;
    }

    public void openTournamentCreationPage() {
        driver.get("https://papergames.io/en/t/create-tournament");
    }

    public String getPageTitle() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}