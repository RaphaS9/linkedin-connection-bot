package br.com.rsousa.pages;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPage extends PageObject {

    public ConnectionPage(WebDriver driver, String url) {
        super(driver);
        this.driver.navigate().to(url);
    }

    public JavascriptExecutor conectaComAsPessoasDaPagina() {
        List<WebElement> conectarButtons = this.getBotoesConectarDaPaginaDeConexoes();

//        EXECUTANDO A AÇAO DE CLICAR NOS BOTOES COM JAVASCRIPT
        JavascriptExecutor executorJavascript = this.getExecutorJavascript(this.driver);

        conectarButtons.forEach(bt -> {
//            Clica em cada botao "Conectar"
            executorJavascript.executeScript("arguments[0].click();", bt);
            this.aguardar(2);

//            Tenta clicar em "enviar" no popup que aparece apos clicar em "Conectar"
//            e captura a Exception caso o botao "enviar" não seja encontrado, para que o o programa continue seu fluxo,
//            isto pode ocorrer por conta da configuração de privacidade de alguns usuarios que nao permitem novas conexoes
//            com isso o popup é mostrado de outra forma sem ter este botao "enviar"

//            Em seguida no finally clicamos em fechar o popup, isto para que em situaçoes de um usuario que nao aceita conexoes
//            fecharmos e seguir para o proximo passo do laço, caso contrario o programa vai tentar fecha-lo mas ja tera sido feito, nao atrapalhando o funcionamento
            try {
                WebElement enviarConexaoButton = driver.findElement(By.xpath("//button[@aria-label='Enviar agora']"));
                executorJavascript.executeScript("arguments[0].click();", enviarConexaoButton);

            } catch (Exception e) {
            } finally {
                //                CASO TENHAMOS ALCANÇADO O LIMITE DE CONEXAO
                try {
                    WebElement entendiLimiteDeConexaoButton = driver.findElement(By.xpath("//button[@aria-label='Entendi']"));
                    this.fechar();
                    throw new RuntimeException("Você atingiu o limite de conexões semanais do linkedin");
                } catch (WebDriverException e) {
                }

                WebElement closeButton = driver.findElement(By.xpath("//button[@aria-label='Fechar']"));
                executorJavascript.executeScript("arguments[0].click();", closeButton);


                this.aguardar(1.5);
            }
        });

        return executorJavascript;
    }

    private List<WebElement> getBotoesConectarDaPaginaDeConexoes() {
        this.aguardar(2);
        List<WebElement> allButtons = driver.findElements(By.tagName("button"));
        List<WebElement> conectarButtons = new ArrayList<>();

//        PEGANDO SOMENTE OS BOTOES DE CONECTAR
        allButtons.forEach(bt -> {
            if (bt.getText().contains("Conectar")) {
                conectarButtons.add(bt);
            }
        });
        return conectarButtons;
    }

    public void avancaAPaginaDeConexoes(JavascriptExecutor executorJavascript) {
        executorJavascript.executeScript("window.scroll(0, 1500)");
        this.aguardar(1);
        WebElement avancarButton = driver.findElement(By.cssSelector("button[aria-label*='Avançar']"));
        executorJavascript.executeScript("arguments[0].click();", avancarButton);
    }

    public void conectaComAsPessoasPorQuantidadeDePaginas(Integer paginas) {
        for (int i = 1; i <= paginas; i++) {
            JavascriptExecutor executorJavascript = conectaComAsPessoasDaPagina();
            avancaAPaginaDeConexoes(executorJavascript);
            this.aguardar(2);
        }
    }
}
