package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Class para a User Story 09.
 *
 * US09: Como utilizador, quero gerir a minha Lista de Amigos para ver quem está online
 * e enviar convites diretos para jogos privados.
 */
public class UserStory09 {

    private final WebDriver driver;

    private final By body = By.tagName("body");

    public UserStory09(WebDriver driver) {
        this.driver = driver;
    }

    public void openFriendsPage() {
        driver.get("https://papergames.io/en/friends");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1275, 692));
    }

    public boolean isFriendsPageOpened() {
        return driver.getCurrentUrl().toLowerCase().contains("friends");
    }

    public boolean hasFriendsContent() {
        String text = driver.findElement(body).getText().toLowerCase();
        return text.contains("friend")
                || text.contains("friends")
                || text.contains("login")
                || text.contains("sign in");
    }
}