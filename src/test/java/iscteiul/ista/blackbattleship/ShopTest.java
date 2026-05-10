import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ShopPage;

public class ShopTest {
    private WebDriver driver;
    private ShopPage shopPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        shopPage = new ShopPage(driver);
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/battleship");
    }

    @Test
    @DisplayName("US11: Aquisição de moedas virtuais")
    public void testUS11_BuyCoins() throws InterruptedException {
        shopPage.abrirLoja();
        shopPage.irParaMoedas();
        shopPage.selecionarPrimeiroPacote();
        Thread.sleep(2000); // Pausa para observação (conforme guião)
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}