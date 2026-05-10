import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

public class HomeTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://papergames.io/en/");
    }

    @Test
    @DisplayName("US13: Acesso ao Merchandising")
    public void testUS13_Merch() {
        HomePage home = new HomePage(driver);
        home.clicarEmGoodies();

        // Trocar para a nova janela que abriu
        Object[] handles = driver.getWindowHandles().toArray();
        driver.switchTo().window(handles[1].toString());

        Assertions.assertTrue(driver.getCurrentUrl().contains("merch"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}