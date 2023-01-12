package br.com.rsousa;


import br.com.rsousa.config.LoadConfigs;
import br.com.rsousa.pages.ConnectionPage;
import br.com.rsousa.pages.LoginPage;
import org.openqa.selenium.WebDriver;


public class MainLinkedin {
    public static void main(String[] args) {
        LoadConfigs configs = new LoadConfigs();
        WebDriver driver = null;

//        So realizo login se tiver sido informado no application.properties
        if (!configs.getLoginLinkedin().isBlank() && configs.getCaminhoDoProfile().isBlank() ) {
            LoginPage loginPage = new LoginPage();

            String login = configs.getLoginLinkedin();
            String password = configs.getPasswordLinkedin();
            loginPage.preencheFormularioLogin(login, password);
            driver = loginPage.getDriver();
        }


/*      So vou a pagina de conexoes caso um caminho de profile do browser JA LOGADO NO LINKEDIN tenha sido informado em application.properties
        Ou se o campo de login não está vazio

        Fazemos essa checagem pois a nossa pagina de conexão necessita de um driver que esteja LOGADO, e para o termos,
        conseguimos via profile ou se nos logamos via bot anteriormente
*/
        if (!configs.getCaminhoDoProfile().isBlank() || !configs.getLoginLinkedin().isBlank()) {
            String urlDesejada = configs.getUrlConexoesLinkedin();
            Integer quantidadedePaginas = configs.getQuantidadeDePaginas();
            ConnectionPage connectionPage = new ConnectionPage(driver, urlDesejada);
            connectionPage.conectaComAsPessoasPorQuantidadeDePaginas(quantidadedePaginas);
            connectionPage.fechar();
        }

    }
}
