package com.zillionwon.spider;

import dev.failsafe.internal.util.Assert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

@DisplayName("示例测试")
public class ExampleTest {
    @DisplayName("Selenium 测试")
    @Test
    public void driverTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.getTitle();
        // 隐式等待很少是最好的解决方案, 但在这里最容易演示, 所以我们将使用它作为占位符
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        textBox.sendKeys("Selenium");
        submitButton.click();
        WebElement message = driver.findElement(By.id("message"));
        Assert.state("Received!".equals(message.getText()), "返回值不为 Received!");
        driver.quit();
    }

    @DisplayName("jsoup 测试")
    @Test
    public void jsoupTest() {
        Document doc;
        try {
            doc = Jsoup.connect("https://en.wikipedia.org/").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            System.out.printf("title: %s\nlink: %s\n\n", headline.attr("title"), headline.absUrl("href"));
        }
    }
}
