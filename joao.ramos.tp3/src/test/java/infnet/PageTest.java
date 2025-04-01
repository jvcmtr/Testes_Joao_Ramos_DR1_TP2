package infnet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class PageTest {
    private WebDriver driver;
    private final String URL = "https://evanescent-yam.demo.prestashop.com";

    @BeforeEach
    void InitDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/caminho/para/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void TesteContactUsForm () {
        driver.get(URL + "/en/contact-us");
        
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("Foo@email.com");

        WebElement messageInput = driver.findElement(By.id("contactform-message"));
        messageInput.sendKeys("Teste sendo feito por joao ramos");
        
        WebElement sendButton = driver.findElement(By.name("submitMessage"));
        sendButton.click();

        try {
            driver.wait(1000);
        } catch (Exception e) {
            fail();
        }
    
        // Este teste vai falhar, já que uma vez que você envia a mensagem a pagina retorna  Bad gateway Error code 502 
        WebElement results = driver.findElement(By.id("popup-success"));
        assertTrue(results.isDisplayed(), "A sua mensagem foi recebida com sucesso");
    }

    @AfterEach
    void quitDriver() {
        driver.quit();
    }
}
