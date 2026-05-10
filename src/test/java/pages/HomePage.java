package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By goodiesLink = By.linkText("Goodies");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarEmGoodies() {
        driver.findElement(goodiesLink).click();
    }
}