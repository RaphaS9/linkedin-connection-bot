package br.com.rsousa.pages;

import br.com.rsousa.config.LoadConfigs;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class PageObject {
    protected WebDriver driver;
    private ChromeOptions options = new ChromeOptions();
    private LoadConfigs configs = new LoadConfigs();

    public PageObject(WebDriver driver) {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", configs.getCaminhoDoDriver());
            this.driver = new ChromeDriver(configOptionsDefault());
        } else {
            this.driver = driver;
        }
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)).pageLoadTimeout(Duration.ofSeconds(10));
    }

    private ChromeOptions configOptionsDefault() {
        if (!configs.getCaminhoDoProfile().isBlank()) {
            this.options.addArguments("user-data-dir=" + configs.getCaminhoDoProfile());
        }
        this.options.addArguments("test-type");
        this.options.addArguments("--start-maximized");
        this.options.addArguments("--disable-popup-blocking");
        return options;
    }

    protected ChromeOptions getOptions() {
        return this.options;
    }

    protected void setOptions(List<String> arguments) {
        this.options.addArguments(arguments);
    }

    public void fechar() {
        this.driver.quit();
    }

    public JavascriptExecutor getExecutorJavascript(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void aguardar(double segundos) {
        try {
            Thread.sleep((long) (segundos * 1000l));
        } catch (InterruptedException e) {
            throw new RuntimeException("problema com thread sleep page object");
        }
    }

}
