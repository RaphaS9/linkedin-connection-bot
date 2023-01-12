package br.com.rsousa.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfigs {
    private static String loginLinkedin;
    private static String passwordLinkedin;
    private static String urlConexoesLinkedin;
    private static String caminhoDoDriver;
    private static String caminhoDoProfile;
    private static Integer quantidadeDePaginas;

    static {
        try (InputStream input = LoadConfigs.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            loginLinkedin = prop.getProperty("linkedin.login");
            passwordLinkedin = prop.getProperty("linkedin.password");
            urlConexoesLinkedin = prop.getProperty("linkedin.conexoes.url");

            caminhoDoDriver = convertePadraoDoPath(prop.getProperty("driver.path"));
            caminhoDoProfile = convertePadraoDoPath(prop.getProperty("driver.path.profile"));

            quantidadeDePaginas = Integer.valueOf(prop.getProperty("linkedin.conexoes.paginas"));
        } catch (IOException ex) {
            System.out.println("Alguma informação não encontrada ou inválida em config.properties");
        }
    }

    private static String convertePadraoDoPath(String path) {
        if (path.contains("\\")) {
            path = path.replace("\\", "/");
        }
        return path;
    }

    public String getLoginLinkedin() {
        return loginLinkedin;
    }

    public String getPasswordLinkedin() {
        return passwordLinkedin;
    }

    public String getUrlConexoesLinkedin() {
        return urlConexoesLinkedin;
    }

    public String getCaminhoDoDriver() {
        return caminhoDoDriver;
    }

    public String getCaminhoDoProfile() {
        return caminhoDoProfile;
    }

    public Integer getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }
}
