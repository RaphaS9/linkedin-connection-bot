package br.com.rsousa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class LoginPage extends PageObject {

    private final String URL_LOGIN = "https://www.linkedin.com/login/pt";

    public LoginPage() {
        super(null);
        this.driver.navigate().to(URL_LOGIN);
    }

    public void preencheFormularioLogin(String login, String senha) {
        driver.findElement(By.name("session_key")).sendKeys(login);
        driver.findElement(By.name("session_password")).sendKeys(senha);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

}
