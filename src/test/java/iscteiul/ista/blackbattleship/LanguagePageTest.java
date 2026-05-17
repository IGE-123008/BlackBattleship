package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LanguagePage;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanguagePageTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/");
    }

    @Test
    @DisplayName("US04 - Alterar Idioma para Espanhol")
    void testAlterarIdioma() {
        // Garante que a página carregou e o botão de idioma está visível
        // Vamos usar um seletor mais genérico que apanha o botão do globo
        $(".language-item, [aria-label='Change language'], .MuiButton-root").shouldBe(Condition.visible).click();

        // No PaperGames, o menu às vezes demora a renderizar as opções
        // Procuramos a opção "Español" dentro da lista que apareceu
        $(byText("Español")).shouldBe(Condition.visible).click();

        // Validação: URL deve conter /es/
        webdriver().shouldHave(urlContaining("/es/"));

        // Verificação extra: O botão de login agora deve dizer "Conectarse"
        // Isso confirma que o texto da página mudou mesmo
        $(byText("Conectarse")).shouldBe(Condition.visible);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}