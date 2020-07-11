package com.seleniumProject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.*;
public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");

    }
}
