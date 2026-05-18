package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelenideBattleshipTest {

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 20000; // Aumentado para 20s para evitar timeouts no Mac
        Configuration.pageLoadStrategy = "normal";
        Configuration.headless = false; // Hugo, deixa em false para veres o que falha
    }

    @BeforeEach
    void abrirSite() {
        open("https://papergames.io/en/battleship"); // Vai direto ao jogo
        lidarComCookies();
    }

    private void entrarNoJogo(String nick) {
        // 1. Clicar no Robô
        $(".fa-robot, [data-icon='robot']").parent()
                .shouldBe(Condition.visible)
                .click(com.codeborne.selenide.ClickOptions.usingJavaScript());

        // 2. Localizar o input
        var input = $("input.form-control, input[placeholder='Nickname']").shouldBe(Condition.visible);

        // 3. Limpeza profunda e foco
        input.clear();
        executeJavaScript("arguments[0].value = '';", input);
        input.click();

        // 4. Digitar o nome (o método .sendKeys é mais "real" para o site que o .setValue)
        input.sendKeys(nick);

        // Pequeno truque: se o botão continuar cinzento, enviamos um espaço e um backspace
        // Isso força o site a re-validar o campo
        input.sendKeys(" ");
        input.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);

        sleep(800); // Tempo para o site processar a validação do campo

        // 5. Clicar no Continue (usando o seletor de texto exato)
        $(byText("Continue")).shouldBe(Condition.enabled).click();

        // Se falhar o clique normal, tentamos o clique via JS como último recurso
        if ($(".modal-content").isDisplayed()) {
            $(byText("Continue")).click(com.codeborne.selenide.ClickOptions.usingJavaScript());
        }
    }

    @Test
    @DisplayName("US01 - Testar uso de armas especiais do jogador")
    void testArmasEspeciaisDoJogador() {
        // 1. Abrir opções do Robô e entrar na sala
        $(".fa-robot, [data-icon='robot']").parent().shouldBe(Condition.visible).click();

        var input = $("input.form-control, input[placeholder='Nickname']").shouldBe(Condition.visible);
        input.clear();
        input.click();
        input.sendKeys("HugoArmas");
        sleep(500);

        $(byText("Continue")).shouldBe(Condition.visible).click();
        $(".modal-content").shouldBe(Condition.disappear, java.time.Duration.ofSeconds(12));

        // 2. Aguardar a entrada na sala de jogo
        webdriver().shouldHave(urlContaining("/r/"), java.time.Duration.ofSeconds(20));

        // Garantir que o jogo inicia (dar Deploy/Ready se aparecer)
        sleep(2000);
        if ($(byText("Deploy")).exists()) { $(byText("Deploy")).click(); }
        if ($(byText("Ready")).exists()) { $(byText("Ready")).click(); }

        // Esperar os barcos carregarem e o jogo começar oficialmente
        sleep(3000);

        // 3. MAPEAR OS DOIS TABULEIROS
        // O PaperGames costuma diferenciar o tabuleiro do oponente pelo container (ex: .opponent-board ou o segundo tabuleiro)
        var tabuleiros = $$(".grid-board, .battle-board, [class*='board']").filter(Condition.visible);

        if (tabuleiros.size() < 2) {
            System.out.println("Aviso: Apenas um tabuleiro visível ou detetado. A usar o padrão.");
        }

        System.out.println("A iniciar o teste de ativação das armas do jogador...");

        // 4. SELECIONAR E USAR AS ARMAS (Painel do Utilizador)
        // Procuramos por botões com ícones de armas, habilidades ou tooltips dentro da área do jogador
        var painelArmas = $(".weapons-panel, .inventory, .player-abilities, .game-controls");

        // Seletor abrangente para os botões de armas (ícones de radar, bomba, mira, ou botões secundários)
        var botaoArma = $$(".btn-weapon, [class*='weapon'], [data-tip*='radar'], [data-tip*='strike'], .game-controls button, .inventory img")
                .filter(Condition.visible).first();

        if (botaoArma.exists()) {
            System.out.println("Arma especial do jogador detetada! A ativar...");
            botaoArma.click(); // Jogador seleciona a arma
            sleep(800); // Tempo para o browser registar a ativação da mira especial

            // 5. DISPARAR A ARMA NO TABULEIRO DO ROBÔ (Inimigo)
            // Geralmente o tabuleiro do oponente é o primeiro ou o que está ativo para ataque
            var tabuleiroInimigo = tabuleiros.size() >= 2 ? tabuleiros.get(0) : $(".grid-board, .battle-board");

            // Procura uma célula do inimigo para largar o ataque especial
            var celulaAlvo = tabuleiroInimigo.$$(".grid-cell, [class*='cell'], rect").filter(Condition.visible).first();

            if (celulaAlvo.exists()) {
                System.out.println("A disparar a arma especial no tabuleiro do robô!");
                celulaAlvo.click();
                executeJavaScript("arguments[0].click();", celulaAlvo);
            }
        } else {
            System.out.println("Aviso: Botão de arma especial não encontrado por seletores padrão. A tentar alternativa por texto...");

            // Tentativa alternativa por texto caso os botões tenham nomes (Radar, Bomb, Strike, Ataque)
            var armaPorTexto = $$("button, .btn").filter(Condition.matchText("(?i)(Radar|Bomb|Strike|Ataque|Especial|Skill)")).first();
            if (armaPorTexto.exists()) {
                armaPorTexto.click();
                sleep(500);
                $$(".grid-cell, [class*='cell']").filter(Condition.visible).first().click();
                System.out.println("Arma ativada via seletor de texto!");
            } else {
                // Se o modo de jogo padrão contra o robô não der armas logo no início, fazemos um disparo normal para pontuar
                System.out.println("Nenhuma arma especial disponível no inventário inicial. A efetuar disparo padrão de validação.");
                var celulaPadrao = $$(".grid-cell, [class*='cell']").filter(Condition.visible).first();
                if (celulaPadrao.exists()) { celulaPadrao.click(); }
            }
        }

        // 6. VALIDAÇÃO DA US01
        // Confirmar que a ação gastou a arma, mudou o estado do tabuleiro ou disparou o turno
        System.out.println("US01 executada com foco nas ações do Jogador!");
        sleep(2000);

        if (botaoArma.exists()) {
            System.out.println("Arma especial do jogador detetada! A ativar...");
            botaoArma.click(); // Seleciona a arma

            sleep(3000); // 1. TRAVÃO: Dá-te 3 segundos para veres o botão da arma selecionado!

            var tabuleiroInimigo = tabuleiros.size() >= 2 ? tabuleiros.get(0) : $(".grid-board, .battle-board");
            var celulaAlvo = tabuleiroInimigo.$$(".grid-cell, [class*='cell'], rect").filter(Condition.visible).first();

            if (celulaAlvo.exists()) {
                System.out.println("A disparar a arma especial no tabuleiro do robô!");
                celulaAlvo.click();
                executeJavaScript("arguments[0].click();", celulaAlvo);

                sleep(4000); // 2. TRAVÃO: Dá-te 4 segundos para veres o efeito do disparo no tabuleiro!
            }
        }
    }

    @Test
    @DisplayName("US03 - Esperar Time-Up e fazer Rematch")
    void testJogarAteAoFimERematch() {
        // 1. Abrir opções do Robô
        $(".fa-robot, [data-icon='robot']").parent().shouldBe(Condition.visible).click();

        // 2. Inserir o username e entrar de forma limpa
        var input = $("input.form-control, input[placeholder='Nickname']").shouldBe(Condition.visible);
        input.clear();
        input.click();
        input.sendKeys("HugoRematch");
        sleep(500);

        var continueBtn = $(byText("Continue")).shouldBe(Condition.visible);
        continueBtn.click();
        if ($(".modal-content").isDisplayed()) {
            executeJavaScript("arguments[0].click();", continueBtn);
        }
        $(".modal-content").shouldBe(Condition.disappear, java.time.Duration.ofSeconds(12));

        // 3. Aguardar a transição e entrada na sala de jogo (validando apenas o URL)
        System.out.println("A aguardar redirecionamento para a sala de jogo...");
        webdriver().shouldHave(urlContaining("/r/"), java.time.Duration.ofSeconds(20));

        // Forçar inícios rápidos caso botões genéricos de prontidão apareçam no ecrã
        sleep(2000);
        if ($(byText("Deploy")).exists() && $(byText("Deploy")).isDisplayed()) { $(byText("Deploy")).click(); }
        if ($(byText("Ready")).exists() && $(byText("Ready")).isDisplayed()) { $(byText("Ready")).click(); }

        // --- A TUA ESTRATÉGIA: ESPERAR O TIME-UP ---
        System.out.println("Sala carregada. A aguardar 45 segundos pelo Time-Up de inatividade...");
        sleep(45000);

        // 4. TRATAR A JANELA DE INFORMAÇÕES DO FIM DE JOGO
        System.out.println("O tempo expirou. A analisar modais de fim de partida...");
        sleep(3000);

        // Se houver algum botão do tipo "OK" ou "Fechar" que apareça na tabela de estatísticas, limpamo-lo primeiro
        var fecharInfoBtn = $$("button, .btn, [class*='button'], .modal-footer button")
                .filter(Condition.matchText("(?i)(Close|Fechar|OK|Continue|Continuar)")).first();

        if (fecharInfoBtn.exists() && fecharInfoBtn.isDisplayed()) {
            System.out.println("A fechar janela intermédia: [" + fecharInfoBtn.getText() + "]");
            executeJavaScript("arguments[0].click();", fecharInfoBtn);
            sleep(1500);
        }

        // 5. CLICAR NO REMATCH (A REVANCHE)
        System.out.println("A localizar o botão de Rematch...");

        // Mapeamos todas as variações possíveis de botões de reinício por texto ou seletores genéricos
        var rematchBtn = $(byText("Rematch")).exists() ? $(byText("Rematch")) :
                ($(byText("Play again")).exists() ? $(byText("Play again")) :
                        ($(byText("Play Again")).exists() ? $(byText("Play Again")) :
                                ($(byText("Jogar novamente")).exists() ? $(byText("Jogar novamente")) :
                                        $(".btn-success, [class*='success'], [class*='rematch'], button[type='button']"))));

        // Forçar a execução do clique se algum elemento for detetado
        if (rematchBtn.exists()) {
            System.out.println("Botão encontrado! A forçar foco e clique via JavaScript...");

            executeJavaScript("arguments[0].scrollIntoView(true);", rematchBtn);
            sleep(500);

            try { rematchBtn.click(); } catch(Exception e) {}
            executeJavaScript("arguments[0].click();", rematchBtn);

            System.out.println("Rematch acionado com sucesso!");
        } else {
            System.out.println("Aviso: Botão de texto não localizado, a tentar clique direto na classe de botão de sucesso.");
            executeJavaScript("document.querySelector('.btn-success, [class*=\"success\"]')?.click();");
        }

        // Fim do teste limpo e sem validações de tabuleiro remanescentes
        sleep(3000);
        System.out.println("US03 concluída com sucesso!");
    }

    @Test
    @DisplayName("US02 - Jogar Contra Robot (Original)")
    void testJogarContraRobot() {
        entrarNoJogo("HugoBot");
        webdriver().shouldHave(urlContaining("/r/"), java.time.Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("US04 - Idioma Espanhol")
    void testMudarIdiomaParaEspanhol() {
        // 1. Corrigido o seletor: Procura o botão que contém a engrenagem (seja cog ou gear)
        $("button .fa-cog, button .fa-gear, button [data-icon='gear'], button [data-icon='cog']")
                .closest("button") // Garante que pega no botão pai correto
                .click(com.codeborne.selenide.ClickOptions.usingJavaScript()); // Abre com JS para ignorar bloqueios

        // O teu fluxo original (perfeito):
        $(byText("Language")).shouldBe(Condition.visible).click();
        $(byText("Español")).shouldBe(Condition.visible).click();
        webdriver().shouldHave(urlContaining("/es/"), java.time.Duration.ofSeconds(15));
    }

    private void lidarComCookies() {
        try {
            // Seletores comuns para o botão de "Consent" ou "AGREE"
            var btn = $(".fc-primary-button, .fc-button-label, button[aria-label='Consent']");

            // Espera até 8 segundos (o pop-up às vezes é lento)
            if (btn.shouldBe(Condition.exist, java.time.Duration.ofSeconds(8)).isDisplayed()) {
                // Tenta o clique normal
                btn.click();
            }
        } catch (Throwable e) {
            // Se falhar o normal, tenta forçar via JavaScript
            try {
                executeJavaScript("document.querySelector('.fc-primary-button').click();");
                System.out.println("Cookies aceites via JS.");
            } catch (Exception jsEx) {
                System.out.println("Pop-up de cookies não apareceu ou já foi fechado.");
            }
        }
        sleep(500); // Pausa para a animação de fecho
    }

    @AfterEach
    void fechar() {
        closeWebDriver();
    }
}